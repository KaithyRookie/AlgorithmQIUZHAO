import java.util.Arrays;

/**
 *
 * 338. 比特位计数
 * 
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 
 * @author kaithy.xu
 * @date 2020-08-16 15:31
 */
public class CountingBits {

    /**
     * 利用位运算+DP方式，从 cur = 1 开始，每一次循环，都将 cur 有移一位得到 next，在 cur 与 next 之间的数字的 1 的个数满足 DP 公式：
     * DP[i] = DP[cur] + DP[i-cur]
     * @param num
     * @return
     */
    public int[] countBits(int num) {

        int[] result = new int[num+1];
        result[0]= 0;
        int cur = 1;
        while (cur <= num) {
            result[cur] = 1;
            int next = cur << 1;
            if(next > num) {
                next = num+1;
            }

            for (int i = cur+1; i < next; i++) {
                result[i] = result[cur] + result[i-cur];
            }
            cur = next;
        }

        return result;
    }

    public static void main(String[] args) {
        int num = 16;
        int[] result = new CountingBits().countBits(num);

        System.out.println(Arrays.toString(result));
    }

}
