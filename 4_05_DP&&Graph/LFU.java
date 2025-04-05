import java.util.*;

public class LFU {
    private static class LFUCache {
        private final int capacity;
        private final HashMap<Integer, Integer> keyToValue;
        private final HashMap<Integer, Integer> keyToFreq;
        private final HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
        private int minFreq;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.keyToValue = new HashMap<>();
            this.keyToFreq = new HashMap<>();
            this.freqToKeys = new HashMap<>();
            this.minFreq = 0;
        }

        public int get(int key) {
            if (!keyToValue.containsKey(key)) return -1;

            int freq = keyToFreq.get(key);
            // 更新频率
            keyToFreq.put(key, freq + 1);
            // 从原频率集合中移除
            freqToKeys.get(freq).remove(key);
            // 如果原频率是最小频率且该频率集合为空，更新最小频率
            if (freq == minFreq && freqToKeys.get(freq).isEmpty()) {
                minFreq++;
            }
            // 添加到新频率集合
            freqToKeys.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);

            return keyToValue.get(key);
        }

        public void put(int key, int value) {
            if (capacity <= 0) return;

            if (keyToValue.containsKey(key)) {
                keyToValue.put(key, value);
                get(key); // 更新频率
                return;
            }

            if (keyToValue.size() >= capacity) {
                // 获取最小频率的键集合
                LinkedHashSet<Integer> keys = freqToKeys.get(minFreq);
                // 获取最久未使用的键
                int removeKey = keys.iterator().next();
                // 从三个映射中移除
                keys.remove(removeKey);
                if (keys.isEmpty()) {
                    freqToKeys.remove(minFreq);
                    // 这里不需要更新 minFreq，因为马上要添加新键，minFreq 会被设置为 1
                }
                keyToValue.remove(removeKey);
                keyToFreq.remove(removeKey);
            }

            // 添加新键
            keyToValue.put(key, value);
            keyToFreq.put(key, 1);
            freqToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            minFreq = 1; // 新键的频率总是1
        }
    }

    public int[] LFU(int[][] operators, int k) {
        LFUCache cache = new LFUCache(k);
        List<Integer> result = new ArrayList<>();

        for (int[] op : operators) {
            if (op[0] == 1) {
                cache.put(op[1], op[2]);
            } else if (op[0] == 2) {
                result.add(cache.get(op[1]));
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        LFU solution = new LFU();
        int[][] operators = {
                {1,1,1}, {1,2,2}, {2,1}, {1,3,3}, {2,2}, {2,3}
        };
        int k = 2;
        int[] result = solution.LFU(operators, k);
        System.out.println(Arrays.toString(result));
    }
}