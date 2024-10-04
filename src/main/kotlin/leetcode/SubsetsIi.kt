package leetcode

class SubsetsIi {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        val acc = mutableListOf<Int>()
        val result = mutableListOf<MutableList<Int>>()

        fun helper(i: Int) {
            if (i == nums.size) {
                result.add(acc.toMutableList())
                return
            }
            helper(i + 1)
            if (i > 0 && nums[i] == nums[i - 1]) return

            var j = i
            while (j < nums.size && nums[i] == nums[j]) {
                j += 1
            }
            val consecutive = j - i
            for (k in 0..<consecutive) {
                acc.add(nums[i])
                helper(i + consecutive)
            }
            for (k in 0..<consecutive) {
                acc.removeLast()
            }
        }

        helper(0)
        return result
    }
}
