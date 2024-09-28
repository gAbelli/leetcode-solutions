package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.streams.toList

class GroupAnagramsTest {
    @Test
    fun checkArrayEquality() {
        val s1 = "abc".chars().sorted().toList()
        val s2 = "abc".chars().sorted().toList()
        assertEquals(s1, s2)
    }
}
