package leetcode

class ProductOfArrayExceptSelf {
    fun productExceptSelf(nums: IntArray): IntArray {
        val length = nums.size
        val result = IntArray(length) { 1 }
        for (i in length - 1 downTo 0) {
            val next = if (i == length - 1) 1 else result[i + 1]
            result[i] = nums[i] * next
        }
        for (i in nums.indices) {
            val prev = if (i == 0) 1 else nums[i - 1]
            nums[i] = prev * nums[i]
        }
        for (i in nums.indices) {
            val prev = if (i == 0) 1 else nums[i - 1]
            val next = if (i == length - 1) 1 else result[i + 1]
            result[i] = prev * next
        }
        return result
    }
}
