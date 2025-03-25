import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int row_length = grid.length;
        int col_length = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        //初始化队列，找到腐败橘子位置存入队列，统计新鲜橘子数量

        for(int r = 0; r < row_length; r++){
            for(int c = 0; c < col_length; c++){
                if(grid[r][c] == 2){
                    queue.offer(new int[]{r ,c});
                }else if(grid[r][c] == 1){
                    fresh++;
                }
            }
        }

        if(fresh == 0){
            return 0;
        }

        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

        int min = 0;
        //BFS
        while(!queue.isEmpty()&& fresh>0){  //遍历队列
            int levelSize = queue.size();
            for(int i = 0;i < levelSize;i++){ //遍历每层的元素
                int[] curr_loc = queue.poll();
                int r = curr_loc[0];
                int c = curr_loc[1];

                for(int[] dir: directions){ //遍历每个元素的四个方向
                    int new_r = r + dir[0];
                    int new_c = c + dir[1];

                    if(new_c >=0&& new_r>=0&& new_c<col_length && new_r < row_length&& grid[new_r][new_c] == 1){
                        grid[new_r][new_c] = 2;
                        queue.offer(new int[]{new_r,new_c});
                        fresh--;
                    }
                }
            }
            if(!queue.isEmpty()){ //如果不为空，说明还有下一层新鲜橘子，时间+1；
                min++;
            }

        }
        if(fresh > 0){
            return -1;
        }

        return min;
    }

    public static void main(String[] args){
        RottingOranges oranges = new RottingOranges();
        int[][] grid = {{1,2,0},
                        {1,1,1},
                        {0,0,1}};
        System.out.println(oranges.orangesRotting(grid));
    }
}

