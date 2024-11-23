package leetcode

import kotlin.math.min

class PalindromePartitioningIi {
    fun minCut(s: String): Int {
        val n = s.length
        val palindromeEndings = Array(n) { mutableListOf<Int>() }
        // odd
        for (mid in 0..<n) {
            var i = mid
            var j = mid
            while (i in s.indices && j in s.indices && s[i] == s[j]) {
                palindromeEndings[i].add(j)
                i -= 1
                j += 1
            }
        }
        // even
        for (mid in 0..<n - 1) {
            var i = mid
            var j = mid + 1
            while (i in s.indices && j in s.indices && s[i] == s[j]) {
                palindromeEndings[i].add(j)
                i -= 1
                j += 1
            }
        }
        val dp = IntArray(n + 1) { n + 1 }
        dp[n] = 0
        for (i in s.indices.reversed()) for (j in palindromeEndings[i]) dp[i] = min(dp[i], 1 + dp[j + 1])
        return dp[0] - 1
    }
}
