package leetcode

import kotlin.math.min

class StringCompressionIi {
    private fun charactersCount(n: Int): Int = when (n) {
        100 -> 4
        in 10..99 -> 3
        in 2..9 -> 2
        1 -> 1
        0 -> 0
        else -> throw Exception("Unreachable")
    }

    fun getLengthOfOptimalCompression(s: String, k: Int): Int {
        val memo = Array(s.length + 1) { IntArray(k + 1) { -1 } }
        run {
            memo[0][0] = 0
            var curChar = s[0]
            var curCount = 1
            var curLength = 0
            for (i in 1..<s.length) {
                memo[i][0] = curLength + charactersCount(curCount)
                if (s[i] == curChar) {
                    curCount += 1
                } else {
                    curChar = s[i]
                    curLength += charactersCount(curCount)
                    curCount = 1
                }
            }
            memo[s.length][0] = curLength + charactersCount(curCount)
        }

        // Best solution for the substring consisting of the first i characters and k=k.
        fun helper(i: Int, k: Int): Int {
            if (i == 0) return 0
            if (memo[i][k] != -1) return memo[i][k]
            // Option 1: we drop the last character (notice that k>0)
            var result = helper(i - 1, k - 1)
            // Option 2: we keep it. Then we need to understand how many copies of it we want to keep.
            var lastCharacterCount = 0
            var removed = 0
            var j = i - 1
            while (j >= 0) {
                if (s[j] == s[i - 1]) lastCharacterCount += 1
                else removed += 1
                if (removed > k) break
                result = min(result, charactersCount(lastCharacterCount) + helper(j, k - removed))
                j -= 1
            }
            memo[i][k] = result
            return result
        }

        return helper(s.length, k)
    }
}
