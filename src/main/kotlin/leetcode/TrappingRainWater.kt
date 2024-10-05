package leetcode

import kotlin.math.max
import kotlin.math.min

class TrappingRainWater {
    // The idea is to write the function recursively (see below), imagining that height is itself
    // surrounded by other walls of max height leftMax and rightMax.
    // However, if we write it with a plain while loop, we achieve constant space complexity
    fun trap(height: IntArray): Int {
        if (height.isEmpty()) return 0
        var start = 0
        var end = height.size - 1
        var leftMax = 0
        var rightMax = 0
        var result = 0

        while (start <= end) {
            if (leftMax <= rightMax) {
                result += max(leftMax - height[start], 0)
                leftMax = max(leftMax, height[start])
                start += 1
            } else {
                result += max(rightMax - height[end], 0)
                rightMax = max(rightMax, height[end])
                end -= 1
            }
        }

        return result
    }

    fun trapTailrec(height: IntArray): Int {
        tailrec fun helper(start: Int, end: Int, leftMax: Int, rightMax: Int, acc: Int): Int {
            if (start > end) return 0
            return if (leftMax <= rightMax) {
                helper(start + 1, end, max(leftMax, height[start]), rightMax, acc + max(leftMax - height[start], 0))
            } else {
                helper(start, end - 1, leftMax, max(rightMax, height[end]), acc + max(rightMax - height[end], 0))
            }
        }

        return helper(0, height.size - 1, 0, 0, 0)
    }

    fun trapConstantSpaceIdea(height: IntArray): Int {
        fun helper(start: Int, end: Int, leftMax: Int, rightMax: Int): Int {
            if (start > end) return 0
            return if (leftMax <= rightMax) {
                max(leftMax - height[start], 0) + helper(start + 1, end, max(leftMax, height[start]), rightMax)
            } else {
                max(rightMax - height[end], 0) + helper(start, end - 1, leftMax, max(rightMax, height[end]))
            }
        }

        return helper(0, height.size - 1, 0, 0)
    }

    fun trapLinearSpace(height: IntArray): Int {
        val n = height.size
        val left = IntArray(n)
        val right = IntArray(n)

        for (i in 1..<n) {
            left[i] = max(left[i - 1], height[i - 1])
        }
        for (i in n - 2 downTo 0) {
            right[i] = max(right[i + 1], height[i + 1])
        }

        var result = 0
        for (i in height.indices) {
            result += max(min(left[i], right[i]) - height[i], 0)
        }
        return result
    }
}
