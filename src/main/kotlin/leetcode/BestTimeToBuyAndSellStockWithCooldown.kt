package leetcode

import kotlin.math.max

class BestTimeToBuyAndSellStockWithCooldown {
    // Let's say we have dp[i] = max_{i' >= i}(-prices[i'] + max_{j >= i'}(prices[j] + dp[j+2])). Then
    // dp[i-1] = max(dp[i], -prices[i-1] + max_{j >= i-1}(prices[j] + dp[j+2]))
    // = max(dp[i], -prices[i-1] + max(max_{j >= i}(prices[j] + dp[j+2]), (prices[i-1] + dp[i+1]))
    // If we call dp2[i] = max_{j >= i}(prices[j] + dp[j+2]), the formula becomes:
    // * dp2[i-1] = max(dp2[i], prices[i-1] + dp[i+1])
    // * dp[i-1] = max(dp[i], -prices[i-1] + dp2[i])
    // If we want to do this in constant space, we need to keep track of:
    // * dp[i], dp[i+1]
    // * dp2[i]
    fun maxProfit(prices: IntArray): Int {
        var dpi = 0
        var dpiPlusOne = 0
        var dp2i = prices[prices.size - 1]
        for (i in prices.size - 1 downTo 1) {
            dp2i = max(dp2i, prices[i - 1] + dpiPlusOne).also {
                dpi = max(dpi, -prices[i - 1] + dp2i).also {
                    dpiPlusOne = dpi
                }
            }
        }
        return dpi
    }

    fun maxProfitLinearSpace(prices: IntArray): Int {
        val dp = IntArray(prices.size + 1) { 0 }
        val dp2 = IntArray(prices.size) { 0 }
        dp2[prices.size - 1] = prices[prices.size - 1]
        for (i in prices.size - 1 downTo 1) {
            dp2[i - 1] = max(dp2[i], prices[i - 1] + dp[i + 1])
            dp[i - 1] = max(dp[i], -prices[i - 1] + dp2[i])
        }
        return dp[0]
    }
}
