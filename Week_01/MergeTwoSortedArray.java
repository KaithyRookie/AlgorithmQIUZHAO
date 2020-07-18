import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 
 * 解题思路：
 * 解法1：
 * 从后往前，依次将两数组中最大的元素填入nums1中
 * 
 * 时间复杂度：O(m+n)
 * 空间复杂度： O(1)
 *
 * @author kaithy.xu
 * @date 2020-07-18 18:23
 */
public class MergeTwoSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int last = m+n-1;
        int i = m-1, j=n-1;
        while (i >=0 && j >= 0) {
            if(nums2[j] >= nums1[i]) {
                nums1[last--] = nums2[j--];
            }else {
                nums1[last--] = nums1[i--];
            }
        }
        while (j >= 0) {
            nums1[last--] = nums2[j--];
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {5,7,8,0,0,0};
        int[] nums2 = {2,3,4};
        int m = 3;
        int n = 3;
        new MergeTwoSortedArray().merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }

}
