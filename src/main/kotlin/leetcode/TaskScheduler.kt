package leetcode

import java.util.PriorityQueue

class TaskScheduler {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val taskCount = tasks.asSequence().groupingBy { it }.eachCountTo(mutableMapOf())
        val pq = PriorityQueue(compareBy<Pair<Char, Int>> { it.second })
        for (key in taskCount.keys) {
            pq.add(key to 0)
            taskCount[key] = taskCount[key]!! - 1
        }
        var t = 0
        while (pq.isNotEmpty()) {
            val candidates = mutableListOf<Pair<Char, Int>>()
            while (pq.isNotEmpty() && pq.peek().second <= t) candidates.add(pq.poll())
            val pair = candidates.maxByOrNull { taskCount[it.first]!! }
            val task = pair?.first
            if (task != null) {
                if (taskCount[task]!! > 0) pq.add(task to (t + n + 1))
                taskCount[task] = taskCount[task]!! - 1
            }
            candidates.asSequence().filter { it.first != task }.forEach { pq.add(it) }
            t += 1
        }
        return t
    }
}
