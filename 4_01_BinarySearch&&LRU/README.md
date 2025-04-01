```markdown
# 算法题目解析
来源：leetcode hot 100 && 牛客面试101

## 1. 寻找峰值（Find Peak Element）

### 题目内容
给定一个整数数组 `nums`，其中 `nums[i] ≠ nums[i+1]`，找到任一峰值元素并返回其索引。峰值元素是指其值大于左右相邻值的元素。假设 `nums[-1] = nums[n] = -∞`。

### 解题思路
- **二分查找**：利用山峰特性，如果 `nums[mid] < nums[mid+1]`，说明峰值在右侧；否则在左侧。
- **边界处理**：数组为空或单元素时直接返回。

### 需要注意的点
- 题目保证相邻元素不相等，无需处理相等情况。
- 使用 `left < right` 可以避免无限循环。

### 代码
```java
public int findPeakElement(int[] nums) {
    if(nums==null||nums.length==0) return -1;
    if(nums.length==1) return 0;
    int left = 0, right = nums.length-1;
    while(left < right) {
        int mid = left + (right-left)/2;
        if(nums[mid] < nums[mid+1]) left = mid+1;
        else right = mid;
    }
    return left;
}
```

### 复杂度分析
- **时间复杂度**：O(log n)
- **空间复杂度**：O(1)

---

## 2. 数组中的逆序对（Inverse Pairs）

### 题目内容
计算数组中逆序对的总数（若 `i < j` 且 `nums[i] > nums[j]`，则为一个逆序对），结果对 1000000007 取模。

### 解题思路
- **归并排序**：在合并有序数组时统计逆序对数量。
- **取模防溢出**：每次更新计数器时取模。

### 需要注意的点
- 归并过程需要额外空间。
- 大数取模避免溢出。

### 代码
```java
private int count = 0;
private final int MOD = 1000000007;

public int inversePairs(int[] array) {
    if (array == null || array.length < 2) return 0;
    mergeSort(array, 0, array.length-1);
    return count;
}

private void mergeSort(int[] array, int left, int right) {
    if (left >= right) return;
    int mid = left + (right-left)/2;
    mergeSort(array, left, mid);
    mergeSort(array, mid+1, right);
    merge(array, left, mid, right);
}

private void merge(int[] array, int left, int mid, int right) {
    int[] temp = new int[right-left+1];
    int i = left, j = mid+1, k = 0;
    while (i <= mid && j <= right) {
        if (array[i] <= array[j]) temp[k++] = array[i++];
        else {
            temp[k++] = array[j++];
            count = (count + (mid-i+1)) % MOD;
        }
    }
    while (i <= mid) temp[k++] = array[i++];
    while (j <= right) temp[k++] = array[j++];
    System.arraycopy(temp, 0, array, left, temp.length);
}
```

### 复杂度分析
- **时间复杂度**：O(n log n)
- **空间复杂度**：O(n)

---

## 3. 旋转数组的最小数字（Min Number in Rotate Array）

### 题目内容
找出旋转排序数组中的最小元素（例如 `[3,4,5,1,2]` 的最小值是 1）。

### 解题思路
- **二分查找**：比较 `nums[mid]` 和 `nums[right]`：
    - 若 `nums[mid] > nums[right]`，最小值在右侧；
    - 若 `nums[mid] < nums[right]`，最小值在左侧；
    - 若相等，无法判断，右指针左移。

### 需要注意的点
- 处理重复元素（`nums[mid] == nums[right]` 时 `right--`）。
- 边界条件：数组长度为 0 或 1。

### 代码
```java
public int minNumberInRotateArray(int[] nums) {
    if(nums.length == 0) return 0;
    int i = 0, j = nums.length-1;
    while(i < j) {
        int mid = (i + j)/2;
        if(nums[mid] > nums[j]) i = mid+1;
        else if(nums[mid] < nums[j]) j = mid;
        else j--;
    }
    return nums[i];
}
```

### 复杂度分析
- **时间复杂度**：平均 O(log n)，最坏 O(n)（全重复元素）
- **空间复杂度**：O(1)

---

## 4. LRU 缓存（Least Recently Used）

### 题目内容
设计一个 LRU 缓存，支持 `get` 和 `put` 操作，容量满时淘汰最久未使用的数据。

### 解题思路
- **哈希表 + 双向链表**：
    - 哈希表快速定位节点；
    - 双向链表维护访问顺序（最近访问的放头部）。

### 需要注意的点
- 虚拟头尾节点简化边界操作。
- 操作时同步更新哈希表和链表。

### 代码
```java
class LRU {
    class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private HashMap<Integer, Node> cache = new HashMap<>();
    private Node head = new Node(0, 0), tail = new Node(0, 0);
    private int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        remove(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        } else if (cache.size() == capacity) {
            cache.remove(tail.prev.key);
            remove(tail.prev);
        }
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        addToHead(newNode);
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
```

### 复杂度分析
- **时间复杂度**：O(1)（哈希表和链表操作均摊）
- **空间复杂度**：O(n)（存储 n 个节点）
``` 
