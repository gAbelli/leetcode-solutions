package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NonOverlappingIntervalsTest {
    @Test
    fun testEraseOverlapIntervals() {
        val intervals = arrayOf(
            intArrayOf(-3035, 30075),
            intArrayOf(1937, 6906),
            intArrayOf(11834, 20971),
            intArrayOf(44578, 45600),
            intArrayOf(28565, 37578),
            intArrayOf(19621, 34415),
            intArrayOf(32985, 36313),
            intArrayOf(-8144, 1080),
            intArrayOf(-15279, 21851),
            intArrayOf(-27140, -14703),
            intArrayOf(-12098, 16264),
            intArrayOf(-36057, -16287),
            intArrayOf(47939, 48626),
            intArrayOf(-15129, -5773),
            intArrayOf(10508, 46685),
            intArrayOf(-35323, -26257)
        )
        assertEquals(
            9,
            NonOverlappingIntervals().eraseOverlapIntervals(intervals)
        )
    }
}
