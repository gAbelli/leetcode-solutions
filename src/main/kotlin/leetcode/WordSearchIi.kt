package leetcode

class WordSearchIi {
    class TrieNode {
        var exists = false
        val children: Array<TrieNode?> = Array('z' - 'a' + 1) { null }

        fun insert(word: String) = insert(this, word)

        fun search(word: String): Boolean = search(this, word)

        companion object {
            tailrec fun insert(self: TrieNode, word: String) {
                if (word.isEmpty()) return
                val index = word.first() - 'a'
                if (self.children[index] == null) self.children[index] = TrieNode()
                if (word.length == 1) self.children[index]!!.exists = true
                insert(self.children[index]!!, word.substring(1))
            }

            tailrec fun search(self: TrieNode, word: String): Boolean {
                if (word.isEmpty()) return false
                val index = word.first() - 'a'
                if (word.length == 1) return self.children[index]?.exists ?: false
                if (self.children[index] == null) return false
                return search(self.children[index]!!, word.substring(1))
            }
        }
    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val root = TrieNode()
        words.forEach { root.insert(it) }
        val m = board.size
        val n = board[0].size
        val seen = Array(m) { BooleanArray(n) { false } }
        val result = mutableListOf<String>()

        fun helper(startI: Int, startJ: Int, root: TrieNode?, curWord: String) {
            if (root == null) return
            if (root.exists) {
                result.add(curWord)
                root.exists = false
            }
            if (
                startI < 0
                || startI == m
                || startJ < 0
                || startJ == n
                || seen[startI][startJ]
            ) return
            seen[startI][startJ] = true
            val trieNode = root.children[board[startI][startJ] - 'a']
            val newWord = curWord + board[startI][startJ]
            helper(startI + 1, startJ, trieNode, newWord)
            helper(startI - 1, startJ, trieNode, newWord)
            helper(startI, startJ + 1, trieNode, newWord)
            helper(startI, startJ - 1, trieNode, newWord)
            seen[startI][startJ] = false
        }

        for (i in 0..<m) {
            for (j in 0..<n) {
                helper(i, j, root, "")
            }
        }
        return result
    }
}
