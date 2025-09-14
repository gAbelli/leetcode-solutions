package leetcode

class LargestColorValueInADirectedGraph {
    fun largestPathValue(colors: String, edges: Array<IntArray>): Int {
        val n = colors.length
        val successors = Array(n) { mutableListOf<Int>() }
        for (edge in edges) successors[edge[0]].add(edge[1])

        val hasCycles = run {
            val seen = BooleanArray(n) { false }
            val inThisPath = BooleanArray(n) { false }

            /** Returns true if it finds a cycle */
            fun dfs(root: Int): Boolean {
                if (inThisPath[root]) return true
                if (seen[root]) return false
                seen[root] = true
                inThisPath[root] = true
                for (successor in successors[root]) if (dfs(successor)) return true
                inThisPath[root] = false
                return false
            }

            (0..<n).any { dfs(it) }
        }
        if (hasCycles) return -1

        val memo = mutableMapOf<Pair<Int, Char>, Int>()
        fun helper(node: Int, color: Char): Int = memo.getOrPut(node to color) {
            (successors[node].maxOfOrNull { helper(it, color) } ?: 0) + if (colors[node] == color) 1 else 0
        }

        return (0..<n).maxOf { helper(it, colors[it]) }
    }

    fun largestPathValueRecursionDepth(colors: String, edges: Array<IntArray>): Int {
        val n = colors.length
        val successors = Array(n) { mutableListOf<Int>() }
        for (edge in edges) successors[edge[0]].add(edge[1])

        val memo = mutableMapOf<Pair<Int, Char>, Int>()
        fun helper(node: Int, color: Char, recursionDepth: Int): Int? =
            if (recursionDepth > n) null
            else memo.getOrPut(node to color) {
                (successors[node].maxOfOrNull { helper(it, color, recursionDepth + 1) ?: return null } ?: 0) +
                        if (colors[node] == color) 1 else 0
            }

        return (0..<n).maxOf { helper(it, colors[it], 0) ?: return -1 }
    }
}
