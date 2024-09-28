package leetcode

import kotlin.math.max
import kotlin.math.min

class BestTimeToBuyAndSellStock {
    fun maxProfit(prices: IntArray): Int {
        var result = 0
        var minimumSeenPrice = prices[0]
        for (i in 1..<prices.size) {
            result = max(result, prices[i] - minimumSeenPrice)
            minimumSeenPrice = min(minimumSeenPrice, prices[i])
        }
        return result
    }
}
