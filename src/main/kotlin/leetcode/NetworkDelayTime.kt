package leetcode

import java.util.PriorityQueue
import kotlin.math.min

class NetworkDelayTime {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val adjacent = (1..n).associateWith { mutableListOf<Pair</* node */ Int, /* cost */ Int>>() }
        times.forEach { (src, dst, cost) ->
            adjacent[src]!!.add(dst to cost)
        }
        val remainingNodes = (1..n).toMutableSet()
        val distance = (1..n).associateWithTo(mutableMapOf()) { Int.MAX_VALUE }
        distance[k] = 0
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.add(k to 0)

        while (pq.isNotEmpty() && remainingNodes.isNotEmpty()) {
            val (node, cost) = pq.poll()
            if (node !in remainingNodes) continue
            remainingNodes.remove(node)
            for ((otherNode, otherCost) in adjacent[node]!!) if (otherNode in remainingNodes) {
                val newDistance = min(cost + otherCost, distance[otherNode]!!)
                distance[otherNode] = newDistance
                pq.add(otherNode to newDistance)
            }
        }

        return if (remainingNodes.isNotEmpty()) -1 else distance.maxOf { it.value }
    }
}
