package leetcode

import kotlin.math.min

class CheapestFlightsWithinKStops {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val k = min(k, n - 2)
        var costs = IntArray(n) { Int.MAX_VALUE }
        costs[src] = 0

        for (i in 0..k) {
            val costsCopy = costs.copyOf()
            for (flight in flights) {
                val (from, to, price) = Triple(flight[0], flight[1], flight[2])
                if (costs[from] == Int.MAX_VALUE) continue
                costsCopy[to] = min(costsCopy[to], costs[from] + price)
            }
            if (costs.contentEquals(costsCopy)) break
            costs = costsCopy
        }

        return if (costs[dst] == Int.MAX_VALUE) -1 else costs[dst]
    }
}
