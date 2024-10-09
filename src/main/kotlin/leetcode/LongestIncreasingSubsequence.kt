package leetcode

import kotlin.math.max

class LongestIncreasingSubsequence {
    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size) { 1 }
        for (i in nums.indices) {
            for (j in 0..<i) if (nums[j] < nums[i]) dp[i] = max(dp[i], dp[j] + 1)
        }
        return dp.max()
    }
}
