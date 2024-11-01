package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinimizeDeviationInArrayTest {
    @Test
    fun testMinimumDeviation() {
        assertEquals(
            3,
            MinimizeDeviationInArray().minimumDeviation(intArrayOf(4, 1, 5, 20, 3))
        )
    }
}
