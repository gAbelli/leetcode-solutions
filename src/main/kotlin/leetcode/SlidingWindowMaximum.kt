package leetcode

import java.util.PriorityQueue

class SlidingWindowMaximum {
    // We can easily prove that
    // 1. elements of queue (indices) are in increasing order
    // 2. queue.map(nums.get) is in decreasing order
    // 3. we never drop from the queue any relevant numbers
    // In fact, 1 and 2 are trivial and 3 follows from the fact that we only remove elements
    // because they are too old or because they are smaller than some element that comes after them.
    // From these three statements, it follows that the solution is correct
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val result = IntArray(nums.size - k + 1)
        val queue = ArrayDeque<Int>()

        for (i in nums.indices) {
            while (queue.isNotEmpty() && queue.first() <= i - k) queue.removeFirst()
            while (queue.isNotEmpty() && nums[queue.last()] < nums[i]) queue.removeLast()
            queue.add(i)
            if (i >= k - 1) result[i - k + 1] = nums[queue.first()]
        }

        return result
    }

    fun maxSlidingWindowPq(nums: IntArray, k: Int): IntArray {
        val result = IntArray(nums.size - k + 1)
        val pq = PriorityQueue<Pair</* element */ Int, /* index */Int>>(compareBy { -it.first })

        for (i in 0..<k - 1) pq.add(nums[i] to i)
        for (i in k - 1..<nums.size) {
            pq.add(nums[i] to i)
            while (pq.peek().second <= i - k) pq.poll()
            result[i - k + 1] = pq.peek().first
        }

        return result
    }
}
