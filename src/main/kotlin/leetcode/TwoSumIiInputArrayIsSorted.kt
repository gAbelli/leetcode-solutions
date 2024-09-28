package leetcode

class TwoSumIiInputArrayIsSorted {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        val n = numbers.size
        var i = 0
        var j = n - 1
        while (true) {
            val cmp = (numbers[i] + numbers[j]).compareTo(target)
            if (cmp == 0) {
                return intArrayOf(i + 1, j + 1)
            }
            if (cmp < 0) {
                i += 1
            } else {
                j -= 1
            }
        }
    }
}
