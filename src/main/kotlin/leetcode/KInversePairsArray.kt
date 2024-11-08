package leetcode

import kotlin.math.min

class KInversePairsArray {
    companion object {
        val cache = mutableMapOf(Pair(1, 0) to 1)
    }

    private fun (Long).trim(): Int = (this % (1_000_000_000 + 7)).toInt()

    // Interesting to note that the bottom-up dp approach is O(n*k) while the memoization
    // one is O(n^2*k)
    fun kInversePairs(n: Int, k: Int): Int {
        if (k == 0) return 1
        if (n <= 1 || k < 0 || k > n * (n - 1) / 2) return 0
        for (m in 2..n) {
            if (cache.contains(m to min((m * (m - 1) / 2), k))) continue
            var curSum = 0L
            for (h in 0..min((m * (m - 1) / 2), k)) {
                if (h <= (m - 1) * (m - 2) / 2) curSum += cache[(m - 1) to h]!!
                if (h >= m) curSum -= cache.getOrDefault((m - 1) to (h - m), 0)
                cache[m to h] = curSum.trim()
            }
        }
        return cache[n to k]!!
    }

    fun kInversePairsMemo(n: Int, k: Int): Int =
        if (k == 0) 1
        else if (n <= 1 || k < 0 || k > n * (n - 1) / 2) 0
        else cache.getOrPut(n to k) {
            (1..n).sumOf { kInversePairsMemo(n - 1, k - (n - it)).toLong() }.trim()
        }
}
