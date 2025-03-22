import java.util.Stack;
//单调栈解决每日温度问题
public class DailyTemp {
    //逆序遍历单调栈
    public static int[] dailyTemp(int[] temp){
        //时间复杂度O(n) 空间复杂度O（n）
        int n = temp.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>(); //栈中存储的是数组下标


        for(int i = n - 1; i >=0 ;i--){ //逆向遍历
            int t = temp[i];
            while(!stack.isEmpty() && t >= temp[stack.peek()]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                res[i] = stack.peek() - i;
            }
            stack.push(i);
        }

        return res;
    }

    public static int[] dailyTemp2(int[] temp) {
        int n = temp.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i <n ;i++){
            int t = temp[i];
            while(!stack.isEmpty() && t > temp[stack.peek()]){
                int j = stack.pop();
                res[j] = i - j;         //在循环内更新答案的
            }
            stack.push(i);

        }

      return res;

    }
    public static void main(String[] args) {
        int[] temp = {1,8,6,2,5,4,8,3,7};
        int[] res = dailyTemp(temp);
        int[] res1 = dailyTemp2(temp);
        for(int i : res){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i : res1){
            System.out.print(i + " ");
        }

    }
}
