import java.util.*;


public class findKth {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param a int整型一维数组
     * @param n int整型
     * @param K int整型
     * @return int整型
     */
    public int findKth (int[] a, int n, int K) {
        // write code here
        return(quickSort(a, 0 , n-1, n - K));


    }

    public int quickSort(int[] a, int left, int right, int K){
        if(left == right){
            return a[left];
        }

        Random random = new Random();
        int pivotalIndex = left + random.nextInt(right -left + 1);

        pivotalIndex = partition(a, left, right, pivotalIndex);
        if (K == pivotalIndex){
            return a[pivotalIndex];
        }
        if(K > pivotalIndex){
            return quickSort(a, pivotalIndex+1, right, K);
        }else{
            return quickSort(a,left, pivotalIndex - 1, K);
        }


    }

    public int partition (int[] nums, int left, int right, int pivotalIndex){
        int pivotal = nums[pivotalIndex];

        swap(nums, pivotalIndex, right);

        int store_index = left;

        for(int i = left; i < right; i++){
            if(nums[i] < pivotal){
                swap(nums, i, store_index);
                store_index++;
            }

        }
        swap(nums, store_index, right);
        return store_index;

    }

    public void swap(int[] arr, int i , int j ){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}