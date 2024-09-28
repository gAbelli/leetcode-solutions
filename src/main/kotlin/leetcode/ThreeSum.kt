package leetcode

class ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val n = nums.size
        val result = mutableListOf<List<Int>>()
        nums.sort()

        for (i in 0..<n - 1) {
            if (i > 0 && nums[i] == nums[i - 1]) continue
            val target = -nums[i]
            var j = i + 1
            var k = n - 1
            while (j < k) {
                val cmp = (nums[j] + nums[k]).compareTo(target)
                if (cmp == 0) {
                    val candidate = listOf(nums[i], nums[j], nums[k])
                    if (result.isEmpty() || result.last() != candidate) {
                        result.add(candidate)
                    }
                    j += 1
                    k -= 1
                    continue
                }
                if (cmp < 0) {
                    j += 1
                } else {
                    k -= 1
                }
            }
        }

        return result
    }
}
