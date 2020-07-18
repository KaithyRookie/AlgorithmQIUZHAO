import java.util.Arrays;

/**
 * 189. 旋转数组
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 解题思路：
 * 解法1：环状替换
 * 从 i (i=0; i = (i+k) % n)开始计算数组中每个元素替换后的位置 t， 交换数组中 cur(cur 从 0 开始) 与 t的位置，然后将 使得 i = t， 并重新计算t
 * 当n 为 偶数的情况时，会存在 t 循环计算，此时需要使用cur自增并更新 i，以跳出循环
 * 时间复杂度：O(n)
 * 空间复杂度: O(1)
 *
 * 解法2：数组反转
 * 先将数组进行反转，然后分别对 前k个以及后 n-k+1个元素进行反转
 * 这里需要先 k = k%n，，因为当k==n时，等同于没有移动
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 * 
 *
 * @author kaithy.xu
 * @date 2020-07-18 17:48
 */
public class Rotate {

    public void rotate(int[] nums, int k) {
        int length = nums.length;
        if(length == 0 || k == 0) {
            return;
        }
        int i=0,cur = 0,count = 0;
        while (count++ < length) {
            int t = ( i+k ) % length;
            if(t == cur) {
                i = ++cur;
            }else {
                swap(nums, cur, t);
                i = t;
            }

        }
    }

    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        if(length == 0 || k == 0) {
            return;
        }

        k = k%length;

        reverse(nums,0,length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,length-1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums,left++, right--);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int t = nums[left];
        nums[left] = nums[right];
        nums[right] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int k = 4;
        new Rotate().rotate2(nums, k);

        System.out.println(Arrays.toString(nums));

    }
}
