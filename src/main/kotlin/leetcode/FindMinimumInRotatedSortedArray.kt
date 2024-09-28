package leetcode

class FindMinimumInRotatedSortedArray {
    fun findMin(nums: IntArray): Int {
        if (nums.first() <= nums.last()) return nums.first()

        // Goal: find the only index i such that nums[i] < nums[i-1]
        var left = 1
        var right = nums.size - 1

        while (left < right) {
            val mid = (left + right) / 2
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid]
            }
            if (nums[mid] < nums[right]) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return nums[left]
    }
}
