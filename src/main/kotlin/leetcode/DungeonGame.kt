package leetcode

import kotlin.math.max
import kotlin.math.min

class DungeonGame {
    // Actually we can also do this without bisection
    fun calculateMinimumHP(dungeon: Array<IntArray>): Int {
        val m = dungeon.size
        val n = dungeon[0].size
        val upperBound = run {
            var curSum = dungeon[0][0]
            var curMin = dungeon[0][0]
            for (j in 1..<n) {
                curSum += dungeon[0][j]
                curMin = min(curMin, curSum)
            }
            for (i in 1..<m) {
                curSum += dungeon[i][n - 1]
                curMin = min(curMin, curSum)
            }
            -curMin + 1
        }
        if (upperBound <= 1) return 1

        fun isFeasible(startHealth: Int): Boolean {
            val healthMatrix = Array(m) { Array<Int?>(n) { null } }
            healthMatrix[0][0] = (startHealth + dungeon[0][0]).let { if (it <= 0) null else it }

            for (i in 0..<m) for (j in 0..<n) {
                if (i > 0 && healthMatrix[i - 1][j] != null) healthMatrix[i][j] =
                    (dungeon[i][j] + healthMatrix[i - 1][j]!!).let { if (it <= 0) null else it }
                if (j > 0 && healthMatrix[i][j - 1] != null) healthMatrix[i][j] =
                    max(healthMatrix[i][j] ?: Int.MIN_VALUE, dungeon[i][j] + healthMatrix[i][j - 1]!!).let {
                        if (it <= 0) null else it
                    }
            }

            return healthMatrix[m - 1][n - 1] != null
        }

        var left = 1
        var right = upperBound
        while (left < right) {
            val mid = (left + right) / 2
            if (isFeasible(mid)) right = mid
            else left = mid + 1
        }

        return left
    }
}
