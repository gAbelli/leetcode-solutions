package leetcode

class FindAllPeopleWithSecret {
    class UnionFind(val size: Int, val knowsTheSecret: BooleanArray) {
        val parent = IntArray(size) { it }

        fun find(i: Int): Int {
            var k = i
            while (k != parent[k]) {
                parent[k] = parent[parent[k]]
                k = parent[k]
            }
            parent[i] = k
            return k
        }

        fun union(i: Int, j: Int) {
            val rootI = find(i)
            val rootJ = find(j)
            if (knowsTheSecret[rootI]) parent[rootJ] = rootI
            else parent[rootI] = rootJ
        }
    }

    fun findAllPeople(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
        val meetingsByTime = meetings.groupByTo(sortedMapOf()) { it[2] }
        val knowsTheSecret = BooleanArray(n) { false }.apply {
            set(0, true)
            set(firstPerson, true)
        }
        for ((_, meetings) in meetingsByTime) {
            // This line is not needed, but lowers the complexity from O(k^2) to O(n^2 + k) where k = meetings.size
            if (meetings.fold(false) { acc, meeting -> acc || knowsTheSecret[meeting[0]] != knowsTheSecret[meeting[1]] }) {
                val unionFind = UnionFind(n, knowsTheSecret)
                for (meeting in meetings) unionFind.union(meeting[0], meeting[1])
                (0..<n).forEach { knowsTheSecret[it] = knowsTheSecret[unionFind.find(it)] }
            }
        }
        return (0..<n).filter { knowsTheSecret[it] }
    }

    fun findAllPeopleQuadratic(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
        val meetingsByTime = meetings.groupByTo(sortedMapOf()) { it[2] }
        val peopleWhoKnowTheSecret = mutableSetOf(0, firstPerson)
        for ((_, meetings) in meetingsByTime) {
            if (peopleWhoKnowTheSecret.size == n) break
            while (true) {
                var somethingHappened = false
                for (meeting in meetings) {
                    val a = meeting[0]
                    val b = meeting[1]
                    if (peopleWhoKnowTheSecret.contains(a) != peopleWhoKnowTheSecret.contains(b)) {
                        peopleWhoKnowTheSecret.add(a)
                        peopleWhoKnowTheSecret.add(b)
                        somethingHappened = true
                    }
                }
                if (!somethingHappened) break
            }
        }
        return peopleWhoKnowTheSecret.toList()
    }
}
