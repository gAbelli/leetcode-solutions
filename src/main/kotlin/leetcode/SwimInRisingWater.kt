package leetcode

import java.util.PriorityQueue
import kotlin.math.max

class SwimInRisingWater {
    fun swimInWater(grid: Array<IntArray>): Int {
        val n = grid.size
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { grid[it.first][it.second] })
        var t = 0
        val addedToPq = Array(n) { BooleanArray(n) { false } }

        fun add(i: Int, j: Int) {
            if (
                i !in grid.indices
                || j !in grid[0].indices
                || addedToPq[i][j]
            ) return
            pq.add(i to j)
            addedToPq[i][j] = true
        }

        add(0, 0)

        while (pq.isNotEmpty()) {
            val (i, j) = pq.poll()
            t = max(t, grid[i][j])
            if (Pair(i, j) == Pair(n - 1, n - 1)) return t
            add(i - 1, j)
            add(i + 1, j)
            add(i, j - 1)
            add(i, j + 1)
        }

        return t
    }
}
