package leetcode

class PalindromicSubstrings {
    fun countSubstrings(s: String): Int {
        var result = 0

        for (center in s.indices) {
            // odd
            run {
                var i = center
                var j = center
                while (i >= 0 && j < s.length && s[i] == s[j]) {
                    result++
                    i--
                    j++
                }
            }

            // even
            run {
                var i = center
                var j = center + 1
                while (i >= 0 && j < s.length && s[i] == s[j]) {
                    result++
                    i--
                    j++
                }
            }
        }

        return result
    }
}
