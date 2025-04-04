public class SellStock {
    public int sellStock(int[] arr) {
        //假设每天都是最低点买入
        if(arr == null || arr.length == 0) return 0;
        int maxProfit = 0;
        int minPrice = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < minPrice) {
                minPrice = arr[i];
            }else if(arr[i] - minPrice > maxProfit) {
                maxProfit = arr[i] - minPrice;
            }

        }
        return maxProfit;
    }

    public static void main(String[] args) {
        SellStock stock = new SellStock();
        System.out.println(stock.sellStock(new int[]{1,2,3,4,5}));
    }
    //双指针
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int left = 0;    // 买入指针
        int right = 1;   // 卖出指针
        int maxProfit = 0;

        while (right < prices.length) {
            if (prices[right] > prices[left]) {
                int profit = prices[right] - prices[left];
                maxProfit = Math.max(maxProfit, profit);
            } else {
                left = right; // 发现更低的买入点
            }
            right++;
        }
        return maxProfit;
    }
}
