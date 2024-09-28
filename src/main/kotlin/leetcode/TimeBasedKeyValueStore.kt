package leetcode

class TimeBasedKeyValueStore {
    data class Entry(val value: String, val timestamp: Int)

    val m = mutableMapOf<String, MutableList<Entry>>()

    fun set(key: String, value: String, timestamp: Int) {
        if (!m.contains(key)) m[key] = mutableListOf()
        m[key]!!.add(Entry(value, timestamp))
    }

    fun get(key: String, timestamp: Int): String {
        val entries = m[key] ?: return ""
        if (entries.first().timestamp > timestamp) return ""

        var left = 0
        var right = entries.size - 1

        var best = left
        // Goal: find the largest index i such that entries[i].timestamp <= timestamp
        while (left <= right) {
            if (left == right) {
                if (entries[left].timestamp <= timestamp) best = left
                break
            }
            val mid = (left + right) / 2
            val entry = entries[mid]
            if (entries[mid].timestamp <= timestamp) {
                best = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return entries[best].value
    }
}
