package leetcode

import java.util.TreeSet

class MyCalendarI {
    // end = false, start = true
    // so that end < start
    private val calendar = TreeSet(compareBy<Pair<Int, Boolean>> { it.first }.thenBy { it.second })

    init {
        calendar.add(Pair(Int.MIN_VALUE, true))
        calendar.add(Pair(Int.MIN_VALUE + 1, false))
        calendar.add(Pair(Int.MAX_VALUE - 1, true))
        calendar.add(Pair(Int.MAX_VALUE, false))
    }

    fun book(startTime: Int, endTime: Int): Boolean {
        val (left, leftIsStart) = calendar.floor(Pair(startTime, true))!!
        if (leftIsStart) return false
        val (right, rightIsStart) = calendar.ceiling(Pair(endTime, false))!!
        if (!rightIsStart) return false
        if (calendar.higher(Pair(left, false)) != Pair(right, true)) return false
        calendar.add(Pair(startTime, true))
        calendar.add(Pair(endTime, false))
        return true
    }
}
