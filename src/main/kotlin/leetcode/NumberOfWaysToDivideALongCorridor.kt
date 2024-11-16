package leetcode

class NumberOfWaysToDivideALongCorridor {
    private val MOD = 1_000_000_000 + 7
    private fun (Long).trim(): Long = (this % MOD + MOD) % MOD

    fun numberOfWays(corridor: String): Int {
        val seatsCount = corridor.count { it == 'S' }
        if (seatsCount == 0 || seatsCount % 2 == 1) return 0

        tailrec fun helper(i: Int, acc: Long): Long {
            var seatsCount = 0
            var endingPlants = 0
            for (j in i..<corridor.length) {
                val c = corridor[j]
                if (c == 'S') {
                    if (seatsCount == 2) {
                        val newAcc = (acc * (endingPlants + 1)).trim()
                        return helper(j, newAcc)
                    } else seatsCount += 1
                } else {
                    if (seatsCount == 2) endingPlants += 1
                }
            }
            return acc.trim()
        }

        return helper(0, 1).trim().toInt()
    }
}
