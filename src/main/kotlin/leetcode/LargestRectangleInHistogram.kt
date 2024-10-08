package leetcode

import kotlin.math.max

class LargestRectangleInHistogram {
    // Based on https://youtu.be/lktr76SxB2w?si=WjON-OpfcJyH3bPU
    fun largestRectangleArea(heights: IntArray): Int {
        val stack = mutableListOf<Int>()
        var result = 0

        fun update(right: Int) {
            val mid = stack.removeLast()
            val left = if (stack.isEmpty()) -1 else stack.last()
            result = max(result, heights[mid] * (right - left - 1))
        }

        for (right in heights.indices) {
            while (stack.isNotEmpty() && heights[stack.last()] > heights[right]) update(right)
            stack.add(right)
        }

        while (stack.isNotEmpty()) update(heights.size)

        return result
    }

    // Based on a vague memory I have from the first NeetCode 24 hours stream
    fun largestRectangleAreaOriginal(heights: IntArray): Int {
        val stack = mutableListOf<Pair</* index */ Int, /* height */ Int>>(Pair(-1, 0))
        var result = 0
        for (i in heights.indices) {
            result = max(result, heights[i])
            if (stack.last().second < heights[i]) {
                stack.add(Pair(i, heights[i]))
            }
            while (stack.last().second > heights[i]) {
                val (index, height) = stack.removeLast()
                result = max(result, (i - index) * height)
                if (stack.last().second <= heights[i]) {
                    stack.add(Pair(index, heights[i]))
                }
            }
        }
        while (stack.size > 1) {
            val (index, height) = stack.removeLast()
            result = max(result, (heights.size - index) * height)
        }
        return result
    }
}
