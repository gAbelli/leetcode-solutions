package leetcode

class MaxAreaOfIsland {
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        fun helper(i: Int, j: Int): Int {
            if (
                i < 0
                || i >= m
                || j < 0
                || j >= n
                || grid[i][j] == 0
            ) return 0
            grid[i][j] = 0
            return 1 + helper(i - 1, j) + helper(i + 1, j) + helper(i, j - 1) + helper(i, j + 1)
        }

        return grid.indices.maxOf { row ->
            grid[row].indices.maxOf { col ->
                helper(row, col)
            }
        }
    }
}
