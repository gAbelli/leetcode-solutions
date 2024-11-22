package leetcode

import java.util.PriorityQueue
import kotlin.math.max

class ParallelCoursesIii {
    fun minimumTime(n: Int, relations: Array<IntArray>, time: IntArray): Int {
        val successors = Array(n) { mutableSetOf<Int>() }
        val predecessors = Array(n) { mutableSetOf<Int>() }
        for (relation in relations) {
            successors[relation[0] - 1].add(relation[1] - 1)
            predecessors[relation[1] - 1].add(relation[0] - 1)
        }

        val pq = PriorityQueue<Pair</* course number */ Int, /* finish time */ Int>>(compareBy { it.second })
        for (i in 0..<n) if (predecessors[i].isEmpty()) pq.add(Pair(i, time[i]))
        var result = 0

        while (pq.isNotEmpty()) {
            val (course, finishTime) = pq.poll()
            result = max(result, finishTime)
            for (successor in successors[course]) {
                predecessors[successor].remove(course)
                if (predecessors[successor].isEmpty()) pq.add(Pair(successor, finishTime + time[successor]))
            }
        }

        return result
    }
}
