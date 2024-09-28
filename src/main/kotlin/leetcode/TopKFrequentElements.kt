package leetcode

import java.util.PriorityQueue

class TopKFrequentElements {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val counter = nums.asSequence().groupingBy { it }.eachCount()
        val pq = PriorityQueue<Pair<Int, Int>> { p1, p2 ->
            p1.second - p2.second
        }
        for (entry in counter) {
            pq.offer(entry.key to entry.value)
            if (pq.size > k) {
                pq.poll()
            }
        }
        return pq.asSequence().map { it.first }.toList().toIntArray()
    }
}
