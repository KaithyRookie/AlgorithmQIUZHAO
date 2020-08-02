/**
 * 153. 寻找旋转排序数组中的最小值
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * @author kaithy.xu
 * @date 2020-08-02 20:01
 */
public class FindMinimumInRotatedSortedArray {

    /**
     * 解题思路：二分搜索，每次根据中间与最右侧的值的大小决定向左搜索还是向右搜索
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {

        if(nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        int rightNum = nums[right];
        while (left < right) {
            int min = ((right - left) >> 1) + left;

            if(nums[min] > rightNum) {
                left = min+1;
            }else {
                right = min;
            }
        }

        return nums[left];
    }
}
