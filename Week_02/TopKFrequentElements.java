import java.util.*;

/**
 *
 * 347. 前 K 个高频元素
 * 
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 
 * @author kaithy.xu
 * @date 2020-07-26 21:47
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> record = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {

            Integer count = record.getOrDefault(nums[i],0);
            record.put(nums[i],++count);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return record.get(o1) - record.get(o2);
            }
        });

        for (int n : record.keySet()) {
            heap.add(n);
            if(heap.size() > k) {
                heap.poll();
            }
        }

        LinkedList<Integer> result = new LinkedList<>();
        int[] topK = new int[k];

        int count = k-1;
        while (!heap.isEmpty()) {
            topK[count--] = heap.poll();
        }
        return topK;

    }

}
