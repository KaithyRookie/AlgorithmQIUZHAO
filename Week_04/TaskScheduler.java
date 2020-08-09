import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 621. 任务调度器
 * 
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU
 * 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * @author kaithy.xu
 * @date 2020-08-09 20:43
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        return solutionByDesign(tasks, n);

    }

    /**
     * 设计角度，
     * 先根据任务数对任务类型进行排序，根据最大的任务数(假设类型为A) max 构建一个 (max-1) * n 的矩阵,因为最后执行完A 就意味着CPU任务执行结束
     * 每一行代表一轮，每一列代表一种任务类型或者CPU等待
     * 以 A A A B B B 为例，n=2, 
     * 构建的矩阵：
     * A B wait
     * A B wait
     * A B
     * 
     * 所以总的任务时间为 任务数 + wait的数量，通过计算可以得到 总的格子数为 idles = (max-1) * n
     * 依次减去除A外各个类型的任务数，最后判断 rest_idles 是否 > 0，是：result = rest_idles + tasks 否： tasks
     * @param tasks
     * @param n
     * @return
     */
    public int solutionByDesign(char[] tasks, int n) {
        int[] space = new int[26];
        for (char task : tasks) {
            space[task-'A']++;
        }

        Arrays.sort(space);

        int max = space[25]-1, maxIdles = max * n;

        for (int i = 24; i >= 0 && space[i] > 0; i--) {
            maxIdles -= Math.min(space[i],max);
        }

        return maxIdles > 0 ? maxIdles + tasks.length : tasks.length;
    }

    /**
     * 先对任务进行排序，优先处理任务数多的任务类型
     * 
     * @param tasks
     * @param n
     * @return
     */
    public int solutionBySort(char[] tasks, int n) {
        int[] space = new int[26];
        for (char task : tasks) {
            space[task-'A']++;
        }
        Arrays.sort(space);

        int time= 0;
        while (space[25] > 0) {
            int i = 0;
            //冷却时间没有结束之前，一直递增time
            while (i <= n) {
                if(space[25] == 0) {
                    break;
                }
                if(i < 26 && space[25-i] > 0) {
                    space[25-i]--;
                }
                time++;
                i++;
            }

            Arrays.sort(space);
        }

        return time;
    }
}
