package proyecto1;

import java.util.Scanner;

public class RanasYPiedras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numCasos = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el número de casos

        for (int caso = 0; caso < numCasos; caso++) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            int m = scanner.nextInt();
            String disposicion = scanner.next();

            System.out.println("Disposiciones generadas para el caso " + (caso + 1) + ":");

            contarDisposiciones(p, r, m, disposicion);

            System.out.println(); // Agregar línea en blanco entre casos
        }

        scanner.close();
    }

    public static void contarDisposiciones(int p, int r, int m, String disposicion) {
        int[][][] dp = new int[m + 1][p][p];

        // Inicializar la matriz dp con la disposición inicial
        for (int fila = 0; fila < p; fila++) {
            for (int rana = 0; rana < p; rana++) {
                dp[0][fila][rana] = (disposicion.charAt(rana) == 'r') ? 1 : 0;
            }
        }

        // Calcular dp usando programación dinámica y contar disposiciones en cada movimiento
        for (int mov = 1; mov <= m; mov++) {
            for (int fila = 0; fila < p; fila++) {
                for (int rana = 0; rana < p; rana++) {
                    for (int nuevaRana = 0; nuevaRana < p; nuevaRana++) {
                        if (rana != nuevaRana) {
                            dp[mov][fila][rana] += dp[mov - 1][fila][nuevaRana];
                        }
                    }
                }
            }
        }

        // Imprimir la matriz en cada movimiento
        for (int mov = 0; mov <= m; mov++) {
            System.out.println("Matriz en el movimiento " + mov + ":");
            for (int fila = 0; fila < p; fila++) {
                for (int rana = 0; rana < p; rana++) {
                    System.out.print(dp[mov][fila][rana] + " ");
                }
                System.out.println();
            }
        }
    }
}
