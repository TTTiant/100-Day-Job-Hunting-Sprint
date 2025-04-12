public class Fibo {
    //斐波那契数列返回第n项
    // 方法一：递归
    public int fibo(int n) {
        if (n == 1 || n == 2) return n;
        return fibo(n - 1) + fibo(n - 2);
    }

    //方法二：动态规划思想实现斐波那契数列
    public int fibo2(int n){
        int[] dp = new int[n+2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Fibo fibo = new Fibo();
        System.out.println(fibo.fibo(10));
        Fibo fibo2 = new Fibo();
        System.out.println(fibo2.fibo(10));
    }

}
