package leetcode

import java.util.PriorityQueue

class ZeroArrayTransformationIii {
    // newNums[i] < nums[i]
    // newNums[i] = diffs[0] + diffs[1] + ... + diffs[i]
    fun maxRemoval(nums: IntArray, queries: Array<IntArray>): Int {
        val n = nums.size
        val queriesByLeftIndex = Array(n) { mutableListOf<Pair<Int, Int>>() }
        for (query in queries) queriesByLeftIndex[query[0]].add(Pair(query[0], query[1]))
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { -it.second })
        val diffs = IntArray(n + 1) { 0 }
        var curSum = 0 // equals newNums[i]
        for (i in nums.indices) {
            pq.addAll(queriesByLeftIndex[i])
            curSum += diffs[i]
            while (curSum < nums[i]) {
                if (pq.isEmpty()) return -1
                val query = pq.poll()
                if (query.second < i) return -1
                curSum += 1
                diffs[query.first] += 1
                diffs[query.second + 1] -= 1
            }
        }
        return pq.size
    }

    fun maxRemovalSlow(nums: IntArray, queries: Array<IntArray>): Int {
        val queriesByLeftIndex = Array(nums.size) { mutableListOf<Pair<Int, Int>>() }
        for (query in queries) queriesByLeftIndex[query[0]].add(Pair(query[0], query[1]))
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { -it.second })
        for (i in nums.indices) {
            pq.addAll(queriesByLeftIndex[i])
            while (nums[i] > 0) {
                if (pq.isEmpty()) return -1
                val query = pq.poll()
                if (query.second < i) return -1
                for (j in query.first..query.second) nums[j] -= 1
            }
        }
        return pq.size
    }
}
