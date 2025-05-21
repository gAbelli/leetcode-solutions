package leetcode

import kotlin.math.max

class GridGame {
    fun LongArray.safeGet(i: Int): Long =
        if (i in indices) get(i)
        else 0L

    // Objective: find min_i{ max(sum_{j<i}{grid[1][j]}, sum_{j>i}{grid[0][j]}) }
    fun gridGame(grid: Array<IntArray>): Long {
        val n = grid[0].size
        // Very unfortunate that we cannot do this in place because we need a LongArray instead of an IntArray
        val partialSums = Array(2) { LongArray(n) }
        for (i in 0..<n) partialSums[1][i] = grid[1][i] + partialSums[1].safeGet(i - 1)
        for (i in n - 1 downTo 0) partialSums[0][i] = grid[0][i] + partialSums[0].safeGet(i + 1)
        return (0..<n).minOf { max(partialSums[1].safeGet(it - 1), partialSums[0].safeGet(it + 1)) }
    }
}
