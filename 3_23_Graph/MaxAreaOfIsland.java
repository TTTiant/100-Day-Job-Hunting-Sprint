public class MaxAreaOfIsland {
        public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for(int i =0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length;j++){
                if(grid[i][j] == 1){
                    res=Math.max(res, dfs(i,j,grid));
                }
            }
        }
        return res;

    }

    private int dfs(int i, int j, int[][] grid){
        if(i<0 || j<0 || i >= grid.length||j >= grid[i].length || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        int num = 1;
        num += dfs(i+1, j, grid);
        num += dfs(i, j+1, grid);
        num += dfs(i-1, j, grid);
        num += dfs(i, j-1, grid);
        return num;
    }
    public static void main(String[] args) {
            MaxAreaOfIsland m = new MaxAreaOfIsland();
            System.out.println(m.maxAreaOfIsland(new int[][]{
                    {0,1,0,1,0,1,0,1,0,1},
                    {1,1,0,1,1,0,0,0,0,1},
                    {0,1,0,1,1,0,1,0,1,1}
            }));
    }
}


