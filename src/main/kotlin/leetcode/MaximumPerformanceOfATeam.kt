package leetcode

import java.util.PriorityQueue
import kotlin.math.max

class MaximumPerformanceOfATeam {
    // Usual stuff: for every efficiency, find the best answer with that one as minimum efficiency
    fun maxPerformance(n: Int, speeds: IntArray, efficiencies: IntArray, k: Int): Int {
        val sortedIndices = (0..<n).sortedBy { -efficiencies[it] }
        val pq = PriorityQueue<Int>()
        var curSum = 0L
        var result = 0L
        for (i in sortedIndices) {
            pq.add(speeds[i])
            curSum += speeds[i]
            if (pq.size > k) curSum -= pq.poll()
            result = max(result, curSum * efficiencies[i])
        }
        return (result % (1_000_000_000 + 7)).toInt()
    }

    fun maxPerformanceQuadratic(n: Int, speeds: IntArray, efficiencies: IntArray, k: Int): Int {
        val sortedIndices = (0..<n).sortedBy { -speeds[it] }
        var result = 0L
        for (minEfficiency in efficiencies.distinct()) {
            var pickedEngineers = 0
            var totalSpeed = 0L
            for (i in sortedIndices) {
                if (efficiencies[i] >= minEfficiency) {
                    pickedEngineers += 1
                    totalSpeed += speeds[i]
                    result = max(result, minEfficiency * totalSpeed)
                }
                if (pickedEngineers == k) break
            }
        }
        return (result % (1_000_000_000 + 7)).toInt()
    }
}
