package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IpoTest {
    @Test
    fun testFindMaximizedCapital() {
        assertEquals(
            4,
            Ipo().findMaximizedCapital(2, 0, intArrayOf(1, 2, 3), intArrayOf(0, 1, 1))
        )
    }
}
