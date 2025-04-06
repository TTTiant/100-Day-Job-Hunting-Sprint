import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        backTrack(new StringBuilder(), 0, 0,n );
        return res;


    }

    public void backTrack(StringBuilder current, int open, int close, int max) {
        if (current.length() == 2* max) {
            res.add(current.toString());
            return;
        }
        if(open < max){
            current.append('(');
            backTrack(current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1);
        }
        if(close < open){
            current.append(')');
            backTrack(current, open, close+1, max);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        System.out.println(g.generateParenthesis(3));
    }
}
