import java.util.PriorityQueue;

public class MergeKLists {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }

        public ListNode MergeKLists(ListNode[] lists) {
            if (lists == null||lists.length == 0) return null;
            //定义个小根堆
            PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->b.val - a.val);

            // 将所有链表的头节点加入优先队列
            for (ListNode node  : lists) {
                if (node != null) {
                    pq.offer(node);
                }

            }
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            while (!pq.isEmpty()) {
                ListNode smallest = pq.poll();
                current.next = smallest;
                current = current.next;

                if (smallest.next != null) {
                    pq.offer(smallest.next);
                }
            }
            return dummy.next;

        }
    }
}
