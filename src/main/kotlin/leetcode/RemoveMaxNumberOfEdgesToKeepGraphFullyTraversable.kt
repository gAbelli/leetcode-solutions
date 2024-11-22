package leetcode

class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {
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

        /** Returns true if they were already in the same connected component */
        fun union(i: Int, j: Int): Boolean {
            val rootI = find(i)
            val rootJ = find(j)
            parent[rootI] = find(rootJ)
            return rootI == rootJ
        }
    }

    // Claim 1: in any optimal configuration, none of the two graphs (the one that
    // Alice sees and the one that Bob sees) contain any cycles.
    // Proof: suppose by contradiction one of the two graphs has a cycle.
    // WLOG assume it's Alice's graph.
    // * If the cycle only consists of shared edges, just remove one
    // * If there is at least one exclusive edge, just remove that one
    //
    // From here we see that the goal is simply to maximize the number of shared edges.
    // Claim 2: this can be done greedily, i.e. keep picking shared edges unless they
    // generate a cycle.
    // Proof: if there is a cycle, you can remove any of the edges in it. This breaks
    // the cycles and keep connectivity
    fun maxNumEdgesToRemove(n: Int, edges: Array<IntArray>): Int {
        val aliceUnionFind = UnionFind(n)
        val bobUnionFind = UnionFind(n)
        val sharedUnionFind = UnionFind(n)
        var aliceConnectedComponents = n
        var bobConnectedComponents = n
        var sharedEdgesCount = 0
        for (edge in edges) {
            val type = edge[0]
            val u = edge[1] - 1
            val v = edge[2] - 1
            if (type == 1 || type == 3) if (!aliceUnionFind.union(u, v)) aliceConnectedComponents -= 1
            if (type == 2 || type == 3) if (!bobUnionFind.union(u, v)) bobConnectedComponents -= 1
            if (type == 3) if (!sharedUnionFind.union(u, v)) sharedEdgesCount += 1
        }
        return if (aliceConnectedComponents != 1 || bobConnectedComponents != 1) -1
        else edges.size - (2 * n - 2 - sharedEdgesCount)
    }
}
