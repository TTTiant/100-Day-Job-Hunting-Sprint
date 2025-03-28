import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {

            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;

    }

    public int singleNumber3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
            set.remove(num);
        }

//        for(int num : set) {
//            return num;
//        }
        return set.iterator().next();
    }

    public static void main(String[] args) {
        SingleNumber singleNumber = new SingleNumber();
        System.out.println(singleNumber.singleNumber(new int[]{1,2,2,4,4,6,6,9,9}));

        SingleNumber singleNumber2 = new SingleNumber();
        System.out.println(singleNumber2.singleNumber2(new int[]{1,1,5,2,2,4,4,6,6,9,9}));

        SingleNumber singleNumber3 = new SingleNumber();
        System.out.println(singleNumber3.singleNumber2(new int[]{1,1,3,2,2,4,4,6,6,9,9}));

    }
}
