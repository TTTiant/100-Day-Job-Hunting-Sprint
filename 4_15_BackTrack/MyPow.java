public class MyPow {
    public double myPow(double x, int n) {
        double result = 1;

        if(n < 0){
            n = -n;
            x = 1 / x;
        }
        while(n != 0){
            if((n & 1) == 1){
                result *= x;
            }
            x *= x;
            n >>= 1;
        }
        return result;

    }
    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        System.out.println(myPow.myPow(2.0, 10));
    }
}
