package leetcode

class FindInMountainArray {
    interface MountainArray {
        fun get(index: Int): Int
        fun length(): Int
    }

    fun findInMountainArray(target: Int, mountainArr: MountainArray): Int {
        val n = mountainArr.length()
        val peakIndex = run {
            var left = 1
            var right = n - 2
            while (left < right) {
                val mid = (left + right + 1) / 2
                if (mountainArr.get(mid) > mountainArr.get(mid - 1)) left = mid
                else right = mid - 1
            }
            left
        }
        if (mountainArr.get(peakIndex) == target) return peakIndex

        run {
            var left = 0
            var right = peakIndex - 1
            while (left <= right) {
                val mid = (left + right) / 2
                val cmp = mountainArr.get(mid).compareTo(target)
                if (cmp == 0) return mid
                else if (cmp < 0) left = mid + 1
                else right = mid - 1
            }
        }

        run {
            var left = peakIndex + 1
            var right = n - 1
            while (left <= right) {
                val mid = (left + right) / 2
                val cmp = mountainArr.get(mid).compareTo(target)
                if (cmp == 0) return mid
                else if (cmp > 0) left = mid + 1
                else right = mid - 1
            }
        }

        return -1
    }
}
