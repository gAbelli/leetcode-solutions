package leetcode

class JumpGame {
    // The idea is that, if from i you can reach j, then from i you can actually
    // reach every index in i..j.
    // So you don't really have to remember all the indices j from which you can reach
    // the end, but just the leftmost.
    // =>) If you can reach the leftmost, then by definition you can reach the end
    // <=) If you can reach the end, then it's with a series of jumps to indices j1, j2, ..., jn
    //     with j1 >= leftmost. In particular, you can reach leftmost.
    fun canJump(nums: IntArray): Boolean {
        var leftmost = nums.size - 1
        for (i in nums.size - 2 downTo 0) if (i + nums[i] >= leftmost) leftmost = i
        return leftmost == 0
    }
}
