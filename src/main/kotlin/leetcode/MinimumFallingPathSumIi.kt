package leetcode

class MinimumFallingPathSumIi {
    fun minFallingPathSum(grid: Array<IntArray>): Int {
        val n = grid.size
        if (n == 1) return grid[0][0]

        val prev = IntArray(n) { 0 }
        val cur = IntArray(n) { 0 }

        for (row in n - 1 downTo 0) {
            for (col in 0..<n) cur[col] = grid[row][col] + prev[col]
            var minimum = Int.MAX_VALUE
            var secondMinimum = Int.MAX_VALUE
            for (col in 0..<n) {
                if (cur[col] < minimum) {
                    secondMinimum = minimum
                    minimum = cur[col]
                } else if (cur[col] < secondMinimum) {
                    secondMinimum = cur[col]
                }
            }
            for (col in 0..<n) if (cur[col] == minimum) cur[col] = secondMinimum else cur[col] = minimum
            for (col in 0..<n) prev[col] = cur[col]
        }

        return cur.min()
    }
}
