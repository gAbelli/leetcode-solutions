package leetcode

class ContainsDuplicate {
    fun containsDuplicate(nums: IntArray): Boolean {
        val numsSet = mutableSetOf<Int>()
        for (n in nums) {
            if (numsSet.contains(n)) {
                return true
            }
            numsSet.add(n)
        }
        return false
    }
}
