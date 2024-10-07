package leetcode

class RottingOranges {
    fun orangesRotting(grid: Array<IntArray>): Int {
        val freshOrangesCoordinates = mutableSetOf<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Int, Int>>()
        grid.indices.forEach { i ->
            grid[i].indices.forEach { j ->
                if (grid[i][j] == 1) freshOrangesCoordinates.add(i to j)
                if (grid[i][j] == 2) queue.add(i to j)
            }
        }

        fun process(i: Int, j: Int) {
            if (i in grid.indices && j in grid[0].indices && grid[i][j] == 1) {
                grid[i][j] = 2
                queue.add(i to j)
                freshOrangesCoordinates.remove(i to j)
            }
        }

        var result = 0
        while (queue.isNotEmpty() && freshOrangesCoordinates.isNotEmpty()) {
            result += 1
            val n = queue.size
            for (k in 0..<n) {
                val (i, j) = queue.removeFirst()
                process(i - 1, j)
                process(i + 1, j)
                process(i, j - 1)
                process(i, j + 1)
            }
        }

        return if (freshOrangesCoordinates.isEmpty()) result else -1
    }
}
