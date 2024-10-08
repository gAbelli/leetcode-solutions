package leetcode

class FirstMissingPositive {
    fun firstMissingPositive(nums: IntArray): Int {
        for (i in nums.indices) nums[i] -= 1
        for (i in nums.indices) {
            while (i != nums[i] && nums[i] in nums.indices && nums[i] != nums[nums[i]]) {
                nums[i] = nums[nums[i]].also { nums[nums[i]] = nums[i] }
            }
        }
        for (i in nums.indices) if (nums[i] != i) return i + 1
        return nums.size + 1
    }
}
