package leetcode

class DataStreamAsDisjointIntervals {
    val intervals = mutableListOf<IntArray>()
    fun (List<IntArray>).safeGet(i: Int): IntArray {
        return if (i < 0) intArrayOf(Int.MIN_VALUE, Int.MIN_VALUE)
        else get(i)
    }

    fun maybeJoinWithSuccessor(i: Int) {
        val interval = intervals[i]
        if (i != intervals.size - 1 && intervals[i + 1][0] == interval[1] + 1) {
            interval[1] = intervals[i + 1][1]
            intervals.removeAt(i + 1)
        }
    }

    fun addNum(value: Int) {
        // Find the largest index i such that value >= intervals[i][0]
        // or -1 if it doesn't exist
        var left = -1
        var right = intervals.size - 1
        while (left < right) {
            val mid = (left + right + 1) / 2
            if (value >= intervals.safeGet(mid)[0]) left = mid
            else right = mid - 1
        }
        if (left == -1) {
            intervals.add(0, intArrayOf(value, value))
            maybeJoinWithSuccessor(0)
        } else {
            val interval = intervals[left]
            if (value <= interval[1]) return
            else if (value == interval[1] + 1) {
                interval[1] = value
                maybeJoinWithSuccessor(left)
            } else {
                intervals.add(left + 1, intArrayOf(value, value))
                maybeJoinWithSuccessor(left + 1)
            }
        }
    }

    fun getIntervals(): Array<IntArray> = intervals.toTypedArray()
}
