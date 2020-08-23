学习笔记
### 冒泡排序

```
class BubbleSort {
    public void bubbleSort(int[] nums) {
    	int len = nums.length;

    	for (int i = 0; i < len; ++i) {
    		boolean isSorted = false;

    		for (int j=0; j < len; ++j) {
    			if(nums[j+1] < nums[j]) {
    				swap(nums, j+1, j);
    				isSorted = true;
    			}
    		}
    		--len;
    		if(!isSorted) {
    			break;
    		}
    	}
    }

    private void swap(int[] nums, int left, int right) {
    	int t = nums[left];
    	nums[left] = nums[right];
    	nums[right] = nums[left];
    }
}
```

### 选择排序

```

class ChoseSort {
    public void choseSort(int[] nums) {
    	int len = nums.length;

    	for (int i = 0; i < len; ++i) {
    		int min = i;

    		for (int j=i+1; j < len; ++j) {
    			if(nums[j] < nums[min]) {
    				min = j;
    			}
    		}
    		
    		if(min > i)
    			swap(nums, i, min);
    	}
    }

    private void swap(int[] nums, int left, int right) {
    	int t = nums[left];
    	nums[left] = nums[right];
    	nums[right] = nums[left];
    }
}
```

### 插入排序

```
class InsertSort {
    public void insertSort(int[] nums) {
    	int len = nums.length;

    	for (int i = 1; i < len; ++i) {
    		int j = i-1;
    		int pivot = nums[i];

    		while(j >= 0) {
    			if(nums[j] > pivot) {
    				nums[j+1] = nums[j];
    				--j;
    			}else {
    				break;
    			}
    		}

    		nums[j+1] = pivot;
    	}
    }

}

```

### 快速排序

```
class QuickSort {
    public void quickSort(int[] nums) {
    	int len = nums.length;

    	sort(nums, 0, len-1);
    }

    private void sort(int[] nums, int left, int right) {
    	if(left >= right) {
    		return;
    	}

    	int pivot = partition(nums, left, right);

    	sort(nums, left, pivot-1);
    	sort(nums, pivot+1, right);
    }

    private int partition(int[] nums, int left, int right) {

    	int pivot = chosePivot(nums, left, right);

    	int cur = left;
    	for (int i = left; i < right; ++i) {
    		if(nums[i] <= pivot) {
    			swap(nums, i, cur++);
    		}
    	}

    	return cur;
    }

    private int chosePivot(int[] nums, int left, int right) {
    	int mid = (right - left >> 1) + left;
    	if(nums[left] > nums[right]) {
    		swap(nums, left, right);
    	}

    	if(nums[mid] > nums[right]) {
    		swap(nums, mid, right);
    	}

    	if(nums[left] > nums[mid]) {
    		swap(nums, left, mid);
    	}

    	return nums[mid];
    }

    private void swap(int[] nums, int left, int right) {
    	int t = nums[left];
    	nums[left] = nums[right];
    	nums[right] = nums[left];
    }
}

```

### 堆排序

```
class HeapSort {
    public void heapSort(int[] nums) {
    	int len = nums.length;
    	
    	for (int i= (len - 1) >> 1; i >= 0; --i) {
    		
    		heapify(nums, i, len);
    	}

    	for (int i = len -1; i >= 0; --i) {
    		
    		swap(nums, i, 0);

    		heapify(nums, 0, --len);
    	}
    }

    private void heapify(int[] nums, int i, int len) {
    	int maxPos;
    	while(true) {
    		maxPos = i;
    		if(i*2+1 < len && nums[i] < nums[i*2+1]) {
    			maxPos = i*2+1;
    		}
    		if(i*2+2 < len && nums[maxPos] < nums[i*2+2]) {
    			maxPos = i * 2+2;
    		}
    		if(maxPos == i) {
    			return;
    		}

    		swap(nums, maxPos, i);
    		i = maxPos;
    	}
    }

    

    private void swap(int[] nums, int left, int right) {
    	int t = nums[left];
    	nums[left] = nums[right];
    	nums[right] = nums[left];
    }
}

```