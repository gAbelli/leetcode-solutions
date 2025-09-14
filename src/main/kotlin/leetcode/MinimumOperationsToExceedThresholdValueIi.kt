package leetcode

import kotlin.math.max
import kotlin.math.min
import java.util.*

class MinimumOperationsToExceedThresholdValueIi {
    fun minOperations(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Long>()
        pq.addAll(nums.asSequence().map { it.toLong() })
        var result = 0
        while (pq.peek() < k) {
            result++
            val x = pq.poll()
            val y = pq.poll()
            pq.add(min(x, y) * 2 + max(x, y))
        }
        return result
    }
}
