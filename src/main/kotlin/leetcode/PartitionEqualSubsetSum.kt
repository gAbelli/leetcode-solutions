package leetcode

class PartitionEqualSubsetSum {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) return false
        val halfSum = sum / 2
        val memo = mutableMapOf<Pair<Int, Int>, Boolean>()

        fun helper(i: Int, target: Int): Boolean {
            if (i == nums.size) return target == 0
            if (target < 0) return false
            if (Pair(i, target) in memo) return memo[Pair(i, target)]!!
            val result = helper(i + 1, target) || helper(i + 1, target - nums[i])
            memo[Pair(i, target)] = result
            return result
        }

        return helper(0, halfSum)
    }
}
