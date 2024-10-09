package leetcode

import kotlin.math.min

class CoinChange {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val coins = coins.filter { it <= amount }.toIntArray()
        val dp = IntArray(amount + 1) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in 0..<amount) {
            if (dp[i] == Int.MAX_VALUE) continue
            for (coin in coins) if (i + coin <= amount) {
                dp[i + coin] = min(dp[i + coin], dp[i] + 1)
            }
        }

        return if (dp[amount] == Int.MAX_VALUE) -1 else dp[amount]
    }
}
