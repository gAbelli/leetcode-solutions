package leetcode

class Subsets {
    fun subsets(nums: IntArray): List<List<Int>> {
        val acc = mutableListOf<Int>()
        val result = mutableListOf<MutableList<Int>>()

        fun helper(i: Int) {
            if (i == nums.size) {
                result.add(acc.toMutableList())
                return
            }
            helper(i + 1)
            acc.add(nums[i])
            helper(i + 1)
            acc.removeLast()
        }

        helper(0)
        return result
    }
}
