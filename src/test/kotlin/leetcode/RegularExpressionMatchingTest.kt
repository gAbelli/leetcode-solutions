package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegularExpressionMatchingTest {
    @Test
    fun testIsMatch() {
        assertTrue(
            RegularExpressionMatching().isMatch("a", "ab*")
        )
    }
}
