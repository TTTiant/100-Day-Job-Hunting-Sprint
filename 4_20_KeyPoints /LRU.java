import java.util.HashMap;
import java.util.Map;

public class LRU {
    //双链表+ 哈希表结构

    //链表节点定义
    class DNode {
        int key;
        int value;
        DNode next;
        DNode prev;

        public DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public DNode(){}
    }
    //哈希表，value是双链表节点
    private Map<Integer,DNode> map = new HashMap<>();
    private int capacity; //初始容量
    private DNode head;
    private DNode tail;
    private int size; // 目前容量

    //初始化一个缓存结构
    public LRU(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DNode();
        tail = new DNode();
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key){
        DNode node = map.get(key);
        if(node == null) return -1;
        //存在，把节点提前到最近使用
        removeNode(node);
        addToHead(node);
        return node.value;

    }
    public void put(int key, int value) {
        //查询哈希表有没有该值
        DNode node = map.get(key);
        //没有该值
        if(node == null) {
            //创建新node存入key，value
            DNode newNode = new DNode(key, value);
            //哈希表也存入
            map.put(key, newNode);
            //把该值加入到头部，并且长度++
            addToHead(newNode);
            size++;
            //长度超过容量，删除链表最后的节点并哈希表也删除，size--
            if(size > capacity) {
                DNode oldNode = tail.prev;
                removeNode(oldNode);
                map.remove(oldNode.key);
                size--;
            }
        }else{
            //如果有该值，直接更改值，节点移动到前面
            node.value = value;
            removeNode(node);
            addToHead(node);
        }
    }

    public void removeNode(DNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addToHead(DNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;

    }
}
