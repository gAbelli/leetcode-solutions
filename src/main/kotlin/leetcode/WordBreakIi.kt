package leetcode

class WordBreakIi {
    class TrieNode {
        val children = Array<TrieNode?>('z' - 'a' + 1) { null }
        var exists: Boolean = false
    }

    fun wordBreak(s: String, wordDict: List<String>): List<String> {
        val root = TrieNode()

        fun insert(word: String) {
            var cur = root
            for (c in word) {
                if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = TrieNode()
                cur = cur.children[c - 'a']!!
            }
            cur.exists = true
        }

        wordDict.forEach(::insert)

        val lengthsStartingAt = Array(s.length) { i ->
            buildList {
                var cur: TrieNode? = root
                for (j in i..<s.length) {
                    cur = cur!!.children[s[j] - 'a']
                    if (cur == null) break
                    if (cur!!.exists) add(j - i + 1)
                }
            }
        }

        val cur = mutableListOf<String>()
        val result = mutableListOf<String>()

        fun helper(i: Int) {
            if (i == s.length) {
                result.add(cur.joinToString(" "))
                return
            }
            for (length in lengthsStartingAt[i]) {
                cur.add(s.substring(i..<i + length))
                helper(i + length)
                cur.removeLast()
            }
        }

        helper(0)
        return result
    }
}
