package leetcode

import java.util.SortedMap

class QueriesOnNumberOfPointsInsideACircle {
    fun countPoints(points: Array<IntArray>, queries: Array<IntArray>): IntArray {
        val pointsByYByX = sortedMapOf<Int, SortedMap<Int, Int>>().apply {
            for (point in points) getOrPut(point[0]) { sortedMapOf() }.merge(point[1], 1, Int::plus)
        }
        return IntArray(queries.size) { i ->
            val query = queries[i]
            val centerX = query[0]
            val centerY = query[1]
            val r = query[2]
            val rSquared = r * r
            pointsByYByX.subMap(centerX - r, centerX + r + 1).asSequence().sumOf { (x, pointsByY) ->
                pointsByY.subMap(centerY - r, centerY + r + 1).asSequence().sumOf { (y, count) ->
                    val xDiff = x - centerX
                    val yDiff = y - centerY
                    if (xDiff * xDiff + yDiff * yDiff <= rSquared) count
                    else 0
                }
            }
        }
    }
}
