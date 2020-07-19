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
 * 时间复杂度：由于只有一层for循环，所以整个的时间复杂度为O(n)
 * 空间复杂度: 由于只使用了一个变量保存最大面积，两个指针遍历数组，所以空间复杂度为O(1)
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

    /*
    左右指针向中间夹逼思路优化，在移动左指针或右指针时，
    使用while循环一直移动到比当前指针所在高度更大的位置，以减少面积的计算

    */
    public int maxAreaOptim(int[] height) {
        int area = 0;
        int left = 0, right = height.length-1;
        while(left < right) {
            area = Math.max(area,(right-left) * Math.min(height[left], height[right]));

            if(height[left] > height[right]) {
                //右指针高度更小，移动右指针
                int tmp = right;
                while(left < right && height[right] < height[tmp]) {
                    right--;
                }
            }else {

                int tmp = left;
                while(left < right && height[left] < height[tmp]) {
                    left++;
                }
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[] height = {1,8};

        System.out.println(new MaxArea().maxArea(height));
    }
}
