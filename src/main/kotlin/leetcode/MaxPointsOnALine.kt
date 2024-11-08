package leetcode

import kotlin.math.abs
import kotlin.math.round
import kotlin.math.sqrt

class MaxPointsOnALine {
    private tailrec fun gcd(a: Int, b: Int): Int {
        if (a < b) return gcd(b, a)
        if (b == 0) return a
        return gcd(b, a % b)
    }

    // y = mx + q
    // mx - y + q = 0
    // (y2-y1)x + (x1-x2)y + (y1*(x2-x1)-(y2-y1)x1) = 0
    fun maxPoints(points: Array<IntArray>): Int {
        if (points.size == 1) return 1

        val map = mutableMapOf<Triple<Int, Int, Int>, Int>()
        for (i in points.indices) for (j in i + 1..<points.size) {
            val (x1, y1) = points[i]
            val (x2, y2) = points[j]
            val a = y2 - y1
            val b = x1 - x2
            val c = y1 * (x2 - x1) - x1 * (y2 - y1)
            val d = gcd(gcd(abs(a), abs(b)), abs(c))
            val sign =
                if (a > 0 || (a == 0 && b > 0)) 1
                else -1
            val triple = Triple(sign * a / d, sign * b / d, sign * c / d)
            map[triple] = map.getOrDefault(triple, 0) + 1
        }

        val nChooseTwo = map.values.max()
        return (1 + round(sqrt(1.0 + 8 * nChooseTwo)).toInt()) / 2
    }
}
