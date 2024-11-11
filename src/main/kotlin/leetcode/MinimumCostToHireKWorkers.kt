package leetcode

import java.util.PriorityQueue
import kotlin.math.min

class MinimumCostToHireKWorkers {
    // if worker 0 gets paid x, then worker i gets paid quality[i]/quality[0] * x
    // This must be >= wage[i]
    // x >= wage[i] * quality[0] / quality[i] for every i
    // x = max_{i}{wage[i] * quality[0] / quality[i]}
    // x = (max_{i} {wage[i] / quality[i]}) * quality[0]
    fun mincostToHireWorkers(quality: IntArray, wage: IntArray, k: Int): Double {
        val n = quality.size
        val sortedWorkersByRatio = (0..<n).sortedBy { wage[it].toDouble() / quality[it] }
        val pq = PriorityQueue<Int>(compareBy { -quality[it] })
        var qualitySum = 0
        var result = Double.MAX_VALUE

        for (worker in sortedWorkersByRatio) {
            pq.add(worker)
            qualitySum += quality[worker]
            if (pq.size > k) qualitySum -= quality[pq.poll()]
            if (pq.size == k) result =
                min(result, qualitySum.toDouble() * wage[worker].toDouble() / quality[worker].toDouble())
        }

        return result
    }
}
