package leetcode

class CombinationSumIi {
    fun combinationSum2(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        val acc = mutableListOf<Int>()
        val result = mutableListOf<List<Int>>()

        fun helper(i: Int, curSum: Int) {
            if (i == nums.size) {
                if (curSum == target) result.add(acc.toList())
                return
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                helper(i + 1, curSum)
                return
            }

            var j = i
            while (j < nums.size && nums[i] == nums[j]) {
                j += 1
            }
            val consecutive = j - i

            var count = 0
            while (count <= consecutive && curSum + nums[i] * count <= target) {
                helper(i + consecutive, curSum + nums[i] * count)
                count += 1
                acc.add(nums[i])
            }
            while (count != 0) {
                acc.removeLast()
                count -= 1
            }
        }

        helper(0, 0)
        return result
    }
}
