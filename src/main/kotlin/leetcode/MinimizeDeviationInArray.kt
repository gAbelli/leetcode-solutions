package leetcode

import kotlin.math.min

class MinimizeDeviationInArray {
    // Let's give a proof of correctness.
    //
    // First of all, multiplying odd numbers by two at the beginning doesn't change the result
    // because it produces an equivalent array (meaning that we can go back and forth with legal operations).
    //
    // We can now assume without loss of generality that division by two of even numbers is the only
    // allowed operation. In fact, if you consider a new problem where multiplication is not allowed, the
    // two problems clearly have the same optimal solution.
    //
    // Now we write an algorithm that finds the best solution for every possible choice of a maximum element,
    // and then takes the minimum among these solutions.
    // Clearly, the best solution is achieved by making every element as large as possible (but not larger than
    // the maximum).
    // Now we need to prove that (1) our loop does exactly that and (2) our loop explores all possible maxima.
    // 1. This is trivial because the only operation that we have is dividing by two, and this can never improve
    //    the solution (even if we apply it multiple times) while keeping the maximum fixed.
    // 2. The loop uses as maxima all numbers that are greater or equal than the maximum among the largest odd
    //    divisors of the elements of the array. The maximum will never be lower than that, so this is enough.
    //
    // Notice how an analogous version of this algorithm that uses the minimum instead of the maximum would not
    // work because dividing all elements by two at the beginning is not a reversible operation. For example,
    // if 4 becomes 1 then it can never go back to 4.
    fun minimumDeviation(nums: IntArray): Int {
        for (i in nums.indices) if (nums[i] % 2 == 1) nums[i] *= 2
        val set = sortedSetOf<Int>().apply { addAll(nums.asSequence()) }
        var result = Int.MAX_VALUE
        while (true) {
            val first = set.first()
            val last = set.last()
            result = min(result, last - first)
            if (last % 2 == 1) break
            set.remove(last)
            set.add(last / 2)
        }
        return result
    }
}
