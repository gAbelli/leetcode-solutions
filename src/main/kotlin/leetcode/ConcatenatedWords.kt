package leetcode

class ConcatenatedWords {
    class TrieNode {
        val children = Array<TrieNode?>('z' - 'a' + 1) { null }
        var exists: Boolean = false
    }

    fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
        val root = TrieNode()

        fun insert(word: String) {
            var cur = root
            for (c in word) {
                if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = TrieNode()
                cur = cur.children[c - 'a']!!
            }
            cur.exists = true
        }

        fun count(word: String, start: Int, memo: MutableMap<Int, Int>): Int = memo.getOrPut(start) {
            if (start == word.length) return@getOrPut 0
            var cur = root

            for (i in start..<word.length) {
                cur = cur.children[word[i] - 'a'] ?: return@getOrPut -1
                if (cur.exists) count(word, i + 1, memo).also { if (it != -1) return@getOrPut it + 1 }
            }

            -1
        }

        words.forEach { insert(it) }
        return words.filter { count(it, 0, mutableMapOf()) != 1 }
    }
}
