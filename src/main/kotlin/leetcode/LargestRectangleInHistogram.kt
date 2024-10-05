package leetcode

import kotlin.math.max

class LargestRectangleInHistogram {
    fun largestRectangleArea(heights: IntArray): Int {
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
