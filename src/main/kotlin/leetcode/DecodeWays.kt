package leetcode

class DecodeWays {
    fun numDecodings(s: String): Int {
        val memo = IntArray(s.length) { -1 }

        fun decodeFrom(i: Int): Int {
            if (i > s.length) return 0
            if (i == s.length) return 1
            if (s[i] == '0') return 0
            if (memo[i] != -1) return memo[i]
            var result = 0
            if (s[i] == '1') {
                result += decodeFrom(i + 2)
            }
            if (s[i] == '2' && i != s.length - 1 && s[i + 1] in '0'..'6') {
                result += decodeFrom(i + 2)
            }
            result += decodeFrom(i + 1)
            memo[i] = result
            return result
        }

        return decodeFrom(0)
    }
}
