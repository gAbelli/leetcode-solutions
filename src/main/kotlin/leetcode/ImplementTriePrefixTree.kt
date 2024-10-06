package leetcode

class ImplementTriePrefixTree {
    class TrieNode(var exists: Boolean) {
        val children: Array<TrieNode?> = Array('z' - 'a' + 1) { null }
    }

    val root = TrieNode(false)

    fun insert(word: String) {
        var cur: TrieNode? = root
        for (c in word) {
            if (cur!!.children[c - 'a'] == null) cur.children[c - 'a'] = TrieNode(false)
            cur = cur.children[c - 'a']
        }
        cur!!.exists = true
    }

    fun search(word: String): Boolean {
        var cur: TrieNode? = root
        for (c in word) {
            cur = cur!!.children[c - 'a']
            if (cur == null) return false
        }
        return cur!!.exists
    }

    fun startsWith(prefix: String): Boolean {
        var cur: TrieNode? = root
        for (c in prefix) {
            cur = cur!!.children[c - 'a']
            if (cur == null) return false
        }
        return true
    }
}
