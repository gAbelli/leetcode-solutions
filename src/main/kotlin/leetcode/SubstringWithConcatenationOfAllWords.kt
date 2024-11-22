package leetcode

class SubstringWithConcatenationOfAllWords {
    class TrieNode {
        val children = Array<TrieNode?>('z' - 'a' + 1) { null }
        var wordIndices: MutableList<Int> = mutableListOf()
    }

    // Of course we can optimize this using lengthOfEachWord distinct sliding windows
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val root = TrieNode()
        val lengthOfEachWord = words[0].length

        fun insert(wordIndex: Int) {
            val word = words[wordIndex]
            var cur = root
            for (c in word) {
                if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = TrieNode()
                cur = cur.children[c - 'a']!!
            }
            cur.wordIndices.add(wordIndex)
        }

        words.indices.forEach(::insert)
        val wordIndices = Array<MutableList<Int>>(s.length) { mutableListOf() }
        for (i in 0..(s.length - lengthOfEachWord)) {
            var cur: TrieNode? = root
            for (j in i..<i + lengthOfEachWord) {
                cur = cur!!.children[s[j] - 'a']
                if (cur == null) break
            }
            if (cur != null) wordIndices[i] = cur!!.wordIndices
        }

        val result = mutableListOf<Int>()
        val found = BooleanArray(words.size) { false }
        for (i in 0..(s.length - lengthOfEachWord * words.size)) {
            val toAddBack = mutableListOf<Pair<Int, Int>>()
            var index = i
            var count = 0
            while (index < s.length && count < words.size) {
                val wordIndex = wordIndices[index].removeLastOrNull() ?: break
                toAddBack.add(index to wordIndex)
                found[wordIndex] = true
                count += 1
                index += lengthOfEachWord
            }
            for ((indexToAddBack, wordIndex) in toAddBack) wordIndices[indexToAddBack].add(wordIndex)
            if (count == words.size) result.add(i)
            for (j in found.indices) found[j] = false
        }
        return result
    }
}
