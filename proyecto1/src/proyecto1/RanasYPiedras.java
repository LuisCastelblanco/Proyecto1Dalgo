package proyecto1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RanasYPiedras {

    private static final int MOD = 998244353;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        List<Integer> resultados = new ArrayList<>(); // Para almacenar los resultados

        while (t-- > 0) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            int m = scanner.nextInt();
            char[] stones = scanner.next().toCharArray();
            int[][][] dp = new int[p][m + 1][1 << p];

            int resultado = countArrangements(stones, m, 0, dp);
            resultados.add(resultado); // Almacenar cada resultado
        }

        scanner.close();

        // Imprimir todos los resultados al final
        for (int resultado : resultados) {
            System.out.println(resultado);
        }
    }

    static int countArrangements(char[] stones, int m, int idx, int[][][] dp) {
        if (m == 0 || !canMoveAnyMore(stones)) {
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

        int count = countArrangements(stones, m, idx + 1, dp); 

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

    static boolean canMoveAnyMore(char[] stones) {
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] == 'r') {
                if (i > 0 && stones[i - 1] == 'p' || i < stones.length - 1 && stones[i + 1] == 'p') {
                    return true;
                }
            }
        }
        return false;
    }
}
