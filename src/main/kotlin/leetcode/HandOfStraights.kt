package leetcode

class HandOfStraights {
    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
        if (hand.size % groupSize != 0) return false
        val cardCounts = hand.toTypedArray().groupingBy { it }.eachCountTo(mutableMapOf())
        val sortedCards = ArrayDeque<Int>().apply {
            addAll(cardCounts.keys)
            sort()
        }
        while (sortedCards.isNotEmpty()) {
            val firstCard = sortedCards.first()
            if (cardCounts[firstCard]!! == 0) {
                sortedCards.removeFirst()
                continue
            }
            for (i in 0..<groupSize) {
                if (cardCounts.getOrDefault(firstCard + i, 0) == 0) return false
                cardCounts[firstCard + i] = cardCounts[firstCard + i]!! - 1
            }
        }
        return true
    }
}
