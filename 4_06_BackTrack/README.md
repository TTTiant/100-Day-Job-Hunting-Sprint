### 分类整理

#### 1. 回溯算法
- **题目**：组合总和、电话号码的字母组合、括号生成、子集
- **特点**：通过递归尝试所有可能的解，并通过剪枝优化效率。

#### 2. 二叉树
- **题目**：从前序与中序遍历序列构造二叉树
- **特点**：涉及二叉树的构建和遍历。

#### 3. 设计数据结构
- **题目**：LRU缓存机制、Trie树（前缀树）
- **特点**：需要设计高效的数据结构以满足特定操作需求。

#### 4. 其他
- **题目**：无
- **特点**：无

---

### 详细分类与解析

#### 1. 回溯算法

##### 题目 1：组合总和
- **题目内容**：给定一个无重复元素的数组 `candidates` 和一个目标数 `target`，找出所有可以使数字和为 `target` 的组合。数组中的数字可以无限制重复选取。
- **解题思路**：
    - 排序数组以便剪枝。
    - 使用回溯法，从当前索引开始尝试添加元素，递归搜索可能的组合。
    - 通过 `target - candidates[i]` 更新剩余目标值。
- **注意点**：
    - 避免重复组合（通过固定搜索起点 `index`）。
    - 剪枝条件：当前元素大于剩余目标值时跳过。
- **不常用语法**：无。
- **代码**：
  ```java
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
      Arrays.sort(candidates);
      List<Integer> list = new ArrayList<>();
      backTrack(candidates, 0, target, list);
      return res;
  }
  private void backTrack(int[] candidates, int index, int target, List<Integer> list) {
      if (target == 0) {
          res.add(new ArrayList<>(list));
          return;
      }
      for (int i = index; i < candidates.length; i++) {
          if (candidates[i] > target) continue;
          list.add(candidates[i]);
          backTrack(candidates, i, target - candidates[i], list);
          list.remove(list.size() - 1);
      }
  }
  ```
- **复杂度分析**：
    - 时间：O(2^n)，最坏情况下需要遍历所有子集。
    - 空间：O(target)，递归栈深度。

##### 题目 2：电话号码的字母组合
- **题目内容**：给定一个数字字符串，返回所有可能的字母组合（数字到字母的映射参考电话按键）。
- **解题思路**：
    - 使用回溯法，递归生成所有可能的字母组合。
    - 维护一个 `StringBuilder` 动态构建字符串。
- **注意点**：
    - 数字到字母的映射需要正确处理（如 `2 -> "abc"`）。
    - 递归终止条件为字符串长度等于输入数字长度。
- **不常用语法**：`StringBuilder` 的 `deleteCharAt` 方法用于回溯。
- **代码**：
  ```java
  public List<String> letterCombinations(String digits) {
      if (digits.isEmpty()) return ans;
      backTrack(0, digits, new StringBuilder());
      return ans;
  }
  private void backTrack(int i, String digits, StringBuilder s) {
      if (i == digits.length()) {
          ans.add(s.toString());
          return;
      }
      for (char c : map[digits.charAt(i) - '0'].toCharArray()) {
          s.append(c);
          backTrack(i + 1, digits, s);
          s.deleteCharAt(s.length() - 1);
      }
  }
  ```
- **复杂度分析**：
    - 时间：O(3^m × 4^n)，其中 `m` 是映射到 3 个字母的数字个数，`n` 是映射到 4 个字母的数字个数。
    - 空间：O(m + n)，递归栈深度。

##### 题目 3：括号生成
- **题目内容**：生成所有有效的括号组合，`n` 对括号。
- **解题思路**：
    - 回溯法，通过维护当前左右括号的数量生成有效组合。
    - 剪枝条件：右括号数量不能超过左括号。
- **注意点**：
    - 必须保证任意前缀中左括号数量 ≥ 右括号数量。
- **不常用语法**：`StringBuilder` 的 `deleteCharAt` 方法用于回溯。
- **代码**：
  ```java
  public List<String> generateParenthesis(int n) {
      backTrack(new StringBuilder(), 0, 0, n);
      return res;
  }
  private void backTrack(StringBuilder current, int open, int close, int max) {
      if (current.length() == 2 * max) {
          res.add(current.toString());
          return;
      }
      if (open < max) {
          current.append('(');
          backTrack(current, open + 1, close, max);
          current.deleteCharAt(current.length() - 1);
      }
      if (close < open) {
          current.append(')');
          backTrack(current, open, close + 1, max);
          current.deleteCharAt(current.length() - 1);
      }
  }
  ```
- **复杂度分析**：
    - 时间：O(4^n / √n)，卡特兰数的渐进上界。
    - 空间：O(n)，递归栈深度。

##### 题目 4：子集
- **题目内容**：给定一组无重复元素的整数数组，返回所有可能的子集。
- **解题思路**：
    - 回溯法，递归生成所有子集。
    - 每次递归从当前索引的下一个开始，避免重复。
- **注意点**：
    - 空集是任何集合的子集。
- **不常用语法**：无。
- **代码**：
  ```java
  public List<List<Integer>> subsets(int[] nums) {
      backTrack(nums, 0, new ArrayList<>());
      return res;
  }
  private void backTrack(int[] nums, int index, List<Integer> list) {
      res.add(new ArrayList<>(list));
      for (int i = index; i < nums.length; i++) {
          list.add(nums[i]);
          backTrack(nums, i + 1, list);
          list.remove(list.size() - 1);
      }
  }
  ```
