package leetcode

import kotlin.math.max

class DeleteAndEarn {
    fun deleteAndEarn(nums: IntArray): Int {
        val numsWithValue =
            nums.asSequence()
                .groupingBy { it }
                .eachCountTo(sortedMapOf())
                .mapValues { (key, value) -> key * value }
                .toList()
        val n = numsWithValue.size
        val dp = IntArray(n)
        dp[n - 1] = numsWithValue[n - 1].second
        for (i in n - 2 downTo 0) {
            if (numsWithValue[i].first + 1 == numsWithValue[i + 1].first)
                dp[i] = max(dp[i + 1], numsWithValue[i].second + dp.getOrElse(i + 2) { 0 })
            else
                dp[i] = numsWithValue[i].second + dp[i + 1]
        }
        return dp[0]
    }
}
