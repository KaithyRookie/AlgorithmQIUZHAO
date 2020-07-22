public class MaxSlidingWindow {

	public int[] maxSlidingWindow (int[] nums, int k) {

		int length = nums.length;

		if(k*length == 0) {
			return new int[0];
		}

		if(k == 1) {
			return nums;
		}

		LinkedList<Integer> deque = new LinkedList<>();

		int[] result = new int[length-k+1];

		int max = 0;
		for (int i=0; i < k ; i++) {
			
			cleanDeque(deque, nums, i, k);

			deque.addLast(i);
			if(nums[i] > nums[max]) {
				max = i;
			}
		}

		result[0] = nums[max];

		for (int i = k; i < length ; i++) {
			cleanDeque(deque, nums, i, k);
			deque.addLast(i);

			result[i-k+1] = nums[deque.getFirst()];
		}

		return result;
	}

	private void cleanDeque(LinkedList<Integer> deque, int[] nums, int i, int k) {

		int dif = i - k;
		while(!deque.isEmpty() && deque.getFirst() == dif ) {
			deque.removeFirst();
		}

		while(!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
			deque.removeLast();
		} 
	}
}