package proyecto1;

import java.util.HashMap;
import java.util.Scanner;

public class RanasYPiedras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ingrese el número de casos
        int numCasos = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        StringBuilder resultados = new StringBuilder();

        for (int caso = 0; caso < numCasos; caso++) {
            // Ingrese el número de piedras, el número de ranas, el número de movimientos y la disposición inicial
            String[] valores = scanner.nextLine().split(" ");
            int p = Integer.parseInt(valores[0]);
            int r = Integer.parseInt(valores[1]);
            int m = Integer.parseInt(valores[2]);
            String disposicionInicial = valores[3];

            int resultado = contarDisposiciones(p, r, m, disposicionInicial);
            resultados.append(resultado).append(" ");
        }

        scanner.close();

        // Imprimir todos los resultados juntos
        System.out.println(resultados.toString().trim());
    }

    public static int contarDisposiciones(int p, int r, int m, String disposicion) {
        // Llamar a la función recursiva para calcular el número de disposiciones válidas
        return contarDisposicionesRecursivo(p, r, m, disposicion, new HashMap<>());
    }

    private static int contarDisposicionesRecursivo(int p, int r, int m, String disposicion, HashMap<String, Integer> memo) {
        // Si ya hemos calculado el resultado para esta disposición, devolvemos el valor almacenado en memo
        String estado = p + "-" + r + "-" + m + "-" + disposicion;
        if (memo.containsKey(estado)) {
            return memo.get(estado);
        }

        // Caso base: si no hay movimientos, entonces es una disposición válida.
        if (m == 0) {
            return 1;
        }

        // Caso base: si no hay ranas para colocar en las piedras libres, no hay más disposiciones válidas.
        if (r == 0) {
            return 0;
        }

        int resultado = 0;
        int n = disposicion.length();

        // Recorrer la disposición y realizar movimientos válidos
        for (int i = 0; i < n - 1; i++) {
            if (disposicion.charAt(i) == 'p' && disposicion.charAt(i + 1) == 'r') {
                // Realizar un movimiento válido: cambiar la posición de una rana a la piedra libre.
                StringBuilder nuevaDisposicion = new StringBuilder(disposicion);
                nuevaDisposicion.setCharAt(i, 'r');
                nuevaDisposicion.setCharAt(i + 1, 'p');
                resultado += contarDisposicionesRecursivo(p, r - 1, m - 1, nuevaDisposicion.toString(), memo);
            }
        }

        // Almacenar el resultado en memo y devolverlo
        memo.put(estado, resultado);
        return resultado;
    }
}
