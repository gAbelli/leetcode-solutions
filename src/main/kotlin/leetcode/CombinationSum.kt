package leetcode

class CombinationSum {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val acc = mutableListOf<Int>()
        val result = mutableListOf<List<Int>>()

        fun helper(i: Int, curSum: Int) {
            if (i == candidates.size) {
                if (curSum == target) result.add(acc.toList())
                return
            }
            var count = 0
            while (curSum + candidates[i] * count <= target) {
                helper(i + 1, curSum + candidates[i] * count)
                count += 1
                acc.add(candidates[i])
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
