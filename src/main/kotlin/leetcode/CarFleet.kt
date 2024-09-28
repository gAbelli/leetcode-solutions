package leetcode

class CarFleet {
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        val n = position.size
        val indices = Array(n) { it }
        indices.sortBy { -position[it] }

        val sortedPosition = IntArray(n) { position[indices[it]] }
        val sortedSpeed = IntArray(n) { speed[indices[it]] }

        fun intersecting(leadingCarIndex: Int, runnerUpCarIndex: Int): Boolean {
            val position1 = sortedPosition[leadingCarIndex]
            val position2 = sortedPosition[runnerUpCarIndex]
            val speed1 = sortedSpeed[leadingCarIndex]
            val speed2 = sortedSpeed[runnerUpCarIndex]
            return (target - position2).toLong() * speed1 <= (target - position1).toLong() * speed2
        }

        var blockingCarIndex = 0
        var result = 1
        for (i in 1..<sortedPosition.size) {
            if (!intersecting(blockingCarIndex, i)) {
                blockingCarIndex = i
                result += 1
            }
        }
        return result
    }
}
