package leetcode

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class FindTheMaximumSumOfNodeValues {
    // Color a node in blue if it increases after xoring with k, or in red if it decreases.
    // Selecting an edge means changing the color of both its adjacent nodes.
    // The question is: can we make all nodes red? The answer in general is no because the number
    // of blue nodes and red nodes is constant mod 2. But the next best thing is achievable:
    // we can make nums.size - (nums.size % 2) nodes red. The strategy is that if there are
    // two adjacent blue nodes, we can turn them both red, and if there aren't we can just
    // take the two closest blue nodes and make them adjacent by repeatedly exchanging one of
    // the two with a red node along the path that connects them (think of Candy Crush).
    // Notice that:
    // * We could replace xor with any associative, commutative operation that is the inverse of itself
    // * This solution works for any connected graph, not necessarily a tree
    fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
        var sum = 0L
        var blueNodes = 0
        var minLoss = Int.MAX_VALUE
        for (x in nums) {
            val xXorK = x xor k
            sum += max(x, xXorK)
            if (x < xXorK) blueNodes += 1
            minLoss = min(minLoss, abs(x - xXorK))
        }
        return if (blueNodes % 2 == 0) sum
        else sum - minLoss
    }
}
