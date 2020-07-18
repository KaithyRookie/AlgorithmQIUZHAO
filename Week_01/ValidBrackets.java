import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 *
 * 解题思路：
 *
 * 使用栈来保存左括号，当遇到右括号时，则弹出栈顶的元素，判断是否是对应的右括号
 *
 * 时间复杂度：只需要遍历一遍字符串的所有字符，O(n)，n是字符串中字符的个数
 * 空间复杂度：由于使用了栈来保存左括号，所以需要额外的O(n)的空间
 * 
 * @author kaithy.xu
 * @date 2020-07-18 10:00
 */
public class ValidBrackets {

    public boolean isValid(String s) {
        int length = s.length() ;

        LinkedList<Character> stack = new LinkedList<>();

        char[] chars = s.toCharArray();

        String leftBrackets = ")}]";

        for (int i = 0; i < length; i++) {

            int index=-1;
            if((index = leftBrackets.indexOf(chars[i])) == -1) {
                stack.push(chars[i]);
            }else {
                if(stack.isEmpty()) {
                    return false;
                }
                Character r = stack.pop();
                if(0 == index && r != '(') {
                    return false;
                }else if(1 == index && r != '{') {
                    return false;
                }else if(2 == index && r != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "([)]";

        System.out.println(new ValidBrackets().isValid(s));
    }
}
