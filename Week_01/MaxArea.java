/**
 * 11. 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)
 * 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 解题思路：
 * 两边夹逼：利用头尾指正，在向中间移动的过程中计算所能形成的最大面积，在移动指针是，每次只选择两个指针中高度最小的一侧，向中间移动
 *
 * @author kaithy.xu
 * @date 2020-07-17 09:28
 */
public class MaxArea {

    public int maxArea(int[] height) {
        int area = 0;

        for (int left = 0, right= height.length-1; left < right ; ) {

            area = Math.max(area,(right-left) * Math.min(height[left], height[right]));

            if(height[left] > height[right]) {
                right--;
            }else {
                left++;
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[] height = {1,8};

        System.out.println(new MaxArea().maxArea(height));
    }
}
