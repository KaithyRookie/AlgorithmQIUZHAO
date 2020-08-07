import java.util.ArrayList;
import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *

 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {

        int len = triangle.size();
        int col = triangle.get(len-1).size();
        int[] memo = new int[col];
        for (int i = 0; i < col; i++) {
            memo[i] = triangle.get(len-1).get(i);
        }

        for (int i = len-2; i >= 0 ; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                memo[j] = Math.min(memo[j], memo[j+1]) + triangle.get(i).get(j);
            }
        }

        return memo[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();

        triangle.add(List.of(2));
        triangle.add(List.of(3,4));
        triangle.add(List.of(6,5,7));
        triangle.add(List.of(4,1,8,3));

        System.out.println(new Triangle().minimumTotal(triangle));
    }
}
