package leetcode

import kotlin.math.max
import kotlin.math.min

class SplitArrayLargestSum {
    // I also came up with the binary search solution, but I'm not convinced it is better.
    // In fact, we know that k <= 50, and the binary search solution would be O(n*log(nums.sum() - nums.max()))
    // so it really depends on how large the sum and the maximum are.
    fun splitArray(nums: IntArray, k: Int): Int {
        val memo = Array(nums.size + 1) { IntArray(k + 1) { -1 } }

        fun helper(i: Int, k: Int): Int {
            if (k == 0) return if (i == nums.size) 0 else Int.MAX_VALUE
            if (memo[i][k] != -1) return memo[i][k]

            var curSum = 0
            var best = Int.MAX_VALUE
            for (j in i..nums.size - k) {
                curSum += nums[j]
                best = min(best, max(curSum, helper(j + 1, k - 1)))
            }
            memo[i][k] = best
            return best
        }

        return helper(0, k)
    }
}
