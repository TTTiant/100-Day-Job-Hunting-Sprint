public class IsPanlidrome {
    //回文数
    public boolean isPalindrome(int x) {
        if(x < 0 ||(x % 10 == 0 && x != 0)){
            return false;
        }

        int reversedNums = 0 ;
        while(x > reversedNums){

            reversedNums = x % 10 + reversedNums * 10;
            x = x / 10;
        }

        return reversedNums == x || x== reversedNums/10;

        }
    }



