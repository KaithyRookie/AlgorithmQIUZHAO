import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * 433. 最小基因变化
 *
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 *
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 *
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 *
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 *
 * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
 *
 * 注意:
 *
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 *
 * @author kaithy.xu
 * @date 2020-07-30 17:22
 */
public class MinimumGeneticMutation {

    public int minMutation(String start, String end, String[] bank) {

        return bfs(start, end, bank);
    }

    /**
     * 广度优先搜索，根据题意，一共有四种字符A C G T，首先将start入队并出队，遍历出队列的 字符串 tmp，用chars[]中的四个字符分别替换当前位置，
     * 然后判断是否已经被使用，并且在 bank中的，如果满足条件则入队列
     * @param start
     * @param end
     * @param bank
     * @return
     */
    private int bfs(String start, String end, String[] bank) {

        Set<String> bankSet = new HashSet<>(bank.length*3/2);
        for(String b : bank) {
            bankSet.add(b);
        }

        LinkedList<String> queue = new LinkedList<>();

        queue.add(start);
        int result = 0;

        Set<String> visited = new HashSet<>(bank.length*3/2);

        char[] chars = {'A', 'C', 'G', 'T'};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String tmp = queue.poll();
                if(tmp.equals(end)) {
                    return result;
                }

                char[] curr = tmp.toCharArray();

                for (int j = 0; j < curr.length; j++) {
                    char old = curr[j];
                    for (int k = 0; k < 4; k++) {
                        curr[j] = chars[k];

                        String newString = new String(curr);

                        if(!visited.contains(newString) && bankSet.contains(newString)) {
                            visited.add(newString);
                            queue.add(newString);
                        }
                    }
                    curr[j] = old;
                }

            }
            //按层次遍历时，每一层遍历结束后，变动次数+1
            result++;
        }

        return  -1;
    }

    /**
     * 深度优先搜索，每次递归，找到与当前start字符相差一个的字符串，并且没有访问过的字符串，然后在visited中记录下该字符串，将该字符串作为start 进行下一轮的递归，
     */
    private int count = Integer.MAX_VALUE;
    private int dfs(int step, String start, String end, Set<String> visited, String[] bank) {
        if(start.equals(end)) {
            count = Math.min(step, count);
            return count;
        }
        
        for (String b : bank) {

            int diff = 0;
            for (int i = 0; i < b.length(); i++) {
                if(start.charAt(i) != b.charAt(i)){
                    diff++;
                }
                if(diff > 1) {
                    break;
                }
            }
            if(diff == 1 && !visited.contains(b)) {
                visited.add(b);
                dfs(step+1, b, end, visited, bank);
                visited.remove(b);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGATT","AACCGATA","AAACGATA","AAACGGTA"};
        System.out.println(new MinimumGeneticMutation().minMutation(start, end, bank));
    }
}
