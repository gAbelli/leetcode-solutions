package leetcode

class LongestCommonSubsequence {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        if (text1.length > text2.length) return longestCommonSubsequence(text2, text1)
        val dp = IntArray(text1.length + 1) { 0 }
        for (j in text2.indices) {
            var topLeftBackup = dp[0]
            for (i in 1..text1.length) {
                val tmp = dp[i]
                dp[i] = maxOf(dp[i], dp[i - 1], topLeftBackup + if (text1[i - 1] == text2[j]) 1 else 0)
                topLeftBackup = tmp
            }
        }
        return dp[text1.length]
    }
}
