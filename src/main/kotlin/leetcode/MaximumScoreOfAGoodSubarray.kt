package leetcode

import kotlin.math.max
import kotlin.math.min

class MaximumScoreOfAGoodSubarray {
    fun maximumScore(nums: IntArray, k: Int): Int {
        val n = nums.size
        var i = k
        var j = k
        var curMin = nums[k]
        var result = 0

        fun increaseJ() {
            j += 1
            curMin = min(curMin, nums[j])
        }

        fun decreaseI() {
            i -= 1
            curMin = min(curMin, nums[i])
        }

        while (true) {
            result = max(result, curMin * (j - i + 1))
            if (i == 0 && j == n - 1) break
            else if (i == 0) increaseJ()
            else if (j == n - 1) decreaseI()
            else {
                if (nums[i - 1] <= nums[j + 1]) increaseJ()
                else decreaseI()
            }
        }
        return result
    }
}
