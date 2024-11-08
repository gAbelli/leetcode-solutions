package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KInversePairsArrayTest {
    @Test
    fun testKInversePairs() {
        assertEquals(
            663677020,
            KInversePairsArray().kInversePairs(1000, 1000)
        )
    }
}
