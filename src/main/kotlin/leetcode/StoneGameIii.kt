package leetcode

import kotlin.math.max

class StoneGameIii {
    // dp[i] is the best difference between Alice's score and Bob's score if Alice
    // starts from i and plays optimally. If Bob starts, the difference is going to be -dp[i]
    fun stoneGameIII(stoneValue: IntArray): String {
        val n = stoneValue.size
        var a = 0
        var b = 0
        var c = 0
        for (i in stoneValue.indices.reversed()) {
            var result = stoneValue[i] - a
            if (i < n - 1) result = max(result, stoneValue[i] + stoneValue[i + 1] - b)
            if (i < n - 2) result = max(result, stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - c)
            c = b; b = a; a = result
        }
        return if (a == 0) "Tie"
        else if (a > 0) "Alice"
        else "Bob"
    }
}
