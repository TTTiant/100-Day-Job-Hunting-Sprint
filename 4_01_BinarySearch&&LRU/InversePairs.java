public class InversePairs {

    //归并排序
    //题目要求数组没有重复数字
    //时间复杂度小于nlogn
    private static final int MOD = 1000000007; //取模防止整型溢出
    private int count = 0;

    public int inversePairs(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        mergeSort(array, 0, array.length - 1);
        return count;
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        // 1. 创建临时数组，用于存储合并后的结果
        int[] temp = new int[right - left + 1];

        // 2. 初始化指针：
        // - i: 左半部分的起始位置 (left)
        // - j: 右半部分的起始位置 (mid + 1)
        // - k: 临时数组的索引
        int i = left, j = mid + 1, k = 0;

        // 3. 遍历左右两部分，按升序合并
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                // 左半部分当前元素较小，直接放入 temp
                temp[k++] = array[i++];
            } else {
                // 左半部分当前元素较大，说明存在逆序对
                temp[k++] = array[j++];
                // 统计逆序对：左半部分剩余元素 (mid - i + 1) 都会比 array[j] 大
                count = (count + (mid - i + 1)) % MOD;
            }
        }

        // 4. 处理剩余元素（左半部分或右半部分可能还有剩余）
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }

        // 5. 将合并后的 temp 数组复制回原数组
        System.arraycopy(temp, 0, array, left, temp.length);
    }


    public static void main(String[] args) {
        InversePairs inversePairs = new InversePairs();
        System.out.println(inversePairs.inversePairs(
                new int[]{1,4,3,2,5,6,9}

        ));
    }
}