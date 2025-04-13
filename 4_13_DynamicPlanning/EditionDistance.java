import java.util.Arrays;

public class EditionDistance {

    //DP Table 方法解题
    public int EditionDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int [n1 + 1][n2 + 1];
        //Base
        for (int i = 0; i <= n1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n2; j++) {
            dp[0][j] = j;
        }
        //比较两字符，一样跳过，不一样，进行比较增/删/替换操作
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                //i-1和j-1字符相等，那直接跳过，没有操作
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j]= Math.min(
                            //增
                            dp[i-1][j] +1,
                            Math.min(
                            //删
                            dp[i][j-1] +1,
                            //替换
                            dp[i-1][j-1]+1)
                    );
                }


            }
        }
        return dp[n1][n2];
    }

    //备忘录递归方法
    public int[][] memo;
    public int EditionDistance2(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        memo = new int[n1][n2];
        for(int[] row : memo){
            Arrays.fill(row, -1);
        }
        return dp(word1,n1-1,word2,n2-1);
    }

    public int dp(String word1, int i, String word2, int j){
        if(i == -1) return j+1;
        if(j == -1) return i+1;

        if(memo[i][j] !=-1){
            return memo[i][j];
        }
        if(word1.charAt(i) == word2.charAt(j)){
            memo[i][j] = dp(word1,i-1,word2,j-1);
        }else{
            memo[i][j] = Math.min(Math.min(
                    dp(word1,i-1,word2,j)+1,
                    dp(word1,i,word2,j-1)+1),
                    dp(word1,i-1,word2,j-1)+1);
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        String word1 = "rose";
        String word2 = "horie";
        EditionDistance editionDistance = new EditionDistance();
        System.out.println(editionDistance.EditionDistance(word1, word2));
        System.out.println(editionDistance.EditionDistance2(word1, word2));
    }
}
