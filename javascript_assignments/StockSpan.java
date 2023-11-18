import java.util.Stack;

public class StockSpan {
    public static int[] calculateStockSpan(int[] prices) {
        int n = prices.length;
        int[] spans = new int[n];
        spans[0] = 1;

        for (int i = 1; i < n; i++) {
            int span = 1;
            int j = i - 1;

            while (j >= 0 && prices[i] >= prices[j]) {
                span += spans[j];
                j -= spans[j];
            }

            spans[i] = span;
        }

        return spans;
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] spans = calculateStockSpan(prices);

        System.out.print("Stock Spans: ");
        for (int span : spans) {
            System.out.print(span + " ");
        }
    }
}