package proyecto1;

import java.util.Scanner;

public class RanasYPiedras {

    private static final int MOD = 998244353;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            int m = scanner.nextInt();
            char[] stones = scanner.next().toCharArray();
            int[][][] dp = new int[p][m + 1][1 << p];

            System.out.println("Resultado: " + countArrangements(stones, m, 0, dp));
        }

        scanner.close();
    }

    static int countArrangements(char[] stones, int m, int idx, int[][][] dp) {
        if (m == 0) {
            return 1;
        }

        if (idx >= stones.length) {
            return 0;
        }

        int mask = 0;
        for (int i = 0; i < stones.length; i++) {
            mask <<= 1;
            mask |= stones[i] == 'r' ? 1 : 0;
        }

        if (dp[idx][m][mask] != 0) {
            return dp[idx][m][mask];
        }

        // No move is made
        int count = countArrangements(stones, m, idx + 1, dp);

        // Try moving the frog to the left or right if possible
        if (stones[idx] == 'r') {
            if (idx > 0 && stones[idx - 1] == 'p') {
                stones[idx] = 'p';
                stones[idx - 1] = 'r';
                count += countArrangements(stones, m - 1, idx + 1, dp);
                count %= MOD;
                stones[idx] = 'r';
                stones[idx - 1] = 'p';
            }

            if (idx < stones.length - 1 && stones[idx + 1] == 'p') {
                stones[idx] = 'p';
                stones[idx + 1] = 'r';
                count += countArrangements(stones, m - 1, idx + 1, dp);
                count %= MOD;
                stones[idx] = 'r';
                stones[idx + 1] = 'p';
            }
        }

        dp[idx][m][mask] = count;
        return count;
    }
}
