# 图论 Graph
来源：LeetCode hot 100

## 1.题目：岛屿数量

### 题目内容
给你一个由 `'1'`（陆地）和 `'0'`（水）组成的二维网格，请你计算网格中岛屿的数量。岛屿总是被水包围，并且每座岛屿只能由水平方向和/或垂直方向上相邻的陆地连接形成。

## 题目思路
1. 从网格的左上角开始，逐行逐列遍历每个单元格。
2. 当遇到值为 `'1'` 的单元格时，表示发现了一个岛屿，岛屿数量加 1。
3. 从当前陆地单元格开始，使用 DFS 向四个方向（上、下、左、右）递归遍历，将所有相连的陆地标记为 `'0'`，避免重复计数。
4. 完成 DFS 后，继续遍历网格，直到所有单元格都被访问。

```java
public class NumIslands {

    public int numIslands(char[][] grid) {
        // 边界检查
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row_length = grid.length;       // 网格的行数
        int col_length = grid[0].length;   // 网格的列数
        int num_islands = 0;               // 岛屿数量

        // 遍历网格
        for (int row = 0; row < row_length; row++) {
            for (int col = 0; col < col_length; col++) {
                // 发现陆地
                if (grid[row][col] == '1') {
                    num_islands++;          // 岛屿数量加 1
                    dfs(grid, row, col);  // DFS 标记相连的陆地
                }
            }
        }

        return num_islands;
    }

    // DFS 方法
    public void dfs(char[][] grid, int row, int col) {
        int row_length = grid.length;       // 网格的行数
        int col_length = grid[0].length;    // 网格的列数

        // 边界检查：超出网格范围或当前单元格是水
        if (row < 0 || col < 0 || row >= row_length || col >= col_length || grid[row][col] == '0') {
            return;
        }

        // 将当前陆地标记为水
        grid[row][col] = '0';

        // 递归遍历四个方向
        dfs(grid, row - 1, col); // 上
        dfs(grid, row + 1, col); // 下
        dfs(grid, row, col - 1); // 左
        dfs(grid, row, col + 1); // 右
    }

    public static void main(String[] args) {
        NumIslands numIslands = new NumIslands();
        char[][] grid = new char[][]{
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '1'}
        };
        System.out.println(numIslands.numIslands(grid)); // 输出：2
    }
}
```
## 复杂度分析
时间复杂度 O(M×N)，其中 `M` 是网格的行数，`N` 是网格的列数。 。
空间复杂度 O(M×N)

你的代码实现了一个经典的 **岛屿最大面积问题**，使用深度优先搜索（DFS）来遍历二维网格并计算每个岛屿的面积，最终返回最大面积。代码逻辑清晰，实现正确。以下是详细的解释和复杂度分析：



## 2.题目：岛屿的最大面积

### 题目内容
给定一个由 `0` 和 `1` 组成的二维网格，其中 `1` 表示陆地，`0` 表示水域。岛屿是由相邻的陆地（水平或垂直方向）组成的区域。请计算网格中岛屿的最大面积。如果没有岛屿，则返回 `0`。

## 题目思路

### 深度优先搜索（DFS）
1. 从网格的左上角开始，逐行逐列遍历每个单元格。
2. 当遇到值为 `1` 的单元格时，表示发现了一个岛屿。
3. 从当前陆地单元格开始，使用 DFS 向四个方向（上、下、左、右）递归遍历，计算当前岛屿的面积。
4. 在 DFS 过程中，将访问过的陆地单元格标记为 `0`，避免重复计算。
5. 每次计算完一个岛屿的面积后，更新最大面积。
6. 完成 DFS 后，继续遍历网格，直到所有单元格都被访问。

## 代码实现

```java
public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0; // 最大面积

        // 遍历网格
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 发现陆地
                if (grid[i][j] == 1) {
                    // 计算当前岛屿的面积，并更新最大面积
                    res = Math.max(res, dfs(i, j, grid));
                }
            }
        }

        return res;
    }

    // DFS 方法
    private int dfs(int i, int j, int[][] grid) {
        // 边界检查：超出网格范围或当前单元格是水域
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }

        // 将当前陆地标记为水域
        grid[i][j] = 0;

        // 当前岛屿的面积初始化为 1
        int num = 1;

        // 递归遍历四个方向
        num += dfs(i + 1, j, grid); // 下
        num += dfs(i - 1, j, grid); // 上
        num += dfs(i, j + 1, grid); // 右
        num += dfs(i, j - 1, grid); // 左

        return num;
    }

    public static void main(String[] args) {
        MaxAreaOfIsland m = new MaxAreaOfIsland();
        System.out.println(m.maxAreaOfIsland(new int[][]{
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 0, 1},
            {0, 1, 0, 1, 1, 0, 1, 0, 1, 1}
        })); // 输出：7
    }
}
```

## 复杂度分析

时间复杂度 O(M×N)
空间复杂度 O(M×N)




## 3.题目：岛屿的周长

### 题目内容
给定一个由 `0` 和 `1` 组成的二维网格，其中 `1` 表示陆地，`0` 表示水域。岛屿是由相邻的陆地（水平或垂直方向）组成的区域。请计算岛屿的周长。

## 题目思路

### 深度优先搜索（DFS）
1. 从网格的左上角开始，逐行逐列遍历每个单元格。
2. 当遇到值为 `1` 的单元格时，表示发现了一个岛屿。
3. 从当前陆地单元格开始，使用 DFS 向四个方向（上、下、左、右）递归遍历，计算岛屿的周长。
4. 边界条件：
    - 如果当前单元格超出网格范围，说明是边界，周长加 1。
    - 如果当前单元格是水域，说明是边界，周长加 1。
    - 如果当前单元格是已访问的陆地（标记为 `2`），则跳过。
5. 在 DFS 过程中，将访问过的陆地单元格标记为 `2`，避免重复计算。
6. 完成 DFS 后，返回计算得到的周长。

## 代码实现

```java
public class PerimeterOfIsland {

    public int islandPerimeter(int[][] grid) {
        // 遍历网格
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                // 发现陆地
                if (grid[r][c] == 1) {
                    // 计算当前岛屿的周长
                    return dfs(grid, r, c);
                }
            }
        }
        return 0; // 如果没有岛屿，返回 0
    }

    // DFS 方法
    public int dfs(int[][] grid, int r, int c) {
        // 边界条件：超出网格范围，周长加 1
        if (!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)) {
            return 1;
        }

        // 边界条件：当前单元格是水域，周长加 1
        if (grid[r][c] == 0) {
            return 1;
        }

        // 如果当前单元格是已访问的陆地，跳过
        if (grid[r][c] != 1) {
            return 0;
        }

        // 标记当前单元格为已访问
        grid[r][c] = 2;

        // 递归遍历四个方向，并累加周长
        return dfs(grid, r - 1, c) + // 上
               dfs(grid, r, c - 1) + // 左
               dfs(grid, r + 1, c) + // 下
               dfs(grid, r, c + 1);  // 右
    }

    public static void main(String[] args) {
        PerimeterOfIsland p = new PerimeterOfIsland();
        System.out.println(p.islandPerimeter(new int[][]{
            {0, 1, 0},
            {1, 1, 0},
            {0, 1, 0}
        })); // 输出：8
    }
}
```
## 复杂度分析

时间复杂度 O(M×N)
空间复杂度 O(M×N)








