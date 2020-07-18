/**
 *
 * 26. 删除排序数组中的重复项
 *
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 
 * 解题思路：
 * 利用一个指针标记当前检查是否重复的元素的下标 x ，遍历数组，每遇到一个与nums[x] 不同的元素，则更新 nums[++x] = nums[i]
 * 
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 *
 * @author kaithy.xu
 * @date 2020-07-18 17:30
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int left = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[left]) {
                left++;
                nums[left] = nums[i];
            }
        }

        return left+1;
    }
}
