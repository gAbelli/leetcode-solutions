package leetcode

class Permutations {
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun swap(i: Int, j: Int) {
            nums[i] = nums[j].also { nums[j] = nums[i] }
        }

        fun helper(i: Int) {
            if (i == nums.size) {
                result.add(nums.toList())
                return
            }
            for (j in i..<nums.size) {
                swap(i, j)
                helper(i + 1)
                swap(i, j)
            }
        }

        helper(0)
        return result
    }
}
