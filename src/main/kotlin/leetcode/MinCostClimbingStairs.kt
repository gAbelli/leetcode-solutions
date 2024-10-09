package leetcode

import kotlin.math.min

class MinCostClimbingStairs {
    fun minCostClimbingStairs(cost: IntArray): Int {
        var a = cost.last()
        var b = 0
        for (i in cost.size - 2 downTo 0) {
            b = a.also { a = cost[i] + min(a, b) }
        }
        return min(a, b)
    }
}
