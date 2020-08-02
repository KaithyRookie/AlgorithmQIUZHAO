import java.util.Arrays;

/**
 * 455. 分发饼干
 * @author kaithy.xu
 * @date 2020-08-02 22:37
 */
public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        int index = 0;
        for (int i = 0; i < s.length; i++) {
            if(index < g.length && g[index] <= s[i]) {
                count++;
                index++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] g = {10, 9, 8, 7};
        int[] s = {5, 6, 7, 8};
        System.out.println(new AssignCookies().findContentChildren(g, s));
    }
}
