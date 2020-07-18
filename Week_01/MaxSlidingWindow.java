import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 239. 滑动窗口最大值
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 解题思路：
 *
 * 1. 暴力法求解
 * 时间复杂度： O(n*k) 其中n是数组元素的个数
 * 空间复杂度: O(1)
 *
 * 2. 利用双端队列
 * 初始化：
 *
 * 首先先初始化双端队列 deque，即将前k个元素的下标 放入到 deque 中，但是需要确保 deque中 头元素 对应数组中的前k个元素中的最大值
 * 所以每遍历一个数组元素时，都需要检查 deque 中的头部元素是否大于 当前元素，若不是则清空 deque
 * 然后将当前元素加入到 deque 中
 *
 * 滑动状态：
 * 滑动过程中，需要判断 deque 中的头部元素已被滑出，通过通过 当前下标 i 与 k 的差值判断是否需要移出deque
 * 同样在将下标放入到 deque中前，需要先进行一次清理操作
 *
 * 时间复杂度：O(n), 因为数组中的每个元素只会进出一次 deque
 * 空间复杂度：O(k), k为双端队列的长度
 *
 * 3. 动态规划(待实现)
 *
 * @author kaithy.xu
 * @date 2020-07-18 15:24
 */
public class MaxSlidingWindow {

    public int[] forceSolution(int[] nums, int k) {
        int limit = nums.length - k+1;
        int[] result = new int[limit] ;


        for (int i = 0; i < limit; i++) {

            int right = i+k;
            int max = Integer.MIN_VALUE;
            for (int j = i; j < right; j++) {
                max = Math.max(max, nums[j]);
            }

            result[i] = max;
        }

        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;

        if(n*k == 0) {
            return new int[0];
        }
        if(k == 1) {
            return nums;
        }


        Deque<Integer> deque = new ArrayDeque<>(k);
        int limit = nums.length - k+1;
        int[] result = new int[limit] ;

        int max = 0;

        for (int i = 0; i < k; i++) {
            cleanDeque(deque, nums, i , k);

            deque.addLast(i);

            if(nums[i] > nums[max]) {
                max = i;
            }
        }

        result[0] = nums[max];

        for (int i = k; i < n; i++) {
            cleanDeque(deque, nums, i, k);

            deque.addLast(i);

            result[i-k+1] = nums[deque.getFirst()];
        }

        return result;
    }

    private void cleanDeque(Deque<Integer> deque, int[] nums, int i, int k) {

        if(!deque.isEmpty() && deque.getFirst() == (i-k)) {
            deque.removeFirst();
        }

        while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
            deque.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] result = new MaxSlidingWindow().maxSlidingWindow(nums, k);

        System.out.println(Arrays.toString(result));
    }

}
