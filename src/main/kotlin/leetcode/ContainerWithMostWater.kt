package leetcode

import kotlin.math.max
import kotlin.math.min

class ContainerWithMostWater {
    fun maxArea(height: IntArray): Int {
        var result = 0
        var i = 0
        var j = height.size - 1
        while (i < j) {
            val area = min(height[i], height[j]) * (j - i)
            result = max(result, area)
            if (height[i] < height[j]) {
                i += 1
            } else {
                j -= 1
            }
        }
        return result
    }
}
