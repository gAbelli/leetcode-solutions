package leetcode

import kotlin.math.abs

class MinCostToConnectAllPoints {
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

        /** returns true if they were already in the same connected component */
        fun union(i: Int, j: Int): Boolean {
            val parentI = find(i)
            val parentJ = find(j)
            parent[parentI] = parentJ
            return (parentI == parentJ)
        }
    }

    private fun dist(p1: IntArray, p2: IntArray): Int =
        abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])

    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val sortedEdges: List<Triple</* i */ Int, /* j */ Int, /* dist */ Int>> = points.indices.flatMap { i ->
            (i + 1..points.indices.last).map { j ->
                Triple(i, j, dist(points[i], points[j]))
            }
        }.sortedBy { it.third }
        val unionFind = UnionFind(points.size)
        var cost = 0
        var edgesCount = 0

        for ((i, j, d) in sortedEdges) {
            if (edgesCount == points.size - 1) break
            if (!unionFind.union(i, j)) {
                cost += d
                edgesCount += 1
            }
        }

        return cost
    }
}
