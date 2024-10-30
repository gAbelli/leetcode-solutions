package leetcode

import kotlin.math.max
import kotlin.math.min

class MedianOfTwoSortedArrays {
    fun (IntArray).safeGet(i: Int): Int =
        if (i < 0) Int.MIN_VALUE
        else if (i >= size) Int.MAX_VALUE
        else get(i)

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size) return findMedianSortedArrays(nums2, nums1)

        val isEven = (nums1.size + nums2.size) % 2 == 0
        val leftHalfSize = (nums1.size + nums2.size) / 2
        fun other(i: Int): Int = leftHalfSize - i - 2
        fun result(mid: Int): Double =
            if (isEven) {
                val left = max(nums1.safeGet(mid), nums2.safeGet(other(mid)))
                val right = min(nums1.safeGet(mid + 1), nums2.safeGet(other(mid) + 1))
                (left + right).toDouble() / 2
            } else min(nums1.safeGet(mid + 1), nums2.safeGet(other(mid) + 1)).toDouble()


        var left = -1
        var right = nums1.size

        while (true) {
            val mid = left + (right - left) / 2
            if (
                nums1.safeGet(mid) <= nums2.safeGet(other(mid) + 1)
                && nums2.safeGet(other(mid)) <= nums1.safeGet(mid + 1)
            ) return result(mid)
            if (nums1.safeGet(mid) > nums2.safeGet(other(mid) + 1)) right = mid
            else left = mid + 1
        }
    }
}
