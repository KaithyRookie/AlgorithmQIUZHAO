学习笔记

#### 买卖股票的最佳时机系列问题求解
以下问题是自己在阅读完[一个方法团灭 6 道股票问题](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/)后记录的感悟

文中从暴力法开始，通过状态机的思路来得出DP中最为关键的状态转移表以及状态转移方程：

```
dp table： 
dp[n][K][other_state_0]...[other_state_n][hold_state]

其中
n：数组长度，即天数
K： 买卖股票的次数
other_state_i: 表示第i种状态
hold_state：当前股票持有状态，只有0：未持有股票 1:持有股票

状态转移方程:

dp[i][k][other_state_0_i]...[other_state_n_i][0] = 
MAX(dp[i-1][k][other_state_0_i']...[other_state_n_i'][0],dp[i-1][k][other_state_0_i']...[other_state_n_i'][1]+price[i])

dp[i][k][other_state_0_i]...[other_state_n_i][1] =  MAX(dp[i-1][k][other_state_0_i']...[other_state_n_i'][1], dp[i-1][k-1][other_state_0_i']...[other_state_n_i'][0] - price[i])

```

针对状态只有天数，交易次数，持有状态，那么可以简化转移方程与 DP 表：

```

dp[n][K][hold_state]

dp[i][k][0] = 
MAX(dp[i-1][k][0],dp[i-1][k][1]+price[i])

dp[i][k][1] =  MAX(dp[i-1][k][1], dp[i-1][k-1][0] - price[i])

```

而对于 K=1 或者 K 为无穷大(即不限制次数的情况下)，可以继续简化：

```

K=1:

dp[n][hold_state]

for i in n:
	dp[i][0] = MAX(dp[i-1][0],dp[i-1][1]+price[i])

	dp[i][1] =  MAX(dp[i-1][1], - price[i])

```

```

K无穷大:

dp[n][hold_state]

for i in n:

	dp[i][0] = MAX(dp[i-1][0],dp[i-1][1]+price[i])

	dp[i][1] = MAX(dp[i-1][1], dp[i-1][0]- price[i])

```

而针对 K > 1的情况下，又可以根据 K 与 n/2的大小关系分成两种情况，
当 K > n/2 时，也就是意味着不论如何交易，交易总次数都不会超过k，那么可以认为是K无限大的情况；
否则，则需要循环K，以此计算每一个k的dp结果

```

K < n/2

dp[n][K][hold_state]

for i in n:
	for k in K:
		dp[i][k][0] = MAX(dp[i-1][k][0],dp[i-1][k][1]+price[i])

		dp[i][k][1] =  MAX(dp[i-1][k][1], dp[i-1][k-1][0]- price[i])

```

对于存在冷却时间y的情况，只需要调整状态转移方程

```
 dp[i][k][1] = MAX(dp[i-1][1], dp[i-y-1][0]- price[i])

```

而对于需要交付交易费用Z的，只需要调整状态转移方程：

```
 dp[i][k][1] = MAX(dp[i-1][1], dp[i-1][0]- price[i] - Z)

```

