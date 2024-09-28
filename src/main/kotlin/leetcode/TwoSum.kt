package leetcode

class TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val pos = mutableMapOf<Int, Int>()
        for (entry in nums.withIndex()) {
            val diff = target - entry.value
            if (pos.contains(diff)) {
                return intArrayOf(pos[diff]!!, entry.index)
            }
            pos[entry.value] = entry.index
        }
        return intArrayOf()
    }
}
