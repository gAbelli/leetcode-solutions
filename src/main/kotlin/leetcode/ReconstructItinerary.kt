package leetcode

class ReconstructItinerary {
    fun findItinerary(tickets: List<List<String>>): List<String> {
        val stringToInt = mutableMapOf<String, Int>()
        val intToString = mutableListOf<String>()
        var cur = 0

        fun addIfMissing(s: String) {
            if (s in stringToInt) return
            stringToInt[s] = cur
            intToString.add(s)
            cur++
        }

        tickets.forEach {
            addIfMissing(it[0])
            addIfMissing(it[1])
        }

        val comparatorArray = IntArray(cur) { 0 }
        for (indexedValue in (0..<cur).sortedBy { intToString[it] }.withIndex()) {
            comparatorArray[indexedValue.value] = indexedValue.index
        }

        return findItineraryInts(
            tickets.map { it.map { stringToInt[it]!! } },
            stringToInt["JFK"]!!,
            compareBy { comparatorArray[it] }
        ).map { intToString[it] }
    }

    private fun findItineraryInts(
        tickets: List<List<Int>>,
        indexOfJfk: Int,
        alphabeticalComparator: Comparator<Int>
    ): List<Int> {
        val ticketsFrom = tickets
            .groupBy { it[0] }
            .mapValues { (_, l) ->
                l.map { it[1] }.groupingBy { it }.eachCount().toSortedMap(alphabeticalComparator)
            }

        val acc = mutableListOf(indexOfJfk)
        var usedTickets = 0

        fun dfs(start: Int): List<Int>? {
            if (usedTickets == tickets.size) return acc
            val ticketsFromStart = ticketsFrom.getOrDefault(start, sortedMapOf(alphabeticalComparator))
            for (destinationEntry in ticketsFromStart) {
                val destination = destinationEntry.key
                val count = destinationEntry.value
                if (count > 0) {
                    ticketsFromStart[destination] = ticketsFromStart[destination]!! - 1
                    acc.add(destination)
                    usedTickets += 1
                    val result = dfs(destination)
                    if (result != null) return result
                    usedTickets -= 1
                    acc.removeLast()
                    ticketsFromStart[destination] = ticketsFromStart.getOrPut(destination) { 0 } + 1
                }
            }
            return null
        }

        return dfs(indexOfJfk)!!
    }

    fun findItineraryStraightforward(tickets: List<List<String>>): List<String> {
        val ticketsFrom = tickets
            .groupBy { it[0] }
            .mapValues { (_, l) ->
                l.map { it[1] }.groupingBy { it }.eachCount().toSortedMap()
            }

        val acc = mutableListOf("JFK")
        var usedTickets = 0

        fun dfs(start: String): List<String>? {
            if (usedTickets == tickets.size) return acc
            val ticketsFromStart = ticketsFrom.getOrDefault(start, sortedMapOf())
            for (destinationEntry in ticketsFromStart) {
                val destination = destinationEntry.key
                val count = destinationEntry.value
                if (count > 0) {
                    ticketsFromStart[destination] = ticketsFromStart[destination]!! - 1
                    acc.add(destination)
                    usedTickets += 1
                    val result = dfs(destination)
                    if (result != null) return result
                    usedTickets -= 1
                    acc.removeLast()
                    ticketsFromStart[destination] = ticketsFromStart.getOrPut(destination) { 0 } + 1
                }
            }
            return null
        }

        return dfs("JFK")!!
    }
}
