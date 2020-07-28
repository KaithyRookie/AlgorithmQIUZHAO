import java.util.ArrayList;
import java.util.List;

/**
 *
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * @author kaithy.xu
 * @date 2020-07-28 20:27
 */
public class Subsets {

    List<List<Integer>> result;
    int n;

    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        n = nums.length;
        for (int i = 0; i < n+1; i++) {

            acquire(0, new ArrayList<>(), i, nums);
        }

        return result;
    }

    /**
     * 解法一：迭代
     * 每遍历数组中的一个元素，将输出的result中的所有list取出来，加上当前元素，然后再将这些新的list加入到输出的result中
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsByIteration(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> newSubList = new ArrayList<>();


            for (List<Integer> cur : result) {
                List<Integer> t = new ArrayList<>(cur);
                t.add(nums[i]);
                newSubList.add(t);
            }

            for (List<Integer> cur : newSubList) {
                result.add(cur);
            }

        }

        return result;
    }

    /**
     * 解法二：回溯
     * 根据数组元素的个数进行分层，从第0层开始到第n层
     * 每次判断当前的 list的size是否等于层数中元素的个数限制，若相等这加入到输出结果后返回
     * 否则lsit加上当前元素后，向下递归数组中的下一个元素，当回溯时，则将list中的最后一个元素移除
     *
     * @param first
     * @param cur
     * @param length
     * @param nums
     */
    private void acquire(int first, List<Integer> cur, int length,int[] nums) {
        if(length == cur.size()) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = first; i < n; i++) {
            cur.add(nums[i]);
            acquire(i+1, cur, length, nums);

            cur.remove(cur.size()-1);
        }
    }

    /**
     * 分治思想：
     * 每递归一次时，判断是否添加当前下标的元素，
     * @param index
     * @param nums
     * @param cur
     */
    private void subsetsByReverse(int index, int[] nums, List<Integer> cur) {
        if(index == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }

        subsetsByReverse(index+1, nums, cur);
        cur.add(nums[index]);
        subsetsByReverse(index+1, nums, cur);
        cur.remove(cur.size()-1);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> result = new Subsets().subsetsByIteration(nums);
        for (List<Integer> r : result) {
            System.out.println(r);
        }
    }
}
