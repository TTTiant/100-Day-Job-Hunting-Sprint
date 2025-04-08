public class PartitionLinkedList {
    //LeetCode 86 分个链表
    class ListNode {
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode();
        ListNode smallHead = small;
        ListNode large = new ListNode();
        ListNode largeHead = large;

        while(head!=null){
            if(head.val < x){
                small.next = head; //把small节点连接到head上，不是把head取下来
                small = small.next;

            }else{
                large.next = head;
                large =large.next;

            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
