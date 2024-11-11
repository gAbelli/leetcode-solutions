package leetcode

import kotlin.math.max
import kotlin.math.min

class MinimumDifficultyOfAJobSchedule {
    fun minDifficulty(jobDifficulty: IntArray, d: Int): Int {
        val n = jobDifficulty.size
        if (d > n) return -1

        val memo = Array(n) { IntArray(d + 1) { -1 } }

        // i <= n - d
        // d >= 1
        fun helper(i: Int, d: Int): Int {
            if (memo[i][d] != -1) return memo[i][d]
            if (d == 1) {
                val result = (i..<n).maxOf { jobDifficulty[it] }
                memo[i][d] = result
                return result
            }
            var maxSoFar = 0
            var result = Int.MAX_VALUE
            for (j in i..n - d) {
                maxSoFar = max(maxSoFar, jobDifficulty[j])
                result = min(result, maxSoFar + helper(j + 1, d - 1))
            }
            memo[i][d] = result
            return result
        }

        return helper(0, d)
    }
}
