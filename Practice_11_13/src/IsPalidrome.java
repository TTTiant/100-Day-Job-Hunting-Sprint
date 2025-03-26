public class IsPalidrome {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if(s.charAt(left)!= s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalidrome isPalidrome = new IsPalidrome();
        IsPalidrome isPalidrome1 = new IsPalidrome();
        System.out.println(isPalidrome.isPalindrome("abba"));
        System.out.println(isPalidrome1.isPalindrome("abab"));

    }
}
