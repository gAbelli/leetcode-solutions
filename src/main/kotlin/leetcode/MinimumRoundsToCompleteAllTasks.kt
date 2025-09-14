package leetcode

class MinimumRoundsToCompleteAllTasks {
    fun minimumRounds(tasks: IntArray): Int {
        return tasks
            .asSequence()
            .groupingBy { it }
            .eachCount()
            .values
            .sumOf {
                if (it == 1) return -1
                (it + 2) / 3
            }
    }
}
