package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ThreeSumTest {
    @Test
    fun testThreeSum() {
        val nums = intArrayOf(-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6)
        assertEquals(
            listOf(
                listOf(-4, -2, 6),
                listOf(-4, 0, 4),
                listOf(-4, 1, 3),
                listOf(-4, 2, 2),
                listOf(-2, -2, 4),
                listOf(-2, 0, 2)
            ),
            ThreeSum().threeSum(nums)
        )
    }
}
