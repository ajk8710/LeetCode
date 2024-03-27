package slidingWindow.easy;

// You are given an array prices where prices[i] is the price of a given stock on the ith day.
// You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
// Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
public class LC121BestTimeToBuyAndSellStock {
    
    // Two Pointer technique: Time O(n), Memory Space O(1).
    // Let past = index 0, future = index 1.
    // Compute prices[future] - prices[past]. Update max if needed.
    // Only advance past if future price < past price (found new minimum point to buy).

    // Basically, having current minimum point, compute profit between its futures (advancing future pointer).
    // Set new minimum point if found. Continue advancing future. Compute profit & update max if needed.
    // (Why buy higher in past, when I can buy lower in this future. If I was supposed to sell before this new minimum,
    //  I already have compute it as current max profit.)
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int past = 0, future = 1;

        while (future < prices.length) {
            int profit = prices[future] - prices[past];  // Compute profit
            if (profit > maxProfit) {  // Update max if new max found
                maxProfit = profit;
            }
            if (prices[future] < prices[past]) {  // Found new minimum point to buy
                past = future;
            }
            future++;
        }

        return maxProfit;
    }
    
    
    // Brute Force
    // For each buying date i & future selling dates j > i, find the max of prices[j] - prices[i]
    public int maxProfitBruteForce(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {  // Two pointer technique advances i to j when new min found.
            for (int j = i + 1; j < prices.length; j++) {  // Then when j reaches to prices.length, whole loop ends instead of running outer loop.
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }

        return maxProfit;
    }
    
}
