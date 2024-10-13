package leetcode

class RegularExpressionMatching {
    fun isMatch(s: String, p: String): Boolean {
        val memo = Array(s.length) { Array<Boolean?>(p.length) { null } }

        fun helper(i: Int, j: Int): Boolean {
            if (j == p.length) return i == s.length
            if (i != s.length && memo[i][j] != null) return memo[i][j]!!
            val isStar = (j < p.length - 1 && p[j + 1] == '*')
            if (!isStar && i == s.length) return false
            var result = false
            when (p[j]) {
                in 'a'..'z' -> {
                    if (isStar) {
                        var ii = i
                        while (ii <= s.length) {
                            if (helper(ii, j + 2)) {
                                result = true
                                break
                            }
                            if (ii == s.length || s[ii] != p[j]) break
                            ii++
                        }
                    } else {
                        result = if (s[i] == p[j]) helper(i + 1, j + 1)
                        else false
                    }
                }

                '.' -> {
                    if (isStar) {
                        var ii = i
                        while (ii <= s.length) {
                            if (helper(ii, j + 2)) {
                                result = true
                                break
                            }
                            ii++
                        }
                    } else result = helper(i + 1, j + 1)
                }

                else -> throw Exception("Unreachable")
            }
            if (i != s.length) memo[i][j] = result
            return result
        }

        return helper(0, 0)
    }
}
