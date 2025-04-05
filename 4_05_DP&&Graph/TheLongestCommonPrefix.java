public class TheLongestCommonPrefix {
    public String longestCommonPrefix (String[] strs) {
        // write code here
        if(strs.length==0 || strs == null){
            return "";
        }
        int rows = strs.length;
        int cols = strs[0].length();

        for(int i = 0; i <cols;i++){
            char firstChar = strs[0].charAt(i);
            for(int j = 1; j < rows; j++){
                if(strs[j].length() == i || strs[j].charAt(i) != firstChar){ //长度不足或者字符串不匹配就返回目前相同的子串
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
    public static void main(String[] args) {
        TheLongestCommonPrefix t = new TheLongestCommonPrefix();
        System.out.println(t.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(t.longestCommonPrefix(new String[]{"dog","racecar","car"}));

    }
}
