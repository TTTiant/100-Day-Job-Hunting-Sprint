import java.util.HashMap;
import java.util.Map;

public class ReviewLRU {

    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode(){}
        public DLinkedNode(int _key, int _value){
            key = _key;
            value = _value;
        }

    }
    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private DLinkedNode head = new DLinkedNode();
    private DLinkedNode  tail = new DLinkedNode();
    private int capacity;
    private int size;

    public ReviewLRU(int capacity){

        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key){
        DLinkedNode node = cache.get(key);
        if(node == null)
            return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value){
        DLinkedNode node = cache.get(key);
        if(node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key,newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                DLinkedNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }


    private void addToHead(DLinkedNode node){
        node.prev = head;
        node.next =head.next;
        head.next.prev = node;
        head.next = node;

    }

    private void removeNode(DLinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addToHead(node);
    }

    private  DLinkedNode removeTail(){
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }


}
