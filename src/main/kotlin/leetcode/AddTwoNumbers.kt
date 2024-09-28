package leetcode

import leetcode.datastructures.ListNode

class AddTwoNumbers {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1 = l1
        var l2 = l2
        val dummy = ListNode(0)
        var cur: ListNode? = dummy
        var carry = 0
        while (l1 != null || l2 != null) {
            val x = l1?.`val` ?: 0
            val y = l2?.`val` ?: 0
            var sum = x + y + carry
            carry = 0
            if (sum >= 10) {
                sum -= 10
                carry = 1
            }
            cur!!.next = ListNode(sum)
            cur = cur.next
            l1 = l1?.next
            l2 = l2?.next
        }
        if (carry != 0) {
            cur!!.next = ListNode(1)
        }

        return dummy.next
    }
}
