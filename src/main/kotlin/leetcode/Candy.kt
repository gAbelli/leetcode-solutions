package leetcode

import kotlin.math.max

class Candy {
    // There is also a linear time solution
    fun candy(ratings: IntArray): Int {
        val n = ratings.size
        val sortedIndices = (0..<n).sortedBy { ratings[it] }
        val candies = IntArray(n) { 1 }
        for (i in sortedIndices) {
            if (i > 0 && ratings[i] > ratings[i - 1]) candies[i] = max(candies[i], candies[i - 1] + 1)
            if (i < n - 1 && ratings[i] > ratings[i + 1]) candies[i] = max(candies[i], candies[i + 1] + 1)
        }
        return candies.sum()
    }
}
