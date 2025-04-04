public class QuickSort {
    public void quickSort(int[] arr) {
        if(arr == null || arr.length <= 1){
            return;
        }
        quickSort(arr, 0, arr.length - 1);

    }
    public void quickSort(int[] arr, int left, int right) {
        if(left >= right){
            return;
        }
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    public int partition(int[] arr, int left, int right) {
        int randomIndex = left + (int)(Math.random() * (right - left + 1));
        swap(arr, randomIndex, right);
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if(arr[j] < pivot) {
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr, i,right);
        return i;
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {1,2,3,5,5,6,10,9,9};
        quickSort.quickSort(arr);
        for(int num : arr){
            System.out.print(num + " ");
        }

    }

}
