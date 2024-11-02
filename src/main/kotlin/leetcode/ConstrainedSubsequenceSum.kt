package leetcode

import kotlin.math.max

class ConstrainedSubsequenceSum {
    // The formula would be
    // dp[j] = nums[j] + max(0, dp[j-1], ..., dp[j-k])
    // but we can avoid the O(n*k) complexity with the same trick used in SlidingWindowMaximum.
    fun constrainedSubsetSum(nums: IntArray, k: Int): Int {
        val queue = ArrayDeque<IndexedValue<Int>>()
        var result = Int.MIN_VALUE
        for (i in nums.indices) {
            while (queue.isNotEmpty() && queue.first().index < i - k) queue.removeFirst()
            val newElement =
                if (queue.isEmpty() || queue.first().value < 0) nums[i]
                else nums[i] + queue.first().value
            while (queue.isNotEmpty() && queue.last().value <= newElement) queue.removeLast()
            queue.add(IndexedValue(i, newElement))
            result = max(result, newElement)
        }
        return result
    }
}
