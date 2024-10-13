package leetcode

import kotlin.math.max
import kotlin.math.min

class InsertInterval {
    private fun overlap(i1: IntArray?, i2: IntArray): Boolean =
        (i1 == null) || !(i1[0] > i2[1] || i2[0] > i1[1])

    private fun join(i1: IntArray?, i2: IntArray): IntArray =
        if (i1 == null) i2
        else intArrayOf(min(i1[0], i2[0]), max(i1[1], i2[1]))

    // Of course you can improve this by
    // * inserting the new interval in the right place manually without using .sortedBy
    // * not inserting the new interval at all, but the solution becomes pretty ugly
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        val intervals = (intervals.asSequence() + newInterval).sortedBy { it[0] }

        var current: IntArray? = null
        for (interval in intervals) {
            if (overlap(current, interval)) {
                current = join(current, interval)
            } else {
                result.add(current!!)
                current = interval
            }
        }
        if (current != null) result.add(current)

        return result.toTypedArray()
    }
}
