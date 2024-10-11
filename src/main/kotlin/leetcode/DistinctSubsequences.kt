package leetcode

class DistinctSubsequences {
    fun numDistinct(s: String, t: String): Int {
        val memo = Array(s.length) { IntArray(t.length) { -1 } }

        fun helper(i: Int, j: Int): Int {
            if (j == t.length) return 1
            if (i == s.length) return 0
            if (memo[i][j] != -1) return memo[i][j]
            var result = helper(i + 1, j)
            if (s[i] == t[j]) result += helper(i + 1, j + 1)
            memo[i][j] = result
            return result
        }

        return helper(0, 0)
    }
}
