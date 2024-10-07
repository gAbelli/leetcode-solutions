package leetcode

class SurroundedRegions {
    fun solve(board: Array<CharArray>): Unit {
        val m = board.size
        val n = board[0].size

        fun helper(i: Int, j: Int) {
            if (
                i < 0
                || i >= m
                || j < 0
                || j >= n
                || board[i][j] != 'O'
            ) return
            board[i][j] = 'E'
            helper(i - 1, j)
            helper(i + 1, j)
            helper(i, j - 1)
            helper(i, j + 1)
        }

        for (j in 0..<n) helper(0, j)
        for (j in 0..<n) helper(m - 1, j)
        for (i in 0..<m) helper(i, 0)
        for (i in 0..<m) helper(i, n - 1)

        for (i in 0..<m) for (j in 0..<n) if (board[i][j] == 'O') board[i][j] = 'X'
        for (i in 0..<m) for (j in 0..<n) if (board[i][j] == 'E') board[i][j] = 'O'
    }
}
