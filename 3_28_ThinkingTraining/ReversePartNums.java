import java.util.Arrays;

public class ReversePartNums {

    public int[] solve (int n, int m, int[] a) {
        // write code here
        if(m % n == 0 || a == null) return a;

        m = m % n;
        reverse(a,0,n-1);  //先反转整体
        reverse(a,0,m-1);  // 反转前m个数
        reverse(a,m,n-1);  //反转剩下的数，下标（m，n-1）

        return a;

    }

    public void reverse(int[] a, int start, int end){
        while(start<end){
            int temp = a[start];
            a[start] = a[end];
            a[end] = temp;

            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        ReversePartNums solution = new ReversePartNums();
        int[] result = solution.solve(4,3,new int[]{1,2,3,4});
        System.out.println(Arrays.toString(result));

    }
}
