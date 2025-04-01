import java.util.HashMap;

public class LRU {
    //最近最少使用算法
    //也是最久未使用

    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private HashMap<Integer, Node> cache = new HashMap<>();
    private Node head= new Node(0, 0);
    private Node tail= new Node(0, 0);
    private int capacity;

    public LRU(int capacity){
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node  = cache.get(key);
        remove(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }else if(cache.size()==capacity){
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

    public static void main(String[] args) {
        LRU lru = new LRU(2);
        lru.put(1, 1); // 缓存: {1=1}
        lru.put(2, 2); // 缓存: {2=2, 1=1}
        System.out.println(lru.get(1)); // 返回 1 → 缓存: {1=1, 2=2}
        lru.put(3, 3); // 淘汰 2 → 缓存: {3=3, 1=1}
        System.out.println(lru.get(2)); // 返回 -1（已淘汰）
    }

}

