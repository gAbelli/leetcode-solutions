package leetcode

class PacificAtlanticWaterFlow {
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val m = heights.size
        val n = heights[0].size
        val reachableFromPacific = mutableSetOf<Pair<Int, Int>>()
        val reachableFromAtlantic = mutableSetOf<Pair<Int, Int>>()

        val seen = Array(m) { BooleanArray(n) { false } }

        fun dfs(i: Int, j: Int, prevHeight: Int, dst: MutableSet<Pair<Int, Int>>) {
            if (
                i !in heights.indices
                || j !in heights[0].indices
                || seen[i][j]
                || heights[i][j] < prevHeight
            ) return
            seen[i][j] = true
            dst.add(i to j)
            dfs(i - 1, j, heights[i][j], dst)
            dfs(i + 1, j, heights[i][j], dst)
            dfs(i, j - 1, heights[i][j], dst)
            dfs(i, j + 1, heights[i][j], dst)
        }

        for (j in 0..<n) dfs(0, j, -1, reachableFromPacific)
        for (i in 0..<m) dfs(i, 0, -1, reachableFromPacific)

        for (i in heights.indices) for (j in heights[0].indices) seen[i][j] = false

        for (j in 0..<n) dfs(m - 1, j, -1, reachableFromAtlantic)
        for (i in 0..<m) dfs(i, n - 1, -1, reachableFromAtlantic)

        return reachableFromPacific.intersect(reachableFromAtlantic).asSequence().map { it.toList() }.toList()
    }
}
