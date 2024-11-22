package leetcode

class WildcardMatching {
    // There is a O(n) time O(1) solution: https://leetcode.com/problems/wildcard-matching/solutions/17810/linear-runtime-and-constant-space-solution/
    fun isMatch(s: String, p: String): Boolean {
        val memo = mutableMapOf<Pair<Int, Int>, Boolean>()

        fun helper(i: Int, j: Int): Boolean = memo.getOrPut(i to j) {
            if (i == s.length) {
                if (j == p.length) true
                else if (p[j] == '*') helper(i, j + 1)
                else false
            } else if (j == p.length) false
            else {
                if (p[j] == '*') (i..s.length).fold(false) { acc, newI -> acc || helper(newI, j + 1) }
                else if (s[i] == p[j] || p[j] == '?') helper(i + 1, j + 1)
                else false
            }
        }

        return helper(0, 0)
    }
}
