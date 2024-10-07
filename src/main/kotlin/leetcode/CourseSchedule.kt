package leetcode

class CourseSchedule {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val successors = Array<MutableSet<Int>>(numCourses) { mutableSetOf() }
        val predecessors = Array<MutableSet<Int>>(numCourses) { mutableSetOf() }
        prerequisites.forEach {
            successors[it[1]].add(it[0])
            predecessors[it[0]].add(it[1])
        }

        val coursesWithNoPredecessors = predecessors.indices.filter { i -> predecessors[i].isEmpty() }.toSet()

        val queue = ArrayDeque<Int>()
        queue.addAll(coursesWithNoPredecessors)
        val seen = BooleanArray(numCourses) { coursesWithNoPredecessors.contains(it) }

        while (queue.isNotEmpty()) {
            val course = queue.removeFirst()
            for (successor in successors[course]) {
                predecessors[successor].remove(course)
                if (predecessors[successor].isEmpty() && !seen[successor]) {
                    seen[successor] = true
                    queue.add(successor)
                }
            }
        }

        return seen.fold(true) { a, b -> a && b }
    }
}
