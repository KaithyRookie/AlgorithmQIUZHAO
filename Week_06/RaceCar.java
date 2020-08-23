import java.util.Arrays;
import java.util.LinkedList;

/**
 * 818. 赛车
 *
 * @author kaithy.xu
 * @date 2020-08-23 16:12
 */
public class RaceCar {

    private static class Pair {
        int step;
        int pos;
        int speed;

        public Pair(int step, int pos, int speed) {
            this.step = step;
            this.pos = pos;
            this.speed = speed;
        }
    }

    /**
     * 动态规划
     * @param target
     * @return
     */
    public int racecar(int target) {

        int[] dp = new int[target + 3];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 4;

        for (int i = 3; i <= target ; i++) {
            int k = 32 - Integer.numberOfLeadingZeros(i);
            if(i == (1 << k) - 1) {
                dp[i] = k;
                continue;
            }

            for (int j = 0; j < k-1; j++) {

                dp[i] = Math.min(dp[i], dp[i-(1 << (k-1)) + (1 << j)] + k-1+j+2);

                if((1 << k) - 1 - i < i) {
                    dp[i] = Math.min(dp[i], dp[(1 << k) -1 -i] + k + 1);
                }
            }
        }

        return dp[target];
    }

    private int BFS(int target) {
        int speed = 1;
        int pos = 0;
        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0,pos,speed));
        while (!queue.isEmpty()) {
            Pair tmp = queue.remove();
            int position = tmp.pos;
            int tmpSpeed = tmp.speed;
            int count = tmp.step;
            if(position == target) {
                return count;
            }

            queue.add(new Pair(count+1, position, tmpSpeed > 0 ? -1 : 1));
            queue.add(new Pair(count+1,position+tmpSpeed, tmpSpeed*2));
        }

        return 0;
    }

    public static void main(String[] args) {
        int target = 6;

        System.out.println(new RaceCar().racecar(target));
    }
}
