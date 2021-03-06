/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * 解题思路：
 * 从数组下标0开始，将数组中的所有非0元素按序填充数组的前半部分。
 * 这个可以使用一个变量j 来表示当前填充的位置，循环遍历数组时，每交换一次非0元素nums[i]，就将j加一
 * 这样就可以将所有0移动到数组末尾
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * @author kaithy.xu
 * @date 2020-07-17 09:05
 */
public class MoveZeros {

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                int t = nums[j];
                nums[j++] = nums[i];
                nums[i] =t;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1,0,3,4,0};

        new MoveZeros().moveZeroes(array);
        System.out.println(Arrays.toString(array));
    }
}
