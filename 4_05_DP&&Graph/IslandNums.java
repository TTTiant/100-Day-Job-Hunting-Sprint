public class IslandNums {
    public int numIslands(char[][] grid) {
        if(grid == null||grid.length == 0) return 0;
        int result = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    result++;

                }
            }
        }
        return result;
    }

    public void dfs(char[][] grid, int row, int col) {
        if(row <0 || col <0 || row >= grid.length|| col >= grid[0].length||grid[row][col] == '0') return;
        grid[row][col] = '0';
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }

    public static void main(String[] args) {
        IslandNums islandNums = new IslandNums();
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '1', '0', '0', '0'},
                {'1', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0', '0', '1'},
        };
        System.out.println(islandNums.numIslands(grid));

    }
}

