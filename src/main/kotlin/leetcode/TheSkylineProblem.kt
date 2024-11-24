package leetcode

class TheSkylineProblem {
    enum class Action {
        Start, Finish
    }

    fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
        val statesByX = mutableListOf<Triple</* x */ Int, /* height */ Int, Action>>().apply {
            for (building in buildings) {
                add(Triple(building[0], building[2], Action.Start))
                add(Triple(building[1], building[2], Action.Finish))
            }
        }.groupByTo(sortedMapOf()) { it.first }

        val result = mutableListOf<MutableList<Int>>()
        val heights = sortedMapOf<Int, Int>()
        for ((x, states) in statesByX) {
            for ((_, height, action) in states) when (action) {
                Action.Start -> {
                    heights[height] = heights.getOrDefault(height, 0) + 1
                }

                Action.Finish -> {
                    if (heights[height] == 1) heights.remove(height)
                    else heights[height] = heights[height]!! - 1
                }
            }
            val newBestHeight = if (heights.isEmpty()) 0 else heights.lastKey()
            if (result.isEmpty() || result.last()[1] != newBestHeight) result.add(mutableListOf(x, newBestHeight))
        }
        return result
    }
}
