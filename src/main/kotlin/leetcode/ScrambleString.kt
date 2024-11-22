package leetcode

class ScrambleString {
    fun isScramble(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false

        val charCounts1 = Array(s1.length) { Array(s1.length) { listOf<Int>() } }
        for (i in s1.indices) {
            val counts = IntArray('z' - 'a' + 1) { 0 }
            for (j in i..<s1.length) {
                counts[s1[j] - 'a'] += 1
                charCounts1[i][j - i] = counts.toList()
            }
        }
        val charCounts2 = Array(s1.length) { Array(s1.length) { listOf<Int>() } }
        for (i in s2.indices) {
            val counts = IntArray('z' - 'a' + 1) { 0 }
            for (j in i..<s2.length) {
                counts[s2[j] - 'a'] += 1
                charCounts2[i][j - i] = counts.toList()
            }
        }

        val memo = mutableMapOf<Triple<Int, Int, Int>, Boolean>()
        fun helper(start1: Int, start2: Int, length: Int): Boolean =
            if (length == 1) s1[start1] == s2[start2]
            else memo.getOrPut(Triple(start1, start2, length)) {
                (1..<length).any { i ->
                    run {
                        charCounts1[start1][i - 1] == charCounts2[start2][i - 1]
                                && charCounts1[start1 + i][length - i - 1] == charCounts2[start2 + i][length - i - 1]
                                && helper(start1, start2, i)
                                && helper(start1 + i, start2 + i, length - i)
                    } || run {
                        charCounts1[start1][i - 1] == charCounts2[start2 + length - i][i - 1]
                                && charCounts1[start1 + i][length - i - 1] == charCounts2[start2][length - i - 1]
                                && helper(start1, start2 + length - i, i)
                                && helper(start1 + i, start2, length - i)
                    }
                }
            }

        return helper(0, 0, s1.length)
    }
}
