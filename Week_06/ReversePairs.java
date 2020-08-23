import java.util.Arrays;

/**
 * 493. 翻转对
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * @author kaithy.xu
 * @date 2020-08-23 14:46
 */
public class ReversePairs {

    /**
     * 归并排序
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int len = nums.length;

        if(len == 0) {
            return 0;
        }

        return mergeSort(nums, 0, len-1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if(left >= right) {
            return 0;
        }

        int mid = ((right - left) >> 1) + left;

        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid+1, right);
        int j = mid+1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && ((nums[i]/2) > nums[j] || (nums[i]/2 == nums[j] && nums[i] % 2 > 0)) ) {
                ++j;
            }
            count += j - (mid+1);
        }
        merge(nums, left, mid, right);
        return count;
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = nums[left+i];
        }
        for (int i = 0; i < n2; i++) {
            R[i] = nums[mid+i+1];
        }

        int i=0,j=0;
        for (int k = left; k <= right; k++) {
            if(j < n2 && i < n1) {
                nums[k] = L[i] <= R[j] ? L[i++] : R[j++];
            }else if(j >= n2) {
                nums[k] = L[i++];
            }else {
                nums[k] = R[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,3,1};

        System.out.println(new ReversePairs().reversePairs(nums));

        System.out.println(Arrays.toString(nums));
    }
}
