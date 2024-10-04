package leetcode

class WordSearch {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val m = board.size
        val n = board[0].size
        val seen = Array(m) { BooleanArray(n) { false } }

        fun helper(startI: Int, startJ: Int, index: Int): Boolean {
            if (index == word.length) return true
            if (
                startI < 0
                || startI == m
                || startJ < 0
                || startJ == n
                || seen[startI][startJ]
                || board[startI][startJ] != word[index]
            ) return false
            seen[startI][startJ] = true
            if (
                helper(startI + 1, startJ, index + 1)
                || helper(startI - 1, startJ, index + 1)
                || helper(startI, startJ + 1, index + 1)
                || helper(startI, startJ - 1, index + 1)
            ) return true
            seen[startI][startJ] = false
            return false
        }

        var result = false
        for (i in 0..<m) {
            for (j in 0..<n) {
                result = result || helper(i, j, 0)
            }
        }
        return result
    }
}
