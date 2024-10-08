package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FirstMissingPositiveTest {
    @Test
    fun testFirstMissingPositive() {
        assertEquals(2, FirstMissingPositive().firstMissingPositive(intArrayOf(1, 1)))
    }
}
