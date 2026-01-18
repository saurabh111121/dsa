class Solution {
    void mergeSort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while (i <= m)
            temp[k++] = arr[i++];
        while (j <= r)
            temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, l, temp.length);
    }

    public int[] sortArray(int[] nums) {
        int n = nums.length;
        mergeSort(nums, 0, n-1);
        return nums;
    }
}
