package leetcode

class MergeTripletsToFormTargetTriplet {
    fun mergeTriplets(triplets: Array<IntArray>, target: IntArray): Boolean {
        var foundX = false
        var foundY = false
        var foundZ = false
        for (triplet in triplets) {
            val (x, y, z) = Triple(triplet[0], triplet[1], triplet[2])
            if (x == target[0] && y <= target[1] && z <= target[2]) foundX = true
            if (x <= target[0] && y == target[1] && z <= target[2]) foundY = true
            if (x <= target[0] && y <= target[1] && z == target[2]) foundZ = true
        }
        return foundX && foundY && foundZ
    }
}
