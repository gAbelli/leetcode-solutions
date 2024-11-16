package leetcode

import java.util.PriorityQueue

class NumberOfFlowersInFullBloom {
    fun fullBloomFlowers(flowers: Array<IntArray>, people: IntArray): IntArray {
        flowers.sortBy { it[0] }
        val sortedPeople = people.withIndex().sortedBy { it.value }
        val pq = PriorityQueue<Int>()
        val result = IntArray(people.size) { 0 }

        var curBloomingFlowers = 0
        var flowerIndex = 0
        for ((index, t) in sortedPeople) {
            while (flowerIndex < flowers.size && flowers[flowerIndex][0] <= t) {
                curBloomingFlowers += 1
                pq.add(flowers[flowerIndex][1] + 1)
                flowerIndex += 1
            }
            while (pq.isNotEmpty() && pq.peek() <= t) {
                curBloomingFlowers -= 1
                pq.poll()
            }
            result[index] = curBloomingFlowers
        }

        return result
    }

    enum class Action {
        StartBloom, EndBloom, PersonArrival
    }

    fun fullBloomFlowersStateMachine(flowers: Array<IntArray>, people: IntArray): IntArray {
        val stateMachine = mutableListOf<Triple</* time */ Int, Action, /* index */ Int>>()
        for (flower in flowers) {
            stateMachine.add(Triple(flower[0], Action.StartBloom, 0))
            stateMachine.add(Triple(flower[1] + 1, Action.EndBloom, 0))
        }
        for ((index, person) in people.withIndex()) {
            stateMachine.add(Triple(person, Action.PersonArrival, index))
        }
        stateMachine.sortWith(compareBy<Triple<Int, Action, Int>> { it.first }.thenBy { it.second })

        val result = IntArray(people.size)
        var curBloomingFlowers = 0
        for ((_, action, index) in stateMachine) {
            when (action) {
                Action.StartBloom -> curBloomingFlowers++
                Action.EndBloom -> curBloomingFlowers--
                Action.PersonArrival -> result[index] = curBloomingFlowers
            }
        }

        return result
    }
}
