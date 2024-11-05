package leetcode

import kotlin.math.min

class FreedomTrail {
    private fun (List<Int>).safeGet(i: Int): Int =
        if (i < 0) Int.MIN_VALUE
        else if (i >= size) Int.MAX_VALUE
        else get(i)

    fun findRotateSteps(ring: String, key: String): Int {
        val cache = Array(ring.length) { IntArray(key.length) { -1 } }

        val positionsOf = ring.withIndex().groupBy(
            keySelector = { it.value },
            valueTransform = { it.index }
        )

        fun ringDistance(i: Int, j: Int): Int =
            if (i > j) ringDistance(j, i)
            else min(j - i, i + ring.length - j)

        fun helper(ringIndex: Int, keyIndex: Int): Int {
            if (keyIndex == key.length) return 0
            if (cache[ringIndex][keyIndex] != -1) return cache[ringIndex][keyIndex]
            val left = run {
                // Find the largest i such that positionsOf[c][i] <= ringIndex
                val positions = positionsOf[key[keyIndex]]!!
                var left = -1
                var right = positions.size - 1
                while (left < right) {
                    val mid = (left + right + 1) / 2
                    if (positions.safeGet(mid) <= ringIndex) left = mid
                    else right = mid - 1
                }
                if (left == -1) positions.last()
                else positions[left]
            }
            val right = run {
                // Find the smallest i such that positionsOf[c][i] >= ringIndex
                val positions = positionsOf[key[keyIndex]]!!
                var left = 0
                var right = positions.size
                while (left < right) {
                    val mid = (left + right) / 2
                    if (positions.safeGet(mid) >= ringIndex) right = mid
                    else left = mid + 1
                }
                if (right == positions.size) positions.first()
                else positions[right]
            }
            return min(
                ringDistance(ringIndex, left) + helper(left, keyIndex + 1),
                ringDistance(ringIndex, right) + helper(right, keyIndex + 1),
            ).also { cache[ringIndex][keyIndex] = it }
        }

        return helper(0, 0) + key.length
    }
}
