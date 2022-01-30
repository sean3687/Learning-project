package com.tassiecomp.practicecode

fun main(args: Array<String>){
    println("Hello World!!!")
}

var li = ListNode(5)
var v = li.`val`

class ListNode(var `val`: Int) {
     var next: ListNode? = null
}

class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        return head?.next?.let {
            reverseList(it).apply {
                head.next.next = head;
                head.next = null;
            }
        } ?: head
    }
}