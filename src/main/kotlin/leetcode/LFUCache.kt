package leetcode

import java.util.PriorityQueue

// Of course there is also the alternative implementation where you keep two hash maps:
// * one from key to (value, frequency);
// * one from frequency to list of keys.
// To make this efficient, the first one could be a regular hash map, while the second
// one could be a SortedMap<Int, LinkedHashSet<Int>>.
// The SortedMap can probably be avoided by instead remembering what is the minimum frequency.
// In fact, that can either
// * be reset to 1 when we add a new element;
// * increase by 1 if we increase the frequency of the lowest element.
// This problem is really similar to `MaximumFrequencyStack`.
class LFUCache(capacity: Int) {
    val m = mutableMapOf</* key */ Int, Pair</* value */ Int, /* frequency */ Int>>()
    val pq = PriorityQueue<Triple</* key */ Int, /* frequency */ Int, /* time */ Int>>(
        compareBy<Triple<Int, Int, Int>> { it.second }.thenBy { it.third }
    )
    var t = 0
    val capacity = capacity

    fun get(key: Int): Int {
        if (key !in m) return -1
        val (value, frequency) = m[key]!!
        m[key] = Pair(value, frequency + 1)
        pq.add(Triple(key, frequency + 1, t++))
        return value
    }

    fun put(key: Int, value: Int) {
        if (key in m) {
            val frequency = m[key]!!.second
            m[key] = Pair(value, frequency + 1)
            pq.add(Triple(key, frequency + 1, t++))
        } else {
            if (m.size == capacity) removeLeastFrequent()
            m[key] = Pair(value, 1)
            pq.add(Triple(key, 1, t++))
        }
    }

    fun removeLeastFrequent() {
        fun isInvalid(): Boolean {
            val top = pq.peek()
            return top.first !in m || m[top.first]!!.second != top.second
        }
        while (isInvalid()) pq.poll()
        val keyToRemove = pq.poll().first
        m.remove(keyToRemove)
    }
}
