import java.util.HashMap;

public class LRUCache {
    class DLinkedNode {
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode(int key,int val) {
            this.key = key;
            this.val = val;
        }
        public DLinkedNode() {}

    }
    HashMap<Integer, DLinkedNode> cache = new HashMap<>();

    int capacity;
    int size;
    DLinkedNode head;
    DLinkedNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;

    }
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;
        removeNode(node);
        addToHead(node);

        return node.val;
    }
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        //如果不存在这个node，则需要创造并且添加，并检查长度有没有超过阈值，超过要删除尾部节点
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                DLinkedNode old = tail.prev;
                removeNode(old);
                cache.remove(old.key);
                size--;
            }
        }else{ //如果存在，直接改value，并移动到头部
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
    }

    public void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 返回 1
        cache.put(3, 3); // 淘汰 key 2
        System.out.println(cache.get(2)); // 返回 -1 (未找到)
        cache.put(4, 4); // 淘汰 key 1
        System.out.println(cache.get(1)); // 返回 -1 (未找到)
        System.out.println(cache.get(3)); // 返回 3
        System.out.println(cache.get(4)); // 返回 4
    }

}
