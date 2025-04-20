import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFU {
    public int capacity;
    public HashMap<Integer, Integer> keyToValue;// 存储键值对
    public HashMap<Integer, Integer> keyToFreq;//存储键的使用频率
    public HashMap<Integer, LinkedHashSet<Integer>> freqToKeys; //存储频率到键集合的映射(LinkedHashSet保持顺序)
    //例如：频率1含有哪些key， 频率2含有哪些key
    private int minFreq; //维护一个最小频率

    //定义一个LFU缓存
    public LFU(int capacity) {
        this.capacity = capacity;
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        minFreq = 0;
    }
    public int get(int key){
        if(!keyToValue.containsKey(key)) return -1;

        //更新频率
        int freq = keyToFreq.get(key);
        freqToKeys.get(freq).remove(key); //先删除在此频率下的被get的key
        if(freq == minFreq && freqToKeys.get(freq).isEmpty()) minFreq++;//freq为最小频率，且为空，minfreq++
        freqToKeys.computeIfAbsent(freq+1, k -> new LinkedHashSet<>()).add(key);
        //上面代码 相当于：主要是检查有没有freq+1 频率key，没有就创建，再put
//        if (!freqToKeys.containsKey(freq + 1)) {
//            freqToKeys.put(freq + 1, new LinkedHashSet<>());
//        }
//        freqToKeys.get(freq + 1).add(key);
        keyToValue.put(key, freq+1);
        return keyToValue.get(key);
    }
    public void put(int key, int value){
        //边界检测
        if(capacity <= 0) return;

        //已存在，直接更新值
        if(keyToValue.containsKey(key)) {
            keyToValue.put(key, value); //更新值
            get(key); //更新频率
            return;
        }
        //不存在
        //到容量阈值了,找到最老元素并全面删除
        if(keyToFreq.size() >= capacity) {
            int evict = freqToKeys.get(minFreq).iterator().next(); //遍历到最小频率最老的位置
            //删除最老元素
            freqToKeys.get(minFreq).remove(evict);
            keyToValue.remove(evict);
            keyToFreq.remove(evict);
        }
        //put新键对
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.computeIfAbsent(1, k->new LinkedHashSet<>()).add(key);
        //新值最小频率为1
        minFreq=1;

    }

}
