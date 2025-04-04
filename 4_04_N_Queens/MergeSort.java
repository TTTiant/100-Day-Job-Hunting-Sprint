public class MergeSort {
    public void mergeSort(int[] arr) {
        if(arr == null || arr.length <= 1){
            return;
        }

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
    }

    private void mergeSort(int[] arr, int left, int right, int[] temp) {
        if(left < right){
            int mid = left + (right -left) /2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp); //合并两个有序数组

        }
    }
    private void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            } else{
                temp[k++] = arr[j++];
            }
        }
        while(i <= mid){
            temp[k++] = arr[i++];
        }
        while(j <= right){
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, 0, arr, left, k);


    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {1,2,12,4,6,6,8,8,9,10};
        mergeSort.mergeSort(arr);
        for(int i : arr){
            System.out.print(i + " ");
        }
    }


}
