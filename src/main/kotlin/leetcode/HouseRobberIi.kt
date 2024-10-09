package leetcode

import kotlin.math.max

class HouseRobberIi {
    fun rob(nums: IntArray): Int {
        if (nums.size == 1) return nums.first()

        var result = 0

        run {
            var a = 0
            var b = nums.first()
            for (i in 1..<nums.size - 1) {
                a = b.also { b = max(b, a + nums[i]) }
            }
            result = b
        }

        run {
            var a = 0
            var b = nums[1]
            for (i in 2..<nums.size) {
                a = b.also { b = max(b, a + nums[i]) }
            }
            result = max(result, b)
        }

        return result
    }
}
