package leetcode

import kotlin.math.max

class MaximumValueOfKCoinsFromPiles {
    fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int {
        val dp = Array(k + 1) { IntArray(piles.size) { 0 } }
        for (m in piles.indices) {
            val pile = piles[m]
            var curSum = 0
            for (blocksFromLastPile in 0..pile.size) {
                for (h in blocksFromLastPile..k) dp[h][m] =
                    max(dp[h][m], dp[h - blocksFromLastPile].getOrElse(m - 1) { 0 } + curSum)
                if (blocksFromLastPile != pile.size) curSum += pile[blocksFromLastPile]
            }
        }
        return dp.last().last()
    }
}
