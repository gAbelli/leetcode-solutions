package leetcode

class NumberOfVisiblePeopleInAQueue {
    fun canSeePersonsCount(heights: IntArray): IntArray {
        val n = heights.size
        val result = IntArray(n)
        val stack = mutableListOf<Int>()

        for (i in heights.indices.reversed()) {
            val height = heights[i]
            while (stack.isNotEmpty() && stack.last() < height) {
                stack.removeLast()
                result[i] += 1
            }
            if (stack.isNotEmpty()) result[i] += 1
            stack.add(height)
        }

        return result
    }
}
