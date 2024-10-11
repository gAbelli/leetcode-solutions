package leetcode

class EditDistance {
    fun minDistance(s: String, t: String): Int {
        val memo = Array(s.length) { IntArray(t.length) { -1 } }

        fun helper(i: Int, j: Int): Int {
            if (i == s.length) return t.length - j
            if (j == t.length) return s.length - i
            if (memo[i][j] != -1) return memo[i][j]
            val result = if (s[i] == t[j]) helper(i + 1, j + 1)
            else 1 + minOf(
                helper(i + 1, j + 1),
                helper(i + 1, j),
                helper(i, j + 1)
            )
            memo[i][j] = result
            return result
        }

        return helper(0, 0)
    }
}
