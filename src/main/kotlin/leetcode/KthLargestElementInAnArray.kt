package leetcode

import java.util.PriorityQueue

class KthLargestElementInAnArray {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Int>()
        nums.forEach {
            pq.add(it)
            if (pq.size > k) pq.poll()
        }
        return pq.poll()
    }
}
