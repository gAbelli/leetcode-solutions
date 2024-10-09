package leetcode

import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

class ClimbingStairs {
    fun climbStairs(n: Int): Int = round((phi.pow(n + 1) - psi.pow(n + 1)) / rootFive).toInt()

    companion object {
        val rootFive = sqrt(5.0)
        val phi = (1 + rootFive) / 2
        val psi = -1.0 / phi
    }
}
