import java.util.ArrayList;
import java.util.List;

public class PhoneNumConbinations {
    String[] map = {"","", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    ArrayList<String> ans = new ArrayList<String>();

    public List<String> letterCombinations(String digits){
        if(digits == null || digits.length() == 0) return ans;
        backTrack(0,digits,new StringBuilder());
        return ans;
    }

    public void backTrack(int i, String digits, StringBuilder s) {
        if (i == digits.length()) { //边界检测
            ans.add(s.toString()); //转变为可输出的字符串
            return;
        }
        for(char c : map[digits.charAt(i) - '0'].toCharArray()){ //通过ASCLL码转换为字符数字
            s.append(c);
            backTrack(i + 1, digits, s);
            s.deleteCharAt(s.length() - 1); //回溯
        }


    }
    public static void main(String[] args) {
        PhoneNumConbinations p = new PhoneNumConbinations();
        System.out.println(p.letterCombinations("23"));
    }
}
