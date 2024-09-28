package leetcode

import kotlin.math.max

class LongestConsecutiveSequence {
    fun longestConsecutive(nums: IntArray): Int {
        val numsSet = nums.toSet()
        val longestChain = mutableMapOf<Int, Int>()

        fun findLongestChainStartingAt(n: Int): Int {
            if (!numsSet.contains(n)) return 0
            if (n in longestChain.keys) return longestChain[n]!!
            val result = findLongestChainStartingAt(n + 1) + 1
            longestChain[n] = result
            return result
        }

        var result = 0

        for (num in numsSet) {
            result = max(result, findLongestChainStartingAt(num))
        }

        return result
    }
}
