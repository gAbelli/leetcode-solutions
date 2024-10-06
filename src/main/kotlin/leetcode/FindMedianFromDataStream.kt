package leetcode

import java.util.PriorityQueue

class FindMedianFromDataStream {
    val leftQueue = PriorityQueue<Int>(compareBy { -it })
    val rightQueue = PriorityQueue<Int>()

    init {
        leftQueue.add(Int.MIN_VALUE / 2)
        rightQueue.add(Int.MAX_VALUE / 2)
    }

    fun addNum(num: Int) {
        if (num > leftQueue.peek()) rightQueue.add(num)
        else leftQueue.add(num)
        if (leftQueue.size < rightQueue.size - 1) leftQueue.add(rightQueue.poll())
        if (rightQueue.size < leftQueue.size) rightQueue.add(leftQueue.poll())
    }

    fun findMedian(): Double {
        val isEven = (leftQueue.size + rightQueue.size) % 2 == 0
        return if (isEven) (leftQueue.peek() + rightQueue.peek()).toDouble() / 2
        else rightQueue.peek().toDouble()
    }
}
