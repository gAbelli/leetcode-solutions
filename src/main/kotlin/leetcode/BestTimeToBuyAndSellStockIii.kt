package leetcode

import kotlin.math.max

class BestTimeToBuyAndSellStockIii {
    // Let dp[i] be the solution to BestTimeToBuyAndSellStockI starting from index i
    // Then the solution to this is dp2[0] where
    // dp2[i] = max_{i' >= i}{-prices[i'] + max_{j >= i'}{prices[j] + dp[j + 1]}}
    // Now let's call
    // dp3[i] = max_{j >= i}{prices[j] + dp[j + 1]}
    //        = max(prices[i] + dp[i+1], dp3[i+1]}
    // dp2[i] = max_{i' >= i}{-prices[i'] + dp3[i']}
    //        = max(-prices[i] + dp3[i], dp2[i+1])
    // Naming could be improved of course. Here is a solution in constant space.
    fun maxProfit(prices: IntArray): Int {
        var dpi = 0
        var dp2i = 0
        var dp3i = 0
        var maxSoFar = 0
        for (i in prices.indices.reversed()) {
            maxSoFar = max(maxSoFar, prices[i])
            dp3i = max(prices[i] + dpi, dp3i)
            dpi = max(maxSoFar - prices[i], dpi)
            dp2i = max(-prices[i] + dp3i, dp2i)
        }
        return dp2i
    }
}
