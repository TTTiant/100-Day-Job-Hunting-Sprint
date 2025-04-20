import java.util.ArrayList;
import java.util.List;

public class NQueen {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        // 初始化棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backTrack(solutions, board,0);
        return solutions;

    }

    private void backTrack(List<List<String>> solutions, char[][] board, int row) {//按行放置皇后
        if (row == board.length) {
            solutions.add(constructSolution(board));
            return;
        }

        for(int col = 0; col < board.length; col++){
            if(isValid(board, row, col)){
                board[row][col] = 'Q';
                backTrack(solutions, board, row + 1);
                board[row][col] = '.';
            }
        }
    }


    private boolean isValid(char[][] board, int row, int col){
        //检查列
        for(int i = 0; i < row; i++){
            if(board[i][col] == 'Q'){
                return false;
            }
        }
        //检查左上对角线
        for(int i = row-1, j = col-1; i>=0 &&  j>=0; i--, j--){
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        //检查右上对角线
        for(int i = row-1, j = col+1; i>=0 && j<board.length; i--, j++){
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }

    private List<String> constructSolution(char[][] board){
        List<String> solutions = new ArrayList<>();
        for(char[] row: board){
            solutions.add(new String(row));
        }
        return solutions;
    }

    public static void main(String[] args) {
        NQueen nq = new NQueen();
        int n = 5;
        List<List<String>> solutions = nq.solveNQueens(n);

        System.out.println(n + "-Queens solutions:");

        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("\n--- Solution " + (i + 1) + " ---");
            List<String> solution = solutions.get(i);
            for (String row : solution) {
                // 替换默认的 'Q' 和 '.'
                String formattedRow = row.replace('Q', '♛').replace('.', '·');
                System.out.println(formattedRow);
            }
        }

        System.out.println("\nTotal solutions: " + solutions.size());
    }



}
