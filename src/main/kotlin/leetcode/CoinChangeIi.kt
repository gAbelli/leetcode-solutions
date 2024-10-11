package leetcode

class CoinChangeIi {
    fun change(amount: Int, coins: IntArray): Int {
        val dp = IntArray(amount + 1) { 0 }
        dp[0] = 1

        for (coin in coins) {
            for (i in 0..amount - coin) {
                dp[i + coin] = dp[i + coin] + dp[i]
            }
        }

        return dp[amount]
    }
}
