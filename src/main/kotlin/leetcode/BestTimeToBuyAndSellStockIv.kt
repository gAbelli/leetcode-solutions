package leetcode

import kotlin.math.max

class BestTimeToBuyAndSellStockIv {
    // Let dp[i][k] the maximum profit we can obtain if we start at day i and we
    // complete at most k transactions. Then
    // dp[i][k] = max_{i' >= i}{-prices[i'] + max_{j >= i'}{prices[j] + dp[j+1][k-1]}
    // dp2[i][k] = max_{j >= i}{prices[j] + dp[j+1][k-1]}
    //           = max(prices[i] + dp[i+1][k-1], dp2[i+1][k])
    // dp[i][k] = max_{i' >= i}{-prices[i'] + dp2[i'][k]}
    //          = max(-prices[i] + dp2[i][k], dp[i+1][k])
    // With the usual tricks we can lower the space complexity
    fun maxProfit(k: Int, prices: IntArray): Int {
        val n = prices.size
        val dp = Array(n + 1) { IntArray(k + 1) { 0 } }
        val dp2 = Array(n + 1) { IntArray(k + 1) { 0 } }
        for (i in prices.indices.reversed()) for (j in 1..k) {
            dp2[i][j] = max(prices[i] + dp[i + 1][j - 1], dp2[i + 1][j])
            dp[i][j] = max(-prices[i] + dp2[i][j], dp[i + 1][j])
        }
        return dp[0][k]
    }
}
