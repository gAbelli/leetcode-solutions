package leetcode

class GasStation {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        if (cost.sum() > gas.sum()) return -1
        var bestIndex = 0
        var minGas = 0
        var curGas = 0
        for (i in gas.indices) {
            if (curGas < minGas) {
                bestIndex = i
                minGas = curGas
            }
            curGas += gas[i] - cost[i]
        }
        return bestIndex
    }
}
