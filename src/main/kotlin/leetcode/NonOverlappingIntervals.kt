package leetcode

class NonOverlappingIntervals {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortWith(compareBy { it[1] })
        var result = 0
        var prev = 0
        for (i in 1..<intervals.size) {
            if (intervals[i][0] < intervals[prev][1]) result += 1
            else prev = i
        }
        return result
    }
}
