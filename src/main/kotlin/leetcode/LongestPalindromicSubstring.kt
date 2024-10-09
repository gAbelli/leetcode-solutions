package leetcode

class LongestPalindromicSubstring {
    fun longestPalindrome(s: String): String {
        var bestI = 0
        var bestJ = 0
        var result = 1

        for (center in s.indices) {
            // odd
            run {
                var i = center - 1
                var j = center + 1
                while (i >= 0 && j < s.length && s[i] == s[j]) {
                    val length = j - i + 1
                    if (length > result) {
                        result = length
                        bestI = i
                        bestJ = j
                    }
                    i--
                    j++
                }
            }

            // even
            run {
                var i = center
                var j = center + 1
                while (i >= 0 && j < s.length && s[i] == s[j]) {
                    val length = j - i + 1
                    if (length > result) {
                        result = length
                        bestI = i
                        bestJ = j
                    }
                    i--
                    j++
                }
            }
        }

        return s.substring(bestI..bestJ)
    }
}
