import java.util.*;


public class TheLeastKNums {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param input int整型一维数组
     * @param k int整型
     * @return int整型ArrayList
     */
    public ArrayList<Integer> GetLeastNumbers_Solution (int[] input, int k) {
        // write code here
        if (k == 0 ){
            return new ArrayList<>();
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 遍历数组元素 比较压进堆中 比较堆顶元素 小的进堆 大的poll出。
        for(int num: input){
            if(maxHeap.size() < k){
                maxHeap.offer(num);
            }else{
                if(maxHeap.peek() > num){
                    maxHeap.poll();
                    maxHeap.offer(num);
                }
            }
        }
        //遍历堆元素并放进ArrayList
        ArrayList<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty()){
            result.add(maxHeap.poll());
        }
        // 反转List return
        Collections.reverse(result);

        return result;

    }
}
