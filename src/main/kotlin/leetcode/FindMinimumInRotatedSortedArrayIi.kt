package leetcode

class FindMinimumInRotatedSortedArrayIi {
    // If nums[mid] == nums[right], it can happen that right was exactly the index
    // right after the peak, so doing right -= 1 seems like a bad idea.
    // But if that's the case, then for every i >= right or i <= mid,
    // the array must have the same value (i.e. nums[mid]).
    // Therefore, after doing right -= 1, we are in a situation where nums[left..right]
    // is sorted in increasing order. So we will always be in the case nums[mid] <= nums[right],
    // which means that left always stays fixed and eventually we get to it.
    // Surely there are solutions that are easier to remember. Also in this one it's not guaranteed
    // that left ends up being the first index after the peak.
    fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1

        while (left < right) {
            val mid = (left + right) / 2
            if (nums[mid] > nums[right]) left = mid + 1
            else if (nums[mid] < nums[right]) right = mid
            else right -= 1
        }

        return nums[left]
    }
}
