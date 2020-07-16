package sort;

/**
 * @author wrj
 * @date 2020/7/16
 */
public class Sort {

    /**
     * 冒泡排序 平均时间复杂度O(n^2)
     * @param arr 数组
     */
    public static void bubble(int[] arr) {
        // 方式一：最大的数移动到最右侧
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }

        // 方式二：最小的数移动到最左侧
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }

    /**
     * 快速排序 平均时间复杂度O(log2N) 最坏时间复杂度O(n^2)
     * @param arr 数组
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start > end) {

            return;
        }

        int base = arr[start];

        int i = start, j = end;

        while (i < j) {

            while (arr[j] >= base && i < j) {
                j--;
            }

            while (arr[i] <= base && i < j) {
                i++;
            }

            if (i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }

        }

        arr[start] = arr[i];
        arr[i] = base;

        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);

    }

}
