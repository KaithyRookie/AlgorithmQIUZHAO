import java.util.*;

/**
 * 49. 字母异位词分组
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 解题思路：
 * 整体思路上还是依靠HashMap 做一个映射关系
 *
 * 解法1：首先对字符串的字符数组进行排序，然后转为字符串作为HashMap的key,value这是key在结果列表中的位置
 *
 * 时间复杂度：O(nKlog(K): 因为 Arrays.sort() 排序的时间复杂度为O（KlogK)其中K是字符串中字符数；外层循环n次
 * 空间复杂度: 空间复杂度上，使用了HashMap存放key和index，其中key的长度为K，一共有 n 个字符串，所以需要 O(nK)
 *
 * 解法2：利用26个素数构成的数组，对每个字符串计算hash值，将hash值作为HashMap的key，value依旧是对应的结果列表中的下标
 * 这里用到了算术基本定理：一个正整数要么自身是素数，要么是多个素数的乘积
 * 时间复杂度：O(nK)：hash的计算是常数级别的，但是需要遍历K次，外层的循环n，
 * 空间复杂度：于1相同，都是O(nK)
 * 
 *
 * 解法3：将字符串的字符统计数据转换为key,这里没有硬性规定一定要使用那种格式，自己用的是 下标:计数; 的格式
 * 时间复杂度：字符串长度为K，统计字符数据会遍历一次，外层为n，总的时间复杂度为O(nK)
 * 空间复杂度：StringBuilder 基于动态数组,由于key的模式相同，长度固定为4, 可以认为是常量级的，所以总的空间复杂度 与1相同，是O(nK)
 *
 * @author kaithy.xu
 * @date 2020-07-18 21:35
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, Integer> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            int index = map.getOrDefault(key, -1);
            if(-1 != index) {
                result.get(index-1).add(str);
            }else {
                List<String> list = new ArrayList<>();
                list.add(str);
                result.add(list);

                map.put(key, result.size());
            }
        }
        return result;
    }

    private int[] seeds = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    public List<List<String>> groupAnagrams2(String[] strs) {

        Map<Double, Integer> recordMap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        for (String str : strs) {
            Double hash = calculateHash(str);

            int index = recordMap.getOrDefault(hash,-1);
            if( -1 == index) {
                List<String> list = new ArrayList<>();
                list.add(str);
                result.add(list);
                recordMap.put(hash, result.size());
            }else {
                result.get(index-1).add(str);
            }
        }

        return result;
    }

    private Double calculateHash(String str){
        Double hash = 1D;

        for (Character c : str.toCharArray()) {
            hash *= seeds[c.charValue()-'a'];
        }
        return hash;
    }


    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, Integer> recordMap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        for (String str : strs) {

            int[] count = new int[27];
            for (Character c : str.toCharArray()) {
                count[c-'a'] += 1;
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 27; i++) {
                if(count[i] > 0) {
                   builder.append(i).append(":").append(count[i]).append(";");
                }
            }
            String key = builder.toString();

            int index = recordMap.getOrDefault(key, -1);

            if( -1 == index) {
                List<String> list = new ArrayList<>();
                list.add(str);
                result.add(list);
                recordMap.put(key, result.size());
            }else {
                result.get(index-1).add(str);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = new GroupAnagrams().groupAnagrams2(strs);

        for (List<String> stringList : result) {
            System.out.println(stringList.toString());
        }
    }
}
