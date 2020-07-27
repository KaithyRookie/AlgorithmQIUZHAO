/**
 * 50. Pow(x, n)
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 解题思路：
 * 利用递归+快速幂
 *
 * 即递归求解  x^N -> x^N/2 -> x^N/4 -> x^ N/8 -> ... -> x^1 -> x^0=1
 *
 * 在拿到递归结果y后，判断当前n是否是奇数，若是 则 x*y*y, 否则 y*y，
 *
 * @author kaithy.xu
 * @date 2020-07-27 21:04
 */
public class PowxN {

    public double myPow(double x, int n) {
        if(n == 0) {
            return 1;
        }
        if(x == 1 || x == 0) {
            return x;
        }

        if(n == 1) {
            return x;
        }
        if(n == -1) {
            return 1.0D/x;
        }

        return calculate2(x, n);

    }

    private double calculate(double x, int n) {
        if(n == 0) {
            return 1.0;
        }
        double y = calculate(x, n/2);

        return n % 2 == 0 ? y * y : x*y*y;
    }

    /**
     * 利用二进制思想+递归算法
     * n 转换为二进制为 1*b+2*b+4*b+..+2^i*b 其中 b = 0 或 1
     * 原理：x^n = x^(1*b+2*b+4*b+..+2^i*b) = (x^1*b) * (x^2*b) * (x^4*b) * ... * (x^2^i*b)
     * 
     * 因此，每次将n 向右移一位，通过与 1 做 & 操作判断最后一位是否为1，为1则乘上当前的 x，否则忽略，直到 n == 0 是推出循环
     * 每次循环，除了将n右移一位外，并将 x*=x 
     * @param x
     * @param n
     * @return
     */
    private double calculate2(double x, int n) {

        long b = n;

        if( b < 0) {
            x = 1/ x;
            b = -b;
        }
        double res = 1.0D;
        while (b > 0) {
            if((b & 1) == 1) {
                res *= x;
            }
            x *=x;
            b >>=1;
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(new PowxN().myPow(2.0D,10));
    }
}
