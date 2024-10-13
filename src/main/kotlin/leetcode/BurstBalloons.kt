package leetcode

class BurstBalloons {
    fun (IntArray).safeGet(i: Int) = getOrElse(i) { 1 }

    fun maxCoins(nums: IntArray): Int {
        val memo = Array(nums.size) { IntArray(nums.size) { -1 } }

        fun helper(left: Int, right: Int): Int {
            if (left > right) return 0
            if (memo[left][right] != -1) return memo[left][right]
            val result = (left..right).maxOf { i ->
                helper(left, i - 1) +
                        helper(i + 1, right) +
                        nums[i] * nums.safeGet(left - 1) * nums.safeGet(right + 1)
            }
            memo[left][right] = result
            return result
        }

        return helper(0, nums.size - 1)
    }
}
