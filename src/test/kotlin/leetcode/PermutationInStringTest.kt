package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PermutationInStringTest {
    @Test
    fun testPermutationInString() {
        val s1 = "ab"
        val s2 = "eidbaooo"
        assertTrue(PermutationInString().checkInclusion(s1, s2))
    }
}
