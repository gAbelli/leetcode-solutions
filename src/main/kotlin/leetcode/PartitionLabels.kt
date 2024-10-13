package leetcode

import kotlin.math.max

class PartitionLabels {
    fun partitionLabels(s: String): List<Int> {
        val result = mutableListOf<Int>()
        val lastOccurrence = mutableMapOf<Char, Int>()
        for ((index, c) in s.withIndex()) {
            lastOccurrence[c] = index
        }
        var start = 0
        var atLeast = 0
        for (end in s.indices) {
            atLeast = max(atLeast, lastOccurrence[s[end]]!!)
            if (end == atLeast) {
                result.add(end - start + 1)
                start = end + 1
            }
        }

        return result
    }
}
