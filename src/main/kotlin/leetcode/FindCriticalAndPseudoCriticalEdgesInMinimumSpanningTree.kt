package leetcode

class FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree {
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

        fun isConnected(): Boolean {
            val root = find(parent[0])
            return parent.all { find(it) == root }
        }
    }

    fun findCriticalAndPseudoCriticalEdges(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val edges = edges.withIndex().sortedBy { it.value[2] }

        val mstCost = run {
            var cost = 0
            val unionFind = UnionFind(n)
            for ((_, edge) in edges) if (unionFind.find(edge[0]) != unionFind.find(edge[1])) {
                unionFind.union(edge[0], edge[1])
                cost += edge[2]
            }
            cost
        }

        val criticalEdges = edges
            .asSequence()
            .filter { (index, _) ->
                var cost = 0
                val unionFind = UnionFind(n)
                for ((i, edge) in edges) if (i != index && unionFind.find(edge[0]) != unionFind.find(edge[1])) {
                    unionFind.union(edge[0], edge[1])
                    cost += edge[2]
                }
                !unionFind.isConnected() || cost != mstCost
            }
            .map { it.index }
            .toList()
        val criticalEdgesSet = criticalEdges.toSet()

        val pseudoCriticalEdges = edges
            .asSequence()
            .filterNot { criticalEdgesSet.contains(it.index) }
            .filter { (_, candidate) ->
                var cost = candidate[2]
                val unionFind = UnionFind(n)
                unionFind.union(candidate[0], candidate[1])
                for ((_, edge) in edges) if (unionFind.find(edge[0]) != unionFind.find(edge[1])) {
                    unionFind.union(edge[0], edge[1])
                    cost += edge[2]
                }
                cost == mstCost
            }
            .map { it.index }
            .toList()

        return listOf(criticalEdges, pseudoCriticalEdges)
    }
}
