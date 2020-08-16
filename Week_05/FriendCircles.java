import java.util.LinkedList;

/**
 * @author kaithy.xu
 * @date 2020-08-16 16:16
 */
public class FriendCircles {

    /**
     * DFS解法
     * 时间复杂度O(n^2)
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int rows = M.length;
        int count =0;
        if(0 == rows) {
            return 0;
        }
        boolean[] visited = new boolean[rows];

        for (int i = 0; i < rows; i++) {
            if(!visited[i]) {
                dfs(M, i, visited);
                ++count;
            }
        }

        return count;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        for (int j = 0; j < M.length; j++) {
            if(!visited[j] && M[i][j] == 1) {
                visited[j] = true;
                dfs(M, j, visited);
            }
        }
    }

    private int bfs(int[][] M) {
        int rows = M.length;
        int count =0;
        if(0 == rows) {
            return 0;
        }
        boolean[] visited = new boolean[rows];

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            if(!visited[i]) {
                queue.add(i);
                
                while (!queue.isEmpty()) {
                    int cur = queue.remove();
                    visited[cur] = true;
                    for (int j = 0; j < rows; j++) {
                        if(!visited[j] && M[i][j] == 1) {
                            queue.add(j);
                        }
                    }
                }
                ++count;
            }
        }

        return count;
    }

    /**
     * 使用并查集解决问题
     * 时间复杂度O(n^3)
     * @param M
     * @return
     */
    public int solution(int[][] M) {
        int rows = M.length;
        if(0 == rows) {
            return 0;
        }

        UnionField unionField = new UnionField(rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if(M[i][j] == 1) {
                    unionField.union(i, j);
                }
            }
        }

        return unionField.getCount();
    }

    private static class UnionField {
        private int count;
        private int[] parent;

        public UnionField(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;

            }
        }

        public int find(int p) {
            int root = p;
            while (root != parent[root]) {
                parent[root] = parent[parent[root]];
                root = parent[root];
            }
            //压缩路径
            int i = p;
            while (i != root) {
                int tmp = i;
                i = parent[i];
                parent[tmp] = root;
            }

            return root;
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if(rootI != rootJ) {
                parent[rootJ] = rootI;
                --count;
            }
        }

        public int getCount() {
            return this.count;
        }
    }

    public static void main(String[] args) {
        int[][] M = {
            {1,1,0},
            {1,1,0},
            {0,0,1}
        };

        System.out.println(new FriendCircles().solution(M));
    }
}
