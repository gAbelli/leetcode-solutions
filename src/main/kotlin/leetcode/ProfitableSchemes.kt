package leetcode

import kotlin.math.max
import kotlin.math.min

class ProfitableSchemes {
    private val MOD = 1_000_000_000 + 7
    private fun (Long).trim(): Long = (this % MOD + MOD) % MOD

    // The number of rows can be n+1 instead of groupSum because we can completely ignore the profits that
    // require more than n people.
    // The fact that the number of columns can be minProfit+1 is more mysterious to me.
    // It needs to be combined with the fact that the for loop goes only from minProfit downTo 0
    // instead of starting from profit.sum(). I believe that we should just interpret
    // dp[i][j] as the number of ways to make profit j with i people if j < minProfit, otherwise
    // as the number of ways to make *at least* minProfit with i people.
    fun profitableSchemes(n: Int, minProfit: Int, group: IntArray, profit: IntArray): Int {
        val dp = Array(n + 1) { LongArray(minProfit + 1) { 0 } }
        dp[0][0] = 1
        for (k in group.indices) for (j in minProfit downTo 0) for (i in n downTo group[k])
            dp[i][j] = (dp[i][j] + dp[i - group[k]][max(j - profit[k], 0)]).trim()
        return (0..n).sumOf { dp[it][minProfit] }.trim().toInt()
    }

    fun profitableSchemesClassic(n: Int, minProfit: Int, group: IntArray, profit: IntArray): Int {
        val groupSum = group.sum()
        val profitSum = profit.sum()
        val dp = Array(groupSum + 1) { LongArray(profitSum + 1) { 0 } }
        dp[0][0] = 1
        for (k in group.indices) for (j in profitSum downTo profit[k]) for (i in groupSum downTo group[k])
            dp[i][j] = (dp[i][j] + dp[i - group[k]][j - profit[k]]).trim()
        return (0..min(n, groupSum)).sumOf { i -> (minProfit..profitSum).sumOf { j -> dp[i][j] }.trim() }.trim().toInt()
    }
}
