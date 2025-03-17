# 项目名称
动态规划算法题、LRU缓存机制
题目来源：牛客网-模版速刷TOP101、 LeetCode Hot 100


## 题目&解题思路

语言：Java

### LRU缓存机制 (来自leetcode)

请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。

实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存

int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。

void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。

函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

### 解题思路
本题用哈希表+双向链表的方式去解题。这种组合当访问缓存中的某个键时，哈希表快速定位，双向链表快速将其移到链表头部，保持O(1)时间复杂度。

双向链表可以快速的插入和删除，并且能维护访问顺序。
哈希表可以快速查找键对，空间效率也很高，确保查找和更新操作的高效性。

这样我们通过key，可以快速找到相对应的位置。

get方法思路：
首先判断key是否存在：不存在，返回-1
存在，通过哈希表直接定位到此节点，并设置为最近被使用的节点放在链表头部，并返回值。

put方法思路：
同样首先判断key是否存在，
不存在，使用key和value创建一个新的节点，同样在链表头部添加改节点，并将key和该节点添加进
哈希表中。然后判断添加之后超出双向链表的最大容量。如果超出，删除尾部节点和哈希表对应的项。

存在的话，通过哈希表定位，并更新值，将节点移动到头部位置。

在这两个方法中，有需要相同的操作，例如：添加节点到头部，删除头部节点，删除节点，删除尾部节点。
这些封装成方法以便多次利用。

此题目需要重新塑造个数据结构（哈希表+双向链表）

首先自己构建双向链表节点+哈希键对：

class DLinkedNode{
     int key;      //存储键
     int value;    //存储值
     DLinkedNode prev;  //指向前一个节点的指针
     DLinkedNode next;  //指向后一个节点的指针
     public DLinkedNode(){}
     public DLinkedNode(int _key, int _value){
     key = _key;
     value = _value;
}
}

之后初始化LRUCache缓存，我们需要的属性有：
head，tail这是两个节点相互连接作为链表的伪头部和伪尾部；
链表初始长度size = 0；
链表的容量capacity = capacity；可以作为参数设置

private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
private int size;
private int capacity;
private DLinkedNode head, tail;

public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
}

### BM68.矩阵的最小路径和

### 解题思路

动态规划核心思想：动态规划算法的基本思想是：将待求解的问题分解成若干个相互联系的子问题，先求解子问题，
然后从这些子问题的解得到原问题的解；对于重复出现的子问题，只在第一次遇到的时候对它进行求解，并把答案保存起来，
让以后再次遇到时直接引用答案，不必重新求解。动态规划算法将问题的解决方案视为一系列决策的结果。（要注意边界检测）

首先建立动态规划数组；dp[i][j],i和j分别代表行和列
先思考边界：假如只有一行或者一列，那么路径只有一条，我们可以先给dp[i][0]和dp[0][j]赋值。

在思考到最右下角，每次只有两种选择向下或者向右走，如果要到位置为（i，j),那么只要找到（i-1,j）和（i,j-1）的
最小路径和其中最小的一个。因此，我们得到状态转移方程为：

dp[i][j] == min(dp[i-1][j], dp[i][j-1])

最后返回dp[n-1][m-1]（n、m为二维数组的行数和列数）




