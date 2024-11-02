package leetcode

import java.util.PriorityQueue

class Ipo {
    // Clearly we want to use a greedy approach. The straightforward solution is quadratic, but
    // with a priority queue we can make it loglinear.
    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        val n = profits.size
        val sortedProjects = (0..<n).sortedBy { capital[it] }
        val pq = PriorityQueue<Int>(compareBy { -it })
        var pickedProjects = 0
        var w = w
        var i = 0
        while (pickedProjects != k) {
            while (i < n && w >= capital[sortedProjects[i]]) {
                pq.add(profits[sortedProjects[i]])
                i += 1
            }
            if (pq.isEmpty()) break
            w += pq.poll()
            pickedProjects += 1
        }
        return w
    }

    fun findMaximizedCapitalQuadratic(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        val n = profits.size
        var w = w
        val pickedProjects = mutableSetOf<Int>()

        while (true) {
            if (pickedProjects.size == k) return w
            val project = (0..<n)
                .asSequence()
                .filterNot { pickedProjects.contains(it) }
                .filter { w >= capital[it] }
                .maxByOrNull { profits[it] }
            if (project == null) return w
            pickedProjects.add(project)
            w += profits[project]
        }
    }
}
