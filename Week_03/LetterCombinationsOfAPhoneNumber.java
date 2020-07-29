import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * @author kaithy.xu
 * @date 2020-07-29 20:24
 */
public class LetterCombinationsOfAPhoneNumber {

    private static final Map<Character, String> record = new HashMap<>();

    static {
        record.put('2',"abc");
        record.put('3',"def");
        record.put('4', "ghi");
        record.put('5',"jkl");
        record.put('6',"mno");
        record.put('7',"pqrs");
        record.put('8',"tuv");
        record.put('9', "wxyz");
    }


    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        acquire(digits, 0, result, "");

        return result;
    }

    /**
     * 回溯法
     * @param digits
     * @param index
     * @param result
     * @param target
     */
    private void acquire(String digits, int index, List<String> result, String target) {
        if(index == digits.length()) {
            result.add(target);
            return;
        }

        String t = record.get(digits.charAt(index));
        char[] tmp = t.toCharArray();

        for (int i = 0; i < tmp.length; i++) {
            acquire(digits, index+1,result, target+tmp[i]);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(new LetterCombinationsOfAPhoneNumber().letterCombinations(digits));
    }
}
