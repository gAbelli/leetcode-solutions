package leetcode

import java.util.PriorityQueue

class KClosestPointsToOrigin {
    fun (IntArray).lengthSquared(): Int = get(0) * get(0) + get(1) * get(1)

    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val pq = PriorityQueue<IntArray> { a, b -> -a.lengthSquared().compareTo(b.lengthSquared()) }
        points.forEach {
            pq.add(it)
            if (pq.size > k) pq.poll()
        }
        return pq.toTypedArray()
    }
}
