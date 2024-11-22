package leetcode

class StickersToSpellWord {
    fun minStickers(stickers: Array<String>, target: String): Int {
        val isFeasible = run {
            val availableChars = stickers.asSequence().map { it.asSequence() }.flatten().toSet()
            target.all { availableChars.contains(it) }
        }
        if (!isFeasible) return -1

        val memo = mutableMapOf<List<Int>, Int>()

        fun helper(targetCounts: List<Int>): Int =
            if (targetCounts.all { it == 0 }) 0
            else memo.getOrPut(targetCounts) {
                stickers.minOf { word ->
                    var madeProgress = false
                    val newCounts = targetCounts.toMutableList()
                    for (c in word) if (newCounts[c - 'a'] != 0) {
                        newCounts[c - 'a'] -= 1
                        madeProgress = true
                    }
                    if (madeProgress) 1 + helper(newCounts)
                    else Int.MAX_VALUE
                }
            }

        val targetCounts = IntArray('z' - 'a' + 1) { 0 }.apply {
            for (c in target) set(c - 'a', get(c - 'a') + 1)
        }.toList()
        return helper(targetCounts)
    }

    fun minStickersBfs(stickers: Array<String>, target: String): Int {
        val isFeasible = run {
            val availableChars = stickers.asSequence().map { it.asSequence() }.flatten().toSet()
            target.asSequence().all { availableChars.contains(it) }
        }
        if (!isFeasible) return -1

        val queue = ArrayDeque<List<Int>>().apply {
            val targetCounts = IntArray('z' - 'a' + 1) { 0 }
            for (c in target) targetCounts[c - 'a'] += 1
            add(targetCounts.toList())
        }
        val seen = mutableSetOf(queue.first())
        var result = 0

        while (true) {
            result += 1
            val size = queue.size
            for (i in 0..<size) {
                val remainingCounts = queue.removeFirst()
                for (word in stickers) {
                    var madeProgress = false
                    val newCounts = remainingCounts.toMutableList()
                    for (c in word) if (newCounts[c - 'a'] != 0) {
                        newCounts[c - 'a'] -= 1
                        madeProgress = true
                    }
                    if (madeProgress) {
                        if (newCounts.all { it == 0 }) return result
                        if (!seen.contains(newCounts)) {
                            seen.add(newCounts)
                            queue.add(newCounts)
                        }
                    }
                }
            }
        }
    }
}
