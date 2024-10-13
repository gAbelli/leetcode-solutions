package leetcode

class JumpGameIi {
    fun jump(nums: IntArray): Int {
        // Element in position i can reach the end in i jumps
        val stack = mutableListOf(nums.size - 1)
        for (i in nums.size - 2 downTo 0) {
            var top: Int? = null
            while (stack.isNotEmpty() && stack.last() <= i + nums[i]) top = stack.removeLast()
            if (top != null) {
                stack.add(top)
                stack.add(i)
            }
        }
        return stack.size - 1
    }
}
