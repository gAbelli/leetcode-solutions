package leetcode

import kotlin.math.min

// Apparently there is also a simpler solution:
// f(n, goal, k) = f(n - 1, goal - 1, k) * n + f(n, goal - 1, k) * (n - k)
// If the first goal-1 songs only include exactly n-1 different songs, then
// we are forced to put the last one at the end. Otherwise, we have to choose
// among n-k songs.
class NumberOfMusicPlaylists {
    companion object {
        // bin(n+1, k+1) = bin(n, k) + bin(n, k+1)
        // bin(n, k) = bin(n-1, k-1) + bin(n-1, k)
        val binomial = Array(101) { LongArray(101) { 0 } }

        init {
            binomial[0][0] = 1
            for (n in 1..100) for (k in 0..n) binomial[n][k] =
                (binomial[n - 1].getOrElse(k - 1) { 0 } + binomial[n - 1][k]).trim()
        }

        fun (Long).trim(): Long = (this % (1_000_000_000 + 7) + (1_000_000_000 + 7)) % (1_000_000_000 + 7)
    }

    fun numMusicPlaylists(n: Int, goal: Int, k: Int): Int {
        val dp = IntArray(n + 1) { 0 }
        for (i in 1..n) {
            var result = numMusicPlaylistsAtMost(i, goal, k)
            for (j in 1..i) result = (result - binomial[i][j] * dp[i - j]).trim()
            dp[i] = result.trim().toInt()
        }
        return dp[n]
    }

    // Either k < n or goal <= n
    private fun numMusicPlaylistsAtMost(n: Int, goal: Int, k: Int): Long {
        if (goal > n && k >= n) return 0

        tailrec fun helper(n: Int, goal: Int, k: Int, acc: Long): Long {
            if (goal == 0) return acc.trim()
            val newAcc = acc * (n - min(k, goal - 1))
            return helper(n, goal - 1, k, newAcc.trim())
        }

        return helper(n, goal, k, 1).trim()
    }
}
