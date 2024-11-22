package leetcode

import kotlin.math.min

class PaintingTheWalls {
    // In my interpretation dp[i][j] is the lowest cost that we can obtain if we are allowed
    // to use tasks up to the ith one, and we use a time of exactly min(j, n).
    // We can optimize this even further by saying that we use a time of at least j.
    // If the update formula is dp[i][j] = min(dp[i-1][j], cost[i] + dp[max(0, j-time[i-1]-1])]
    // then apparently everything works.
    // Notice of course that we have dropped one dimension from the dp array.
    // This is fine as long as we iterate through it in reverse.
    fun paintWalls(cost: IntArray, time: IntArray): Int {
        val n = cost.size
        val timeSum = time.sum() + n
        val dp = LongArray(n + 1) { Int.MAX_VALUE.toLong() }
        dp[0] = 0
        for (i in 1..n) for (j in timeSum downTo time[i - 1] + 1)
            dp[min(j, n)] = min(
                dp[min(j, n)],
                cost[i - 1] + dp[min(j - time[i - 1] - 1, n)]
            )

        return dp[n].toInt()
    }

    fun paintWallsFullMatrix(cost: IntArray, time: IntArray): Int {
        val n = cost.size
        val timeSum = time.sum()
        val dp = Array(n + 1) { LongArray(timeSum + 1) { Int.MAX_VALUE.toLong() } }
        dp[0][0] = 0
        for (i in 1..n) for (j in 0..time.sum()) {
            dp[i][j] = min(
                dp[i - 1][j],
                if (j >= time[i - 1] + 1) cost[i - 1] + dp[i - 1][j - time[i - 1] - 1]
                else Long.MAX_VALUE
            )
        }
        return (n..timeSum).minOf { dp[n][it] }.toInt()
    }

    // I like this solution but it's not efficient enough
    fun paintWallsSlow(cost: IntArray, time: IntArray): Int {
        val n = cost.size
        val dp = Array(n + 1) {
            mutableMapOf</* number of paid workers */ Int, /* total cost */ Int>()
        }
        dp[0][0] = 0
        var result = Int.MAX_VALUE
        for (i in cost.indices) for (t in dp.indices.reversed()) for ((paidWorkersCount, totalCost) in dp[t])
            if (t + time[i] >= n - (paidWorkersCount + 1)) result = min(result, totalCost + cost[i])
            else dp[t + time[i]][paidWorkersCount + 1] = min(
                dp[t + time[i]].getOrDefault(paidWorkersCount + 1, Int.MAX_VALUE),
                totalCost + cost[i]
            )

        return result
    }
}
