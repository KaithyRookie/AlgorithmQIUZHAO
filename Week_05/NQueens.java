public class NQueens {

	public List<List<String>> solveNQueens(int n) {
        int[] region = new int[n];

 		List<List<String>> result = new ArrayList();
        calculate(0, n, region, result);

        return result;
    }

    private void calculate(int row, int n, int[] region, List<List<String>> result) {
    	if(row ==  n) {
    		recordResult(n, region, result);
    	}

    	for(int col = 0; col < n; ++col) {
    		if(check(row, col,region, n)) {
    			region[row] = col;
    			calculate(row+1, n, region, result);
    		}
    	}
    }

    private void recordResult(int n, int[] region, List<List<String>> result) {

    	List<String> record = new ArrayList(n);

    	for (int row = 0; row < n; ++row) {
    		StringBuilder builder = new StringBuilder();
    		for (int col=0; col < n; ++col) {
    			if(col == region[row]) {
    				builder.append("Q");
    			}else{
    				builder.append(".");
    			}
    		}
    		record.add(builder.toString());
    	}

    	result.add(record);
    }

    private boolean check(int row, int col, int[] region, int n) {
    	int leftUp = col - 1, rightUp = col+1;
    	for (int i = row - 1; i >= 0; --i) {
    		if(region[i] == col) {
    			return false;
    		}
    		if(leftUp >= 0) {
    			if(result[i] == leftUp) {
    				return false;
    			}
    		}

    		if(rightUp < n) {
    			if(result[i] == rightUp) {
    				return false;
    			}
    		}

    		--leftUp;
    		++rightUp;
    	}

    	return true;
    }
}