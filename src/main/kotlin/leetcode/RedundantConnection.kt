package leetcode

class RedundantConnection {
    class UnionFind(val size: Int) {
        val arr = IntArray(size) { it }

        fun find(i: Int): Int {
            var i = i
            while (i != arr[i]) i = arr[i]
            return i
        }

        /** returns true if they were already in the same connected component */
        fun union(i: Int, j: Int): Boolean {
            val parentI = find(i)
            val parentJ = find(j)
            arr[parentI] = parentJ
            return (parentI == parentJ)
        }
    }

    fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        val unionFind = UnionFind(edges.size)
        return edges.find { unionFind.union(it[0] - 1, it[1] - 1) }!!
    }
}
