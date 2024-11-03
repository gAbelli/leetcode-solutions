package leetcode

class FindTheLongestValidObstacleCourseAtEachPosition {
    fun longestObstacleCourseAtEachPosition(obstacles: IntArray): IntArray {
        val n = obstacles.size
        // dp[i] is the minimum height you should have to achieve length i+1
        val dp = IntArray(n) { Int.MAX_VALUE }.apply { set(0, 0) }
        val result = IntArray(n) { 1 }
        for (j in 0..<n) {
            // Find the largest i such that obstacles[j] >= dp[i]
            var left = 0
            var right = j
            while (left < right) {
                val mid = (left + right + 1) / 2
                if (obstacles[j] >= dp[mid]) left = mid
                else right = mid - 1
            }
            result[j] = left + 1
            if (left != n - 1) dp[left + 1] = obstacles[j]
        }
        return result
    }
}
