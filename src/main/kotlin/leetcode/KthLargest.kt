package leetcode

import java.util.PriorityQueue

class KthLargest(val k: Int, nums: IntArray) {
    val pq = PriorityQueue<Int>()

    init {
        nums.forEach { add(it) }
    }

    fun add(`val`: Int): Int {
        pq.add(`val`)
        if (pq.size > k) pq.poll()
        return pq.peek()
    }
}
