package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StringCompressionIiTest {
    @Test
    fun testGetLengthOfOptimalCompression() {
        assertEquals(
            4,
            StringCompressionIi().getLengthOfOptimalCompression("aaabcccd", 2)
        )
    }
}
