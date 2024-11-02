package leetcode

class ArithmeticSlicesIiSubsequence {
    // This is usually quadratic, but it can happen that positions[i] is a list of size O(n) and
    // the complexity becomes cubic. This happens for example if all elements in nums are equal.
    // See on LeetCode for quadratic solutions.
    fun numberOfArithmeticSlices(nums: IntArray): Int {
        val nums = LongArray(nums.size) { nums[it].toLong() }
        val positions = nums.withIndex().groupBy(
            keySelector = { it.value },
            valueTransform = { it.index }
        )
        val memo = mutableMapOf<Pair<Int, Long>, Int>()
        fun helper(start: Int, d: Long): Int = memo.getOrPut(Pair(start, d)) {
            positions
                .getOrDefault(nums[start] + d, emptyList())
                .asSequence()
                .filter { it > start }
                .sumOf { 1 + helper(it, d) }
        }
        return nums.indices.sumOf { i -> (i + 1..<nums.size).sumOf { j -> helper(j, nums[j] - nums[i]) } }
    }
}
