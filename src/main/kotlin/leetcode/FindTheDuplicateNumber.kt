package leetcode

class FindTheDuplicateNumber {
    fun findDuplicate(nums: IntArray): Int {
        var slow = 0
        var fast = 0

        slow = nums[slow]
        fast = nums[nums[fast]]
        while (slow != fast) {
            slow = nums[slow]
            fast = nums[nums[fast]]
        }

        var slow2 = 0
        while (slow != slow2) {
            slow = nums[slow]
            slow2 = nums[slow2]
        }

        return slow
    }
}
