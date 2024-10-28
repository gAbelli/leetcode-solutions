package leetcode

import kotlin.math.min

class MinimumIntervalToIncludeEachQuery {
    enum class Operation {
        EXIT, ENTER, EVALUATE
    }

    // This is a cute (and efficient) solution where we imagine a state machine that can execute three kinds of operations:
    // * Enter an interval
    // * Exit an interval
    // * Evaluate the minimum size of active intervals containing the query
    // Operations are then presented in chronological order. It's important to process EXIT/ENTER events before EVALUATE events.
    // The complexity should be O((n+k)log(n+k)).
    // However, I think it's worth remembering this as a general solution instead:
    // https://leetcode.com/problems/minimum-interval-to-include-each-query/solutions/1186792/python-a-classic-heap-question-explained
    fun minInterval(intervals: Array<IntArray>, queries: IntArray): IntArray {
        val result = IntArray(queries.size) { -1 }
        val l = mutableListOf<Triple<Int, Operation, Int>>().apply {
            addAll(intervals.withIndex().map { Triple(it.value[0], Operation.ENTER, it.index) })
            addAll(intervals.withIndex().map { Triple(it.value[1] + 1, Operation.EXIT, it.index) })
            addAll(queries.withIndex().map { Triple(it.value, Operation.EVALUATE, it.index) })
            sortWith(compareBy<Triple<Int, Operation, Int>> { it.first }.thenBy { it.second.ordinal })
        }
        val currentSizes = sortedMapOf<Int, Int>()
        for ((_, op, index) in l) when (op) {
            Operation.EVALUATE -> {
                if (currentSizes.isNotEmpty()) result[index] = currentSizes.firstKey()
            }

            Operation.ENTER -> {
                val size = intervals[index][1] - intervals[index][0] + 1
                currentSizes[size] = currentSizes.getOrDefault(size, 0) + 1
            }

            Operation.EXIT -> {
                val size = intervals[index][1] - intervals[index][0] + 1
                currentSizes[size] = currentSizes[size]!! - 1
                if (currentSizes[size]!! == 0) currentSizes.remove(size)
            }
        }
        return result
    }

    fun minIntervalCache(intervals: Array<IntArray>, queries: IntArray): IntArray {
        val delimiters = mutableListOf(Int.MIN_VALUE).apply {
            addAll(
                intervals.asSequence().flatMap { listOf(it[0], it[1] + 1) }.distinct().sorted()
            )
        }
        val cache = mutableMapOf<Int, Int>()
        return queries.map { query ->
            // Find the largest index i such that query >= delimiters[i]
            // The answer for query will be the same as the one for delimiters[i]
            var left = 0
            var right = delimiters.size - 1
            while (left < right) {
                val mid = (left + right + 1) / 2
                if (query >= delimiters[mid]) left = mid
                else right = mid - 1
            }
            cache.getOrPut(delimiters[left]) {
                var lowestSize = Int.MAX_VALUE
                for (interval in intervals) {
                    if (delimiters[left] in interval[0]..interval[1])
                        lowestSize = min(lowestSize, interval[1] - interval[0] + 1)
                }
                if (lowestSize == Int.MAX_VALUE) -1 else lowestSize
            }
        }.toIntArray()
    }
}
