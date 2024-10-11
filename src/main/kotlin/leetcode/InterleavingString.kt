package leetcode

class InterleavingString {
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s3.length != s1.length + s2.length) return false

        val memo = mutableMapOf<Pair<Int, Int>, Boolean>()

        fun helper(i: Int, j: Int): Boolean {
            if (i == s1.length && j == s2.length) return true
            if (i to j in memo) return memo[i to j]!!
            val c = s3[i + j]
            var result = false
            if (i in s1.indices && c == s1[i]) result = result || helper(i + 1, j)
            if (j in s2.indices && c == s2[j]) result = result || helper(i, j + 1)
            memo[i to j] = result
            return result
        }

        return helper(0, 0)
    }
}
