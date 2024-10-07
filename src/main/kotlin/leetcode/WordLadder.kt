package leetcode

class WordLadder {
    // There is a more optimized solution that involves creating a hash map that contains all words
    // from wordList, but with one character replaced by '*'.
    // This lowers the complexity of the first step from O(n^2 * l) to O(n * l).
    // This is only relevant if the resulting graph turns out to be sparse.
    fun edgeExists(w1: String, w2: String): Boolean {
        var diffFound = false
        for ((c1, c2) in w1.zip(w2)) {
            if (c1 != c2) {
                if (diffFound) return false
                diffFound = true
            }
        }
        return true
    }

    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val wordLength = wordList.first().length
        for (word in wordList) if (word.length != wordLength) return 0
        val endWordIndex = wordList.indexOf(endWord)
        if (endWordIndex == -1) return 0

        val neighbors = wordList.map { mutableListOf<Int>() }
        for (i in wordList.indices) {
            for (j in i + 1..wordList.indices.last) {
                if (edgeExists(wordList[i], wordList[j])) {
                    neighbors[i].add(j)
                    neighbors[j].add(i)
                }
            }
        }

        val queue = ArrayDeque<Int>()
        queue.addAll(wordList.indices.filter { edgeExists(beginWord, wordList[it]) })
        val seen = BooleanArray(wordList.size) { false }
        queue.forEach { seen[it] = true }
        var result = 0

        while (queue.isNotEmpty()) {
            result += 1
            val n = queue.size
            for (k in 0..<n) {
                val i = queue.removeFirst()
                if (i == endWordIndex) return result + 1
                neighbors[i].forEach {
                    if (!seen[it]) {
                        seen[it] = true
                        queue.add(it)
                    }
                }
            }
        }

        return 0
    }
}
