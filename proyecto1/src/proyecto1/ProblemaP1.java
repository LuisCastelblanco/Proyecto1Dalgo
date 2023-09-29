package proyecto1;

import java.util.Scanner;

public class ProblemaP1 {

    private static final int MOD = 998244353;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalCasos = scanner.nextInt();
        StringBuilder resultados = new StringBuilder();

        for (int caso = 0; caso < totalCasos; caso++) {
            int totalPiedras = scanner.nextInt();
            int totalRanas = scanner.nextInt();
            int totalMovimientos = scanner.nextInt();
            char[] estanque = scanner.next().toCharArray();
            int[][][] resultadosSubproblemas = new int[totalPiedras][totalMovimientos + 1][1 << totalPiedras];

            resultados.append(contarDisposiciones(estanque, totalMovimientos, 0, resultadosSubproblemas)).append("\n");
        }

        scanner.close();
        System.out.print(resultados);
    }

    static int contarDisposiciones(char[] estanque, int movimientosRestantes, int indice, int[][][] resultadosSubproblemas) {
        if (movimientosRestantes == 0) {
            return 1;
        }

        if (indice >= estanque.length) {
            return 0;
        }

        int mascaraEstado = 0;
        for (int i = 0; i < estanque.length; i++) {
            mascaraEstado <<= 1;
            mascaraEstado |= estanque[i] == 'r' ? 1 : 0;
        }

        if (resultadosSubproblemas[indice][movimientosRestantes][mascaraEstado] != 0) {
            return resultadosSubproblemas[indice][movimientosRestantes][mascaraEstado];
        }

        int totalDisposiciones = contarDisposiciones(estanque, movimientosRestantes, indice + 1, resultadosSubproblemas);

        if (estanque[indice] == 'r') {
            if (indice > 0 && estanque[indice - 1] == 'p') {
                estanque[indice] = 'p';
                estanque[indice - 1] = 'r';
                totalDisposiciones += contarDisposiciones(estanque, movimientosRestantes - 1, indice + 1, resultadosSubproblemas);
                totalDisposiciones %= MOD;
                estanque[indice] = 'r';
                estanque[indice - 1] = 'p';
            }

            if (indice < estanque.length - 1 && estanque[indice + 1] == 'p') {
                estanque[indice] = 'p';
                estanque[indice + 1] = 'r';
                totalDisposiciones += contarDisposiciones(estanque, movimientosRestantes - 1, indice + 1, resultadosSubproblemas);
                totalDisposiciones %= MOD;
                estanque[indice] = 'r';
                estanque[indice + 1] = 'p';
            }
        }

        resultadosSubproblemas[indice][movimientosRestantes][mascaraEstado] = totalDisposiciones;
        return totalDisposiciones;
    }
}
