package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class TopKFrequentElementsTest {
    @Test
    fun testTopKFrequent() {
        val nums = intArrayOf(1, 1, 1, 2, 2, 3)
        val k = 2
        assertEquals(listOf(1, 2), TopKFrequentElements().topKFrequent(nums, k).toList().sorted())
    }

    @Test
    fun testPriorityQueue() {
        val pq = PriorityQueue<Int>()
        pq.offer(3)
        pq.offer(2)
        pq.offer(1)
        assertEquals(1, pq.peek())
    }
}
