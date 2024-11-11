package leetcode

class MinimumNumberOfDaysToEatNOranges {
    fun minDays(n: Int): Int {
        var result = 0
        var set = mutableSetOf(n)
        while (!set.contains(0)) {
            result += 1
            val newSet = mutableSetOf<Int>()
            for (k in set) {
                newSet.add(k - 1)
                if (k % 2 == 0) newSet.add(k / 2)
                if (k % 3 == 0) newSet.add(k / 3)
            }
            set = newSet
        }
        return result
    }
}
