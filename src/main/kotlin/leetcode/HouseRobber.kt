package leetcode

import kotlin.math.max

class HouseRobber {
    fun rob(nums: IntArray): Int {
        var a = 0
        var b = nums.first()
        for (i in 1..<nums.size) {
            a = b.also { b = max(b, a + nums[i]) }
        }
        return b
    }
}
