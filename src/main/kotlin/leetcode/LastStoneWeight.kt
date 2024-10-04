package leetcode

import java.util.PriorityQueue

class LastStoneWeight {
    fun lastStoneWeight(stones: IntArray): Int {
        val pq = PriorityQueue<Int> { a, b -> -a.compareTo(b) }
        pq.addAll(stones.asSequence())
        while (pq.size > 1) {
            val heavier = pq.poll()
            val lighter = pq.poll()
            if (heavier != lighter) pq.add(heavier - lighter)
        }
        return if (pq.isEmpty()) 0 else pq.poll()
    }
}
