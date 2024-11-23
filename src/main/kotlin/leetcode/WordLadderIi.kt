package leetcode

class WordLadderIi {
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

    // As in Word Ladder I, there is a more efficient solution
    fun findLadders(beginWord: String, endWord: String, wordList: List<String>): List<List<String>> {
        val endWordIndex = wordList.indexOf(endWord)
        if (endWordIndex == -1) return listOf()

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
        val startingIndices = wordList.indices.asSequence().filter { edgeExists(beginWord, wordList[it]) }.toSet()
        queue.addAll(startingIndices)
        val seen = BooleanArray(wordList.size) { false }
        queue.forEach { seen[it] = true }
        var found = false
        val predecessors = Array(wordList.size) { mutableSetOf<Int>() }
        var distance = 0

        while (queue.isNotEmpty() && !found) {
            val seenInThisIteration = BooleanArray(wordList.size) { false }
            distance += 1
            val n = queue.size
            for (k in 0..<n) {
                val i = queue.removeFirst()
                if (i == endWordIndex) found = true
                neighbors[i].forEach {
                    if (!seen[it] || seenInThisIteration[it]) predecessors[it].add(i)
                    if (!seen[it]) {
                        seen[it] = true
                        seenInThisIteration[it] = true
                        queue.add(it)
                    }
                }
            }
        }
        if (!found) return listOf()

        val result = mutableListOf<List<String>>()
        val acc = ArrayDeque<String>()
        fun buildStrings(end: Int, distance: Int) {
            acc.addFirst(wordList[end])
            if (distance == 1) {
                if (startingIndices.contains(end)) {
                    acc.addFirst(beginWord)
                    result.add(acc.toList())
                    acc.removeFirst()
                }
            } else for (predecessor in predecessors[end]) buildStrings(predecessor, distance - 1)
            acc.removeFirst()
        }
        buildStrings(endWordIndex, distance)

        return result
    }
}
