package leetcode

class BinarySearch {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (true) {
            if (left == right) {
                return if (nums[left] == target) left else -1
            }
            val mid = (left + right) / 2
            val cmp = nums[mid].compareTo(target)
            when {
                cmp == 0 -> return mid
                cmp < 0 -> left = mid + 1
                else -> right = mid
            }
        }
    }
}
