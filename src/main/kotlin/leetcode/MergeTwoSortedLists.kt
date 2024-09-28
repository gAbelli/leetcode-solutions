package leetcode

import leetcode.datastructures.ListNode

class MergeTwoSortedLists {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var list1 = list1
        var list2 = list2

        val dummy = ListNode(0)
        var cur = dummy

        while (list1 != null && list2 != null) {
            val x = list1.`val`
            val y = list2.`val`
            if (x <= y) {
                cur.next = ListNode(x)
                list1 = list1.next
            } else {
                cur.next = ListNode(y)
                list2 = list2.next
            }
            cur = cur.next!!
        }
        while (list1 != null) {
            cur.next = ListNode(list1.`val`)
            list1 = list1.next
            cur = cur.next!!
        }
        while (list2 != null) {
            cur.next = ListNode(list2.`val`)
            list2 = list2.next
            cur = cur.next!!
        }

        return dummy.next
    }
}
