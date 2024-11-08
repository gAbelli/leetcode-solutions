package leetcode

import kotlin.math.max

class MaximumProfitInJobScheduling {
    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val n = startTime.size
        val sortedIndices = (0..<n).sortedBy { startTime[it] }
        val startTime = IntArray(n) { startTime[sortedIndices[it]] }
        val endTime = IntArray(n) { endTime[sortedIndices[it]] }
        val profit = IntArray(n) { profit[sortedIndices[it]] }

        val dp = IntArray(n + 1) { 0 }
        for (i in n - 1 downTo 0) {
            // Find the smallest j such that endTime[i] <= startTime[j]
            var left = i + 1
            var right = n
            while (left < right) {
                val mid = (left + right) / 2
                if (endTime[i] <= startTime[mid]) right = mid
                else left = mid + 1
            }

            dp[i] = max(dp[i + 1], profit[i] + dp[left])
        }
        return dp[0]
    }
}
