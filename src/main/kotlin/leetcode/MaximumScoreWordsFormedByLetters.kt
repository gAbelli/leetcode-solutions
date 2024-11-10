package leetcode

import kotlin.math.max

class MaximumScoreWordsFormedByLetters {
    private fun getCounts(letters: Iterable<Char>): MutableList<Int> = mutableListOf<Int>().apply {
        repeat('z' - 'a' + 1) { add(0) }
        for (letter in letters) set(letter - 'a', get(letter - 'a') + 1)
    }

    fun maxScoreWords(words: Array<String>, letters: CharArray, score: IntArray): Int {
        val letterCounts = getCounts(letters.asIterable())
        val letterCountsInWords = words.map { word -> getCounts(word.asIterable()) }
        val scoreOfWords = words.map { it.sumOf { score[it - 'a'] } }

        fun helper(i: Int): Int {
            if (i == words.size) return 0
            var result = helper(i + 1)
            if ((0..'z' - 'a').all { letterCounts[it] >= letterCountsInWords[i][it] }) {
                (0..'z' - 'a').forEach { letterCounts[it] -= letterCountsInWords[i][it] }
                result = max(result, helper(i + 1) + scoreOfWords[i])
                (0..'z' - 'a').forEach { letterCounts[it] += letterCountsInWords[i][it] }
            }
            return result
        }

        return helper(0)
    }
}
