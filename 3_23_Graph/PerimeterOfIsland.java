public class PerimeterOfIsland {
    //岛屿的周长就是岛屿方格和非岛屿方格相邻的边的数量
    //向非岛屿遍历周长+1， 向岛屿遍历周长+2

    public int islandPerimeter(int[][] grid) {
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                if(grid[r][c]==1){
                    return dfs(grid,r,c);
                }
            }
        }
        return 0;

    }
    public int dfs(int[][] grid, int r, int c){
        if(!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)){
            return 1;
        }
        if(grid[r][c] == 0){
            return 1;
        }

        if(grid[r][c] != 1){
            return 0;
        }
        grid[r][c] = 2;
        return dfs(grid, r-1, c)+dfs(grid, r, c-1)+dfs(grid, r+1, c)+dfs(grid, r, c+1);
    }

    public static void main(String[] args) {
        PerimeterOfIsland p = new PerimeterOfIsland();
        System.out.println(p.islandPerimeter(new int[][]{
                {0,1,0},
                {1,1,0},
                {0,1,0}
        }));
    }
}



