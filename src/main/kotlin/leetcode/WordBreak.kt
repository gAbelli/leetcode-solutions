package leetcode

class WordBreak {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val memo = BooleanArray(s.length) { false }

        fun helper(i: Int): Boolean {
            if (i == s.length) return true
            if (memo[i]) return false
            for (word in wordDict) {
                if (s.substring(i).startsWith(word) && helper(i + word.length)) return true
            }
            memo[i] = true
            return false
        }

        return helper(0)
    }
}
