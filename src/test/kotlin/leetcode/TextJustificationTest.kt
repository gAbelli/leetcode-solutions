package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TextJustificationTest {
    @Test
    fun testFullJustify() {
        assertEquals(
            listOf(
                "Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  "
            ),
            TextJustification().fullJustify(
                arrayOf(
                    "Science",
                    "is",
                    "what",
                    "we",
                    "understand",
                    "well",
                    "enough",
                    "to",
                    "explain",
                    "to",
                    "a",
                    "computer.",
                    "Art",
                    "is",
                    "everything",
                    "else",
                    "we",
                    "do"
                ),
                20
            )
        )
    }
}
