package leetcode

class NumberOfGoodPaths {
    class UnionFind(val size: Int) {
        val parent = IntArray(size) { it }

        fun find(i: Int): Int {
            var k = i
            while (k != parent[k]) {
                parent[k] = parent[parent[k]]
                k = parent[k]
            }
            parent[i] = k
            return k
        }

        fun union(i: Int, j: Int) {
            parent[find(i)] = find(j)
        }
    }

    // Pretty crazy solution, I didn't find it myself.
    // But it's worth remembering for future problems.
    fun numberOfGoodPaths(vals: IntArray, edges: Array<IntArray>): Int {
        val n = vals.size
        val neighbors = Array<MutableList<Int>>(n) { mutableListOf() }
        for (edge in edges) {
            neighbors[edge[0]].add(edge[1])
            neighbors[edge[1]].add(edge[0])
        }
        val nodesByValue = sortedMapOf<Int, MutableList<Int>>()
        for (i in vals.indices) nodesByValue.getOrPut(vals[i]) { mutableListOf() }.add(i)
        val unionFind = UnionFind(n)
        var result = n
        for ((value, nodes) in nodesByValue) {
            for (node in nodes) for (neighbor in neighbors[node]) if (vals[neighbor] <= value)
                unionFind.union(node, neighbor)
            val relevantNodesByParent = mutableMapOf<Int, Int>()
            for (node in nodes) {
                val root = unionFind.find(node)
                relevantNodesByParent[root] = relevantNodesByParent.getOrDefault(root, 0) + 1
            }
            for ((_, count) in relevantNodesByParent) result += (count * (count - 1)) / 2
        }
        return result
    }

    fun numberOfGoodPathsQuadratic(vals: IntArray, edges: Array<IntArray>): Int {
        val n = vals.size
        val neighbors = Array<MutableList<Int>>(n) { mutableListOf() }
        for (edge in edges) {
            neighbors[edge[0]].add(edge[1])
            neighbors[edge[1]].add(edge[0])
        }
        var result = 0

        fun dfs(node: Int, prev: Int?, startValue: Int) {
            if (vals[node] > startValue) return
            if (vals[node] == startValue) result += 1
            for (neighbor in neighbors[node]) if (neighbor != prev) dfs(neighbor, node, startValue)
        }

        for (i in 0..<n) dfs(i, null, vals[i])
        return (result + n) / 2
    }
}
