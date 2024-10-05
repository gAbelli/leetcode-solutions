package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MedianOfTwoSortedArraysTest {
    @Test
    fun testFindMedianSortedArrays() {
        assertEquals(
            2.0,
            MedianOfTwoSortedArrays().findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2))
        )
    }
}
