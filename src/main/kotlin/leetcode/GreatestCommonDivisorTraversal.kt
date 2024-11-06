package leetcode

class GreatestCommonDivisorTraversal {
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

    companion object {
        fun getPrimesBelow(n: Int): List<Int> {
            val arr = BooleanArray(n + 1) { true }.apply {
                set(0, false)
                set(1, false)
            }
            for (i in 2..n) if (arr[i]) {
                var j = i * 2
                while (j <= n) {
                    arr[j] = false
                    j += i
                }
            }

            return arr.indices.filter { arr[it] }
        }

        val allPrimes = getPrimesBelow(100_000)

        val primeDivisorsOf = Array(100_001) { k ->
            if (k == 0) return@Array listOf()
            var k = k
            buildList {
                for (prime in allPrimes) {
                    if (prime > k) break
                    if (k % prime == 0) {
                        add(prime)
                        while (k % prime == 0) k /= prime
                    }
                }
            }
        }
    }

    fun canTraverseAllPairs(nums: IntArray): Boolean {
        if (nums.size == 1) return true
        if (nums.contains(1)) return false
        val nums = nums.map { primeDivisorsOf[it] }
        val relevantPrimes = nums.asSequence().flatten().distinct().toList()
        val pos = relevantPrimes.asSequence().mapIndexed { index, value -> value to index }.toMap()
        val unionFind = UnionFind(relevantPrimes.size)
        for (primeFactors in nums) primeFactors.windowed(2).forEach {
            unionFind.union(pos[it[0]]!!, pos[it[1]]!!)
        }
        val root = unionFind.find(0)
        return relevantPrimes.indices.all { unionFind.find(it) == root }
    }
}
