import algorithm.tree.Trie;

import java.util.*;

/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同
 *
 * @author kaithy.xu
 * @date 2020-06-23 21:36
 */
public class WordLadder {

    private static class Pair{
        String word;
        int level;

        public Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return bfs(beginWord, endWord, wordList);
    }

    /**
     * 广度优先搜索
     * 首先对wordlist中的每一个元素word进行预处理，用*替换word的每一个位置，然后保存进散列表中，key为 a*b类型，value为列表，包含含有该模式串的所有word
     * 构造一个队列，从 beginWord开始，一次用 * 替换每个位置，在散列表中找到该模式串对应的列表，并用一个集合set保存已经访问过的合集，遍历列表，对没有访问过的word，入队列
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int bfs2(String beginWord, String endWord, List<String> wordList) {

        int depth = beginWord.length();

        Map<String, List<String>> dictMap = new HashMap<>(depth*wordList.size()*3/2);
        for (String word : wordList) {

            for (int i = 0; i < depth; i++) {
                String newWord = word.substring(0,i)+"*"+word.substring(i+1,depth);
                List<String> list = dictMap.getOrDefault(newWord,new ArrayList<>());
                list.add(word);
                dictMap.put(newWord, list);
            }
        }

        Set<String> visited = new HashSet<>();

        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(beginWord,1));

        while (!queue.isEmpty()) {
            Pair pair = queue.remove();
            int level = pair.level;
            String tmp = pair.word;
            for (int i = 0; i < depth; i++) {
                String newWord = tmp.substring(0,i)+"*"+tmp.substring(i+1,depth);
                for (String t : dictMap.getOrDefault(newWord, new ArrayList<>())) {

                    if(t.equals(endWord)) {
                        return level+1;
                    }
                    if(!visited.contains(t)) {
                        visited.add(t);
                        queue.add(new Pair(t, level+1));
                    }

                }
            }
        }

        return 0;
    }


    /**
     * 广度优先级搜索
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    private int bfs(String beginWord, String endWord, List<String> wordList) {
        Map<String, Boolean> visited = new HashMap<>(wordList.size()*3/2);

        wordList.forEach(word -> {
            visited.put(word, false);
        });

        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.remove();
            String tmp = pair.word;
            int level = pair.level;

            visited.put(tmp,true);
            if(tmp.equals(endWord)) {
                return level+1;
            }
            for (String word : wordList) {
                if(!visited.get(word) && isValid(tmp, word)) {
                    queue.add(new Pair(word, level+1));
                }
            }
        }

        return 0;
    }

    private boolean isValid(String word, String target) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) != target.charAt(i)) {
                count++;
            }

            if(count > 1) {
                return false;
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        String begine = "hit";

        String end = "cog";

        List<String> wordList = List.of("hot","dot","dog","lot","log");

        System.out.println(new WordLadder().ladderLength(begine, end, wordList));
    }
}
