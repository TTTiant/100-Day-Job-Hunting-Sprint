public class NumIslands {

    public int numIslands(char[][] grid) {
        if(grid == null ||grid.length == 0){
            return 0;
        }
        int row_length = grid.length;
        int col_length = grid[0].length;
        int num_islands = 0;
        for(int row = 0; row < row_length; row++){
            for(int col = 0; col < col_length; col++){
                if(grid[row][col] == '1'){
                    num_islands++;
                    dfs(grid, row,col);
                }
            }
        }
        return num_islands;

    }
    public void dfs(char[][] grid, int row, int col){
        int row_length = grid.length;
        int col_length = grid[0].length;

        if(row < 0 || col < 0 || row >= row_length || col >= col_length||grid[row][col] == '0'){
            return;
        }
        grid[row][col] = '0';
        dfs(grid, row-1, col);
        dfs(grid, row+1, col);
        dfs(grid, row, col-1);
        dfs(grid, row, col+1);

    }

    public static void main(String[] args) {
        NumIslands numIslands = new NumIslands();
        char[][] grid = new char[][]{{'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','1'}
        };
        System.out.println(numIslands.numIslands(grid));
    }
}
