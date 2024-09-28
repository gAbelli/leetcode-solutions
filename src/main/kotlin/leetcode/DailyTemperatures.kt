package leetcode

class DailyTemperatures {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = mutableListOf<Int>()
        val result = IntArray(temperatures.size)
        for (i in temperatures.indices.reversed()) {
            while (stack.isNotEmpty() && temperatures[i] >= temperatures[stack.last()]) {
                stack.removeLast()
            }
            if (stack.isNotEmpty()) {
                result[i] = stack.last() - i
            }
            stack.add(i)
        }
        return result
    }
}
