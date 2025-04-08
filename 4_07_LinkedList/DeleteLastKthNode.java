public class DeleteLastKthNode {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {}
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = head;
        //设置双指针
        ListNode left = dummy;
        ListNode right = dummy;
        //让right指针先走n步
        while(n>0){
            right = right.next;
            n--;
        }
        if(right == null) return head.next;

        //在让两指针同步走，直到right到最后，此时left的下一个就是要删除的节点
        while(right.next != null){
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummy;
    }
}
