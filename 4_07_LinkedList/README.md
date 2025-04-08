```markdown
# 链表与字符串经典问题解析
来源leetcode

## 1. 合并两个有序链表 (TwoLinkedListsMerge)

### 题目内容
将两个升序链表合并为一个新的升序链表并返回。

### 解题思路
- **递归法**：比较两个链表头节点的值，较小的节点作为合并后链表的头节点，然后递归处理剩余部分。

### 注意点
- 递归终止条件：任一链表为空时直接返回另一链表
- 递归调用时注意连接节点关系

### 代码
```java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) return list2;
    if (list2 == null) return list1;
    
    if (list1.val < list2.val) {
        list1.next = mergeTwoLists(list1.next, list2);
        return list1;
    } else {
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }
}
```

### 复杂度分析
- 时间复杂度：O(n+m)
- 空间复杂度：O(n+m)（递归栈空间）

## 2. 分隔链表 (PartitionLinkedList)

### 题目内容
给定一个链表和一个特定值 x，将链表分隔成两部分，所有小于 x 的节点排在大于或等于 x 的节点之前。

### 解题思路
- 创建两个虚拟头节点分别连接小值和大值节点
- 遍历原链表，根据值大小分配到不同子链表
- 最后连接两个子链表

### 注意点
- 大值链表末尾需要置null防止环
- 虚拟头节点简化边界处理

### 代码
```java
public ListNode partition(ListNode head, int x) {
    ListNode small = new ListNode();
    ListNode large = new ListNode();
    ListNode smallHead = small, largeHead = large;
    
    while (head != null) {
        if (head.val < x) {
            small.next = head;
            small = small.next;
        } else {
            large.next = head;
            large = large.next;
        }
        head = head.next;
    }
    large.next = null;
    small.next = largeHead.next;
    return smallHead.next;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(1)

## 3. 合并K个升序链表 (MergeKLists)

### 题目内容
合并k个排序链表，返回合并后的排序链表。

### 解题思路
- **优先队列**：使用最小堆维护当前所有链表的头节点
- 每次取出最小节点，将其后继节点加入堆中

### 注意点
- 优先队列的Comparator定义
- 处理空链表情况

### 代码
```java
public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
    for (ListNode node : lists) {
        if (node != null) pq.offer(node);
    }
    
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    while (!pq.isEmpty()) {
        ListNode min = pq.poll();
        curr.next = min;
        curr = curr.next;
        if (min.next != null) pq.offer(min.next);
    }
    return dummy.next;
}
```

### 复杂度分析
- 时间复杂度：O(Nlogk)
- 空间复杂度：O(k)

## 4. 相交链表 (GetIntersectionNode)

### 题目内容
找到两个单链表相交的起始节点。

### 解题思路
- **双指针法**：指针A遍历链表A后转链表B，指针B同理
- 两指针必然在相交点相遇（或同时到达末尾）

### 注意点
- 处理不相交的情况
- 循环终止条件

### 代码
```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode pA = headA, pB = headB;
    while (pA != pB) {
        pA = pA == null ? headB : pA.next;
        pB = pB == null ? headA : pB.next;
    }
    return pA;
}
```

### 复杂度分析
- 时间复杂度：O(m+n)
- 空间复杂度：O(1)

## 5. 删除链表的倒数第N个节点 (DeleteLastKthNode)

### 题目内容
删除链表的倒数第n个节点。

### 解题思路
- **快慢指针**：快指针先走n步，然后同步移动直到快指针到达末尾

### 注意点
- 处理删除头节点的情况
- 虚拟头节点简化操作

### 修正代码
```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode fast = dummy, slow = dummy;
    
    for (int i = 0; i < n; i++) {
        fast = fast.next;
    }
    
    while (fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }
    slow.next = slow.next.next;
    return dummy.next;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(1)

## 6. 环形链表检测 (FindCycleLinkedList)

### 题目内容
检测链表是否有环，并返回环的起点。

### 解题思路
- **快慢指针**：快指针每次两步，慢指针每次一步
- 相遇后，将慢指针重置到头节点，两指针同步移动直到再次相遇

### 注意点
- 边界条件处理（空链表或单节点）
- 第二次相遇点即为环起点

### 代码
```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
    return null;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(1)

## 7. 无重复字符的最长子串 (NoDupliacateLongestString)

### 题目内容
找出字符串中不含有重复字符的最长子串的长度。

### 解题思路
- **滑动窗口**：用HashMap记录字符最后出现位置
- 维护窗口左边界，更新最大长度

### 注意点
- 字符重复时的窗口收缩
- HashMap的更新时机

### 代码
```java
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int left = -1, res = 0;
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        if (map.containsKey(c)) {
            left = Math.max(left, map.get(c));
        }
        map.put(c, right);
        res = Math.max(res, right - left);
    }
    return res;
}
```

### 复杂度分析
- 时间复杂度：O(n)
- 空间复杂度：O(min(m,n))，字符集大小或字符串长度
```