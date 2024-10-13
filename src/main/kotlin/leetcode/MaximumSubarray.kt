package leetcode

import kotlin.math.max

class MaximumSubarray {
    fun maxSubArray(nums: IntArray): Int {
        var result = nums.first()
        var bestEndingHere = nums.first()
        for (i in 1..<nums.size) {
            bestEndingHere = max(nums[i], nums[i] + bestEndingHere)
            result = max(result, bestEndingHere)
        }
        return result
    }
}
