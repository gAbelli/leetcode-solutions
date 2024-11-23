package leetcode

class MinimumCostToCutAStick {
    fun minCost(n: Int, cuts: IntArray): Int {
        cuts.sort()
        val memo = mutableMapOf<Pair<Int, Int>, Int>()
        fun helper(i: Int, j: Int): Int =
            if (i == j) 0
            else memo.getOrPut(i to j) {
                (i..<j).minOf { k ->
                    cuts.getOrElse(j) { n } - cuts.getOrElse(i - 1) { 0 } + helper(i, k) + helper(k + 1, j)
                }
            }

        return helper(0, cuts.size)
    }

    companion object {
        val cache = mutableMapOf<Pair<Int, List<Int>>, Int>()
    }

    fun minCostMemo(n: Int, cuts: IntArray): Int {
        cuts.sort()

        fun helper(n: Int, cuts: List<Int>): Int =
            if (cuts.isEmpty()) 0
            else cache.getOrPut(n to cuts) {
                n + cuts.withIndex().minOf { (i, leftSize) ->
                    helper(leftSize, cuts.subList(0, i)) +
                            helper(n - leftSize, cuts.subList(i + 1, cuts.size).map { it - leftSize })
                }
            }

        return helper(n, cuts.toList())
    }
}
