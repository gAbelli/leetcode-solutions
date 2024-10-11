package leetcode

class TargetSum {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        var dp = mapOf(0 to 1)

        for (num in nums) {
            val newDp = mutableMapOf<Int, Int>()
            for ((sum, count) in dp) {
                newDp[sum + num] = newDp.getOrDefault(sum + num, 0) + count
                newDp[sum - num] = newDp.getOrDefault(sum - num, 0) + count
            }
            dp = newDp
        }

        return dp.getOrDefault(target, 0)
    }
}
