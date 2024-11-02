package leetcode

class SubarraysWithKDifferentIntegers {
    fun <K> (MutableMap<K, Int>).increase(key: K) {
        val prev = getOrPut(key) { 0 }
        put(key, prev + 1)
    }

    fun <K> (MutableMap<K, Int>).decrease(key: K) {
        val prev = get(key)!!
        if (prev == 1) remove(key)
        else put(key, prev - 1)
    }

    // An alternative way to write this is to notice that, if the problem asked about
    // arrays that contain at most k distinct elements, the solution would be simpler to write.
    // But from there we can derive a solution to our real problem, which is just
    // atMost(nums, k) - atMost(nums, k-1)
    fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
        val leftICounts = mutableMapOf<Int, Int>()
        val rightICounts = mutableMapOf<Int, Int>()
        var result = 0
        var leftI = 0
        var rightI = 0

        for (j in nums.indices) {
            leftICounts.increase(nums[j])
            rightICounts.increase(nums[j])
            if (leftICounts.size < k) continue

            if (leftICounts.size == k + 1) {
                while (leftICounts.size == k + 1) {
                    leftICounts.decrease(nums[leftI])
                    leftI += 1
                }
                while (rightICounts.size == k + 1) {
                    rightICounts.decrease(nums[rightI])
                    rightI += 1
                }
            }

            while (rightICounts[nums[rightI]]!! > 1) {
                rightICounts.decrease(nums[rightI])
                rightI += 1
            }

            result += rightI - leftI + 1
        }

        return result
    }
}
