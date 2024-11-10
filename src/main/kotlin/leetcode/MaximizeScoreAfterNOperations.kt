package leetcode

class MaximizeScoreAfterNOperations {
    private tailrec fun gcd(a: Int, b: Int): Int {
        if (a < b) return gcd(b, a)
        if (b == 0) return a
        return gcd(b, a % b)
    }

    fun maxScore(nums: IntArray): Int {
        val n = nums.size / 2
        val gcdTable = Array(2 * n) { i -> IntArray(2 * n) { j -> gcd(nums[i], nums[j]) } }
        val memo = mutableMapOf<Pair<Int, Int>, Int>()

        fun helper(k: Int, bitMask: Int): Int =
            if (k == n + 1) 0
            else memo.getOrPut(k to bitMask) {
                (0..<2 * n)
                    .asSequence()
                    .filter { bitMask and (1 shl it) == 0 }
                    .flatMap { i ->
                        (i + 1..<2 * n).asSequence().filter { bitMask and (1 shl it) == 0 }.map { i to it }
                    }
                    .maxOf { (i, j) -> k * gcdTable[i][j] + helper(k + 1, bitMask or (1 shl i) or (1 shl j)) }
            }

        return helper(1, 0)
    }
}
