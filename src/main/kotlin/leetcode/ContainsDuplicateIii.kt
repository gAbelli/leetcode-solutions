package leetcode

import java.util.*

class ContainsDuplicateIii {
    fun containsNearbyAlmostDuplicate(nums: IntArray, indexDiff: Int, valueDiff: Int): Boolean {
        val validNums = TreeMap<Int, Int>()
        for (j in nums.indices) {
            if (j > indexDiff) {
                val toRemove = nums[j - indexDiff - 1]
                if (validNums[toRemove]!! == 1) validNums.remove(toRemove)
                else validNums[toRemove] = validNums[toRemove]!! - 1
            }
            val floorKey = validNums.floorKey(nums[j])
            if (floorKey != null && nums[j] - floorKey <= valueDiff) return true
            val ceilingKey = validNums.ceilingKey(nums[j])
            if (ceilingKey != null && ceilingKey - nums[j] <= valueDiff) return true
            validNums[nums[j]] = validNums.getOrDefault(nums[j], 0) + 1
        }
        return false
    }
}
