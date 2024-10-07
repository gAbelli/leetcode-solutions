package leetcode

import java.util.PriorityQueue
import kotlin.math.max

class SwimInRisingWater {
    fun swimInWater(grid: Array<IntArray>): Int {
        val n = grid.size
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { grid[it.first][it.second] })
        pq.add(0 to 0)
        var t = 0
        val reached = Array(n) { BooleanArray(n) { false } }
        val addedToPq = Array(n) { BooleanArray(n) { false } }
        addedToPq[0][0] = true

        fun helper(i: Int, j: Int, t: Int) {
            if (
                i !in grid.indices
                || j !in grid[0].indices
                || reached[i][j]
                || addedToPq[i][j]
            ) return
            pq.add(i to j)
            addedToPq[i][j] = true
            if (t >= grid[i][j]) {
                reached[i][j] = true
                helper(i - 1, j, t)
                helper(i + 1, j, t)
                helper(i, j - 1, t)
                helper(i, j + 1, t)
            }
        }

        while (pq.isNotEmpty()) {
            val (i, j) = pq.poll()
            t = max(t, grid[i][j])
            reached[i][j] = true
            if (Pair(i, j) == Pair(n - 1, n - 1)) return t
            helper(i - 1, j, t)
            helper(i + 1, j, t)
            helper(i, j - 1, t)
            helper(i, j + 1, t)
        }

        return t
    }
}
