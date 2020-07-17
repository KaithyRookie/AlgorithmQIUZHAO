/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 结题思路：
 *
 * 解法一: 动态规划
 * 首先需要判断是否满足使用动态规划的几个基本条件：
 * 1. 可拆分，一个大的问题可以拆分成几个小问题
 * 2. 对于第i个小问题的解，是否由1~i-1的解决定
 *
 * 解动态规划的两种方式
 * 1.画出递归树，找到重复的操作，然后使用“备忘录”即数组的形式记录下来
 * 2.找到递推公式，利用递推公式求解
 *
 * 因为爬楼梯每次可以爬一层或两层台阶, 记f(n) 为第n层台阶的不同爬法
 * n:1 f(1) = 1
 * n:2 f(2) = 2 （走两次一层 或 一次 两层）
 * n:3 f(3) = 3 (三个1，一个2一个1， 一个1一个2）
 * n:4 f(4) = 5 (1111,112，121,211,22）
 * 由此可以得到对于第k层，f(k) = f(k-1)+f(k-2)
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        if(1 == n) {
            return 1;
        }
        int[] memo = new int[n];
        memo[0] = 1;
        memo[1] = 2;

        for (int i = 2; i < n; i++) {
            memo[i] = memo[i-1] + memo[i-2];
        }

        return memo[n-1];
    }

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs(3));
    }
}
