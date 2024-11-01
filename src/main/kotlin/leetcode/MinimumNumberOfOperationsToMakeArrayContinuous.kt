package leetcode

class MinimumNumberOfOperationsToMakeArrayContinuous {
    // Claim: there exists an optimal solution in which the leftmost (i.e. smallest) element
    // of the final array hasn't been modified.
    // Proof. Take any optimal solution. Assume the leading k terms have been modified.
    // If we move them to the end of the increasing sequence, the solution is still optimal
    // because they had to be modified anyway, but now the leading term is fixed.
    //
    // The two solutions I wrote are completely equivalent, except the first one uses bisection
    // to lower the complexity from quadratic to loglinear.
    fun minOperations(nums: IntArray): Int {
        val n = nums.size
        val nums = nums.distinct().sorted()
        return nums.indices.minOf { i ->
            // Find the largest j such that nums[i], ..., nums[j] are in (nums[i]..nums[i]+n-1)
            // or equivalently nums[j] <= nums[i] + n - 1
            var left = i
            var right = nums.size - 1
            while (left < right) {
                val mid = (left + right + 1) / 2
                val cmp = nums[mid].compareTo(nums[i] + n - 1)
                if (cmp <= 0) left = mid
                else right = mid - 1
            }
            n - 1 - left + i
        }
    }

    fun minOperationsQuadratic(nums: IntArray): Int {
        val set = nums.toSet()
        return set.minOf { k ->
            (k + 1..<k + nums.size).count { !set.contains(it) }
        }
    }
}