- **复杂度分析**：
    - 时间：O(n × 2^n)，生成所有子集。
    - 空间：O(n)，递归栈深度。

#### 2. 二叉树

##### 题目：从前序与中序遍历序列构造二叉树
- **题目内容**：根据一棵树的前序和中序遍历序列构造二叉树。
- **解题思路**：
    - 前序遍历的第一个元素是根节点。
    - 在中序遍历中找到根节点，分割左右子树。
    - 递归构建左右子树。
- **注意点**：
    - 需要正确处理数组边界（`preStart`、`preEnd` 等）。
- **不常用语法**：无。
- **代码**：
  ```java
  public TreeNode buildTree(int[] preorder, int[] inorder) {
      return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }
  private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
      if (preStart > preEnd || inStart > inEnd) return null;
      int rootVal = preorder[preStart];
      TreeNode root = new TreeNode(rootVal);
      int inOrderRootIndex = findIndex(inorder, inStart, inEnd, rootVal);
      int leftTreeSize = inOrderRootIndex - inStart;
      root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftTreeSize, inorder, inStart, inOrderRootIndex - 1);
      root.right = buildTreeHelper(preorder, preStart + leftTreeSize + 1, preEnd, inorder, inOrderRootIndex + 1, inEnd);
      return root;
  }
  private int findIndex(int[] arr, int start, int end, int target) {
      for (int i = start; i <= end; i++) {
          if (arr[i] == target) return i;
      }
      return -1;
  }
  ```
- **复杂度分析**：
    - 时间：O(n)，每个节点处理一次。
    - 空间：O(n)，递归栈深度。

#### 3. 设计数据结构

##### 题目 1：LRU缓存机制
- **题目内容**：设计一个 LRU（最近最少使用）缓存机制。
- **解题思路**：
    - 使用哈希表 + 双向链表实现。
    - 哈希表存储键值对，双向链表维护访问顺序。
- **注意点**：
    - 需要处理节点的插入、删除和移动操作。
- **不常用语法**：自定义双向链表节点类。
- **代码**：
  ```java
  class LRUCache {
      class DLinkedNode {
          int key, val;
          DLinkedNode prev, next;
          DLinkedNode() {}
          DLinkedNode(int key, int val) { this.key = key; this.val = val; }
      }
      private Map<Integer, DLinkedNode> cache = new HashMap<>();
      private int size, capacity;
      private DLinkedNode head, tail;

      public LRUCache(int capacity) {
          this.capacity = capacity;
          head = new DLinkedNode();
          tail = new DLinkedNode();
          head.next = tail;
          tail.prev = head;
      }
      public int get(int key) {
          DLinkedNode node = cache.get(key);
          if (node == null) return -1;
          moveToHead(node);
          return node.val;
      }
      public void put(int key, int value) {
          DLinkedNode node = cache.get(key);
          if (node == null) {
              node = new DLinkedNode(key, value);
              cache.put(key, node);
              addToHead(node);
              if (++size > capacity) {
                  DLinkedNode tailNode = removeTail();
                  cache.remove(tailNode.key);
                  size--;
              }
          } else {
              node.val = value;
              moveToHead(node);
          }
      }
      private void addToHead(DLinkedNode node) {
          node.prev = head;
          node.next = head.next;
          head.next.prev = node;
          head.next = node;
      }
      private void removeNode(DLinkedNode node) {
          node.prev.next = node.next;
          node.next.prev = node.prev;
      }
      private void moveToHead(DLinkedNode node) {
          removeNode(node);
          addToHead(node);
      }
      private DLinkedNode removeTail() {
          DLinkedNode node = tail.prev;
          removeNode(node);
          return node;
      }
  }
  ```
- **复杂度分析**：
    - 时间：O(1)，哈希表和双向链表操作均为常数时间。
    - 空间：O(capacity)，存储缓存内容。

##### 题目 2：Trie树（前缀树）
- **题目内容**：实现一个 Trie 树，支持插入、搜索和前缀匹配。
- **解题思路**：
    - 使用多叉树结构，每个节点包含子节点映射和单词结束标志。
- **注意点**：
    - 需要处理字符到节点的映射。
- **不常用语法**：`Map<Character, TrieNode>` 用于动态子节点管理。
- **代码**：
  ```java
  class Trie {
      class TrieNode {
          Map<Character, TrieNode> children = new HashMap<>();
          boolean isEnd = false;
      }
      private TrieNode root;
      public Trie() { root = new TrieNode(); }
      public void insert(String word) {
          TrieNode node = root;
          for (char c : word.toCharArray()) {
              node.children.putIfAbsent(c, new TrieNode());
              node = node.children.get(c);
          }
          node.isEnd = true;
      }
      public boolean search(String word) {
          TrieNode node = searchPrefix(word);
          return node != null && node.isEnd;
      }
      public boolean startsWith(String prefix) {
          return searchPrefix(prefix) != null;
      }
      private TrieNode searchPrefix(String prefix) {
          TrieNode node = root;
          for (char c : prefix.toCharArray()) {
              if (!node.children.containsKey(c)) return null;
              node = node.children.get(c);
          }
          return node;
      }
  }
  ```
- **复杂度分析**：
    - 时间：O(L)，L 为单词或前缀长度。
    - 空间：O(N × M)，N 为单词数，M 为平均长度。

---

