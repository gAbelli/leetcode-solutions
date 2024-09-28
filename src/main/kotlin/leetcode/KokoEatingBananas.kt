package leetcode

class KokoEatingBananas {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var left = 1
        var right = piles.max()
        var best = right

        fun enough(k: Int) =
            piles.asSequence().map { if (it % k == 0) it / k else it / k + 1 }.map { it.toLong() }.sum() <= h

        while (left <= right) {
            if (left == right) {
                if (enough(left)) {
                    best = left
                }
                break
            }

            val mid = (left + right) / 2
            if (enough(mid)) {
                best = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return best
    }
}
