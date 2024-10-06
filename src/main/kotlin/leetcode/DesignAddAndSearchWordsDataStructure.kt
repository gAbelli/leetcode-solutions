package leetcode

class DesignAddAndSearchWordsDataStructure {
    class TrieNode {
        var exists = false
        val children: Array<TrieNode?> = Array('z' - 'a' + 1) { null }

        fun insert(word: String) = insert(this, word)

        fun search(word: String): Boolean {
            if (word.isEmpty()) return false
            if (word.first() == '.') return CharRange('a', 'z').fold(false) { acc, c ->
                acc || search(c + word.substring(1))
            }
            val index = word.first() - 'a'
            if (word.length == 1) return children[index]?.exists ?: false
            return children[index]?.search(word.substring(1)) ?: false
        }

        companion object {
            tailrec fun insert(self: TrieNode, word: String) {
                if (word.isEmpty()) return
                val index = word.first() - 'a'
                if (self.children[index] == null) self.children[index] = TrieNode()
                if (word.length == 1) self.children[index]!!.exists = true
                insert(self.children[index]!!, word.substring(1))
            }
        }
    }

    val root = TrieNode()

    fun addWord(word: String) = root.insert(word)

    fun search(word: String): Boolean = root.search(word)
}
