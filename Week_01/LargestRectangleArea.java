import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 解题思路：
 *
 * 1. 暴力法求解
 * 利用两层嵌套for循环，固定左侧边界 i 后，向右遍历找到最小高度 j 所在位置，计算 i,j 构成的面积，取遍历结果中的最大值
 * 时间复杂度: O(n^2) n为数组的元素个数
 * 空间复杂度: O(1)
 *
 * 2. 单调栈
 * 利用单调栈保存遍历过程中的最小高度，并利用两个数组 left[]  与 right[]，
 * left[]  保存 从左向右 遍历过程中，对于位置 i 处左侧的最小高度，若 i 是最小高度，则存入 -1
 * right[] 保存 从右向左 遍历过程中，对于位置 i 处右侧的最小高度，若 i 是最小高度，这存入整个数组的长度n
 * 最后遍历两个数组，对每个位置 i 计算 所围成的矩形面积
 * 时间复杂度 O(n)
 * 空间复杂度 O(n)
 *
 * 3. 单调栈+常数优化
 * 在解法2的基础上，只遍历一次，更新两个数组 left[]  与 right[]，
 * 在维持单调栈的前提下，如果对于位置 i 处的高度heights[i] 小于栈顶的元素，那么可以认为 i 就是栈顶元素的右边界，此时更新right[stack.peek()] = i，
 * 对于那些自己本身就是最小高度的，这用 数组的长度 n 作为其哨兵值
 *
 * 4. 分治（待实现）
 *
 * 
 * @author kaithy.xu
 * @date 2020-07-18 11:27
 */
public class LargestRectangleArea {

    public int forceSoluction(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight=Integer.MAX_VALUE;

            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, (j-i+1) * minHeight);
            }

        }

        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {

        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        //从左向右遍历, 确定每个支柱左侧的最小高度
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            left[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        stack.clear();
        //从右向左遍历，确定每个支柱右侧的最小高度
        for (int i = heights.length-1; i >= 0 ; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            right[i] = stack.isEmpty() ? heights.length : stack.peek();

            stack.push(i);
        }

        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] -1) * heights[i]);
        }

        return maxArea;
    }

    public int bestSolution(int[] heights) {

        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }

            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * heights[i]);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};

        System.out.println(new LargestRectangleArea().bestSolution(heights));
    }
}
