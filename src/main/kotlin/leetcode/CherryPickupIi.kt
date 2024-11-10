package leetcode

import kotlin.math.max
import kotlin.math.min

class CherryPickupIi {
    fun (Array<IntArray>).safeGet(i: Int, j: Int): Int =
        if (i in indices && j in get(i).indices) get(i)[j]
        else 0

    fun cherryPickup(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid.first().size
        val prev = Array(n) { IntArray(n) { 0 } }.apply {
            get(0)[n - 1] = (grid[0][0] + grid[0][n - 1])
        }
        val cur = Array(n) { Array(n) { 0 } }
        for (row in 1..<m) {
            for (col1 in 0..<min(n, row + 1)) for (col2 in max(0, n - row - 1)..<n) cur[col1][col2] = (
                    if (col1 == col2) grid[row][col1]
                    else grid[row][col1] + grid[row][col2]
                    ) + maxOf(
                prev.safeGet(col1 - 1, col2 - 1),
                prev.safeGet(col1, col2 - 1),
                prev.safeGet(col1 + 1, col2 - 1),
                prev.safeGet(col1 - 1, col2),
                prev.safeGet(col1, col2),
                prev.safeGet(col1 + 1, col2),
                prev.safeGet(col1 - 1, col2 + 1),
                prev.safeGet(col1, col2 + 1),
                prev.safeGet(col1 + 1, col2 + 1)
            )

            for (i in 0..<n) for (j in 0..<n) prev[i][j] = cur[i][j]
        }
        return cur.maxOf { it.max() }
    }
}
