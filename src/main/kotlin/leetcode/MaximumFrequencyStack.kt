package leetcode

import java.util.PriorityQueue

// Some alternative ideas:
// * If we had a decreasable heap, we could avoid the while loop at the beginning of pop()
//   (same situation as in Dijkstra).
// * Instead of the heap, we could store a sorted map from frequencies to elements.
// * Same as the previous point, but with a classical hash map instead. The important fact is that the
//   maximum frequency always varies by at most one, so when you push or pop an element it's easy
//   to figure out what is the new highest frequency.
class MaximumFrequencyStack {
    var time = 0
    val frequencyOf = mutableMapOf<Int, Int>()
    val heap = PriorityQueue(
        compareBy<Triple</* value */ Int, /* frequency */ Int, /* time */ Int>> { -it.second }.thenBy { -it.third }
    )

    fun push(value: Int) {
        val frequency = frequencyOf.getOrDefault(value, 0) + 1
        frequencyOf[value] = frequency
        heap.add(Triple(value, frequency, time++))
    }

    fun pop(): Int {
        while (heap.peek().second > frequencyOf[heap.peek().first]!!) {
            heap.poll()
        }
        val value = heap.poll().first
        frequencyOf[value] = frequencyOf[value]!! - 1
        return value
    }
}
