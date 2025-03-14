import java.util.Arrays;

public class QuickSort {

    public  int[] quickSort(int[] arr, int low, int high){
        if(low < high) {
            int pivotal = partition(arr, low, high);

            quickSort(arr, low, pivotal - 1);
            quickSort(arr, pivotal + 1, high);
        }
        return arr;
    }

    public int partition(int[] nums, int low, int high){
        int pivotal = nums[high];
        int i = low - 1;
        for(int j = low; j < high ; j++){
            if(nums[j] <= pivotal){
                i++;
                swap(nums, i, j);
            }

        }
        swap(nums, i + 1, high);
        return i + 1;
    }
    public void swap(int[] nums, int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort solution = new QuickSort();
        int[] arr = {1,2,5,7,14,11,3,4,13,15,16};
        int low = 0;
        int high = arr.length - 1;
        System.out.println(Arrays.toString (solution.quickSort(arr,low,high)));
    }

}
