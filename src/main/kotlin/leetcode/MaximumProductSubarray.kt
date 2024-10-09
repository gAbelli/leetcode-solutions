package leetcode

import kotlin.math.max

class MaximumProductSubarray {
    fun maxProduct(nums: IntArray): Int {
        var result = nums.first()
        var minProductEndingHere = nums.first()
        var maxProductEndingHere = nums.first()

        for (i in 1..<nums.size) {
            val num = nums[i]
            maxProductEndingHere = maxOf(num, num * maxProductEndingHere, num * minProductEndingHere).also {
                minProductEndingHere = minOf(num, num * minProductEndingHere, num * maxProductEndingHere)
            }
            result = max(result, maxProductEndingHere)
        }

        return result
    }
}
