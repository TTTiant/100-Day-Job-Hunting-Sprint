import java.util.HashMap;

public class NoDupliacateLongestString {
    //LeetCode hot 100-3 滑动窗口
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int i = -1;
        int res = 0;
        int n = s.length();
        for(int j =0; j < n;j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(i, map.get(s.charAt(j))); //表中存在值，说明重复，把i提到上次重复值出现的坐标
            }
            map.put(s.charAt(j), j);
            res = Math.max(res, j-i); //j-i 等于目前不重复字符串长度
        }
        return res;
    }


    //动态规划解法



}
