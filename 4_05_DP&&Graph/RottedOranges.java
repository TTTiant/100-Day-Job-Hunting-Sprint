import java.util.LinkedList;
import java.util.Queue;

public class RottedOranges {
    public int rottedOranges(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;

        Queue<int[]> q = new LinkedList<>(); // 队列存储数组，腐烂橘子的坐标
        //遍历表格，记录新鲜橘子个数和把腐烂橘子放入队列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                }else if(grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        //边界检测，没有新鲜橘子，直接返回0
        if (fresh == 0) {
            return 0;
        }

        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //广度遍历定义四个方向
        int min = 0;
        //遍历队列
        while (!q.isEmpty() && fresh > 0) {
            int levelSize = q.size();
            //遍历队列每一层
            for(int i = 0; i < levelSize; i++) {
                //取出腐烂橘子的坐标
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                //腐烂橘子扩撒到四周并且把新的腐烂橘子加入队列
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if(newX>=0 && newY>=0&& newX<m && newY<n && grid[newX][newY] == 1){
                        grid[newX][newY] = 2;
                        q.offer(new int[]{newX, newY});
                        fresh--;
                    }
                }
            }
            //每遍历一层时间加一
            min++;
//            if(!q.isEmpty()) {
//                min++;
//            }
        }
        return min;
    }
    public static void main(String[] args) {
        RottedOranges rottedOranges = new RottedOranges();
        int[][] grid = {
                {0,0,1,1,2,1,0,0},
                {0,0,1,1,1,1,0,0},
        };
        System.out.println(rottedOranges.rottedOranges(grid));
    }
}
