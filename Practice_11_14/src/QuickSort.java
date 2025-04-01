import java.util.Arrays;

public class QuickSort {
    public int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {  // 终止条件
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
        return arr;
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);  // 交换当前元素到小于基准的区域
            }
        }
        swap(arr, i + 1, high);  // 将基准放到正确位置
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort sortAlgorithm = new QuickSort();
        int[] arr = new int[]{3, 2, 5, 1, 6, 4};
        int[] sortedArr = sortAlgorithm.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(sortedArr));  // 输出: [1, 2, 3, 4, 5, 6]
    }
}