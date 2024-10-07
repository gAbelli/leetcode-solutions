package leetcode

class NumberOfIslands {
    fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid[0].size

        fun helper(i: Int, j: Int): Boolean {
            if (
                i < 0
                || i >= m
                || j < 0
                || j >= n
                || grid[i][j] == '0'
            ) return false
            grid[i][j] = '0'
            helper(i - 1, j)
            helper(i + 1, j)
            helper(i, j - 1)
            helper(i, j + 1)

            return true
        }

        return grid.indices.sumOf { row ->
            grid[row].indices.count { col ->
                helper(row, col)
            }
        }
    }
}
