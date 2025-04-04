public class JumpGame {
    public boolean canJump(int[] nums) {
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(k < i) return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        JumpGame jumpGame2 = new JumpGame();
        System.out.println(jumpGame.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jumpGame2.canJump(new int[]{2, 1, 1, 0, 4}));
    }
}

