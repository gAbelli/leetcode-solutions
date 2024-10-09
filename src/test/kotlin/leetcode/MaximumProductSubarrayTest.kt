package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaximumProductSubarrayTest {
    @Test
    fun testMaxProduct() {
        assertEquals(12, MaximumProductSubarray().maxProduct(intArrayOf(-4, -3, -2)))
    }
}
