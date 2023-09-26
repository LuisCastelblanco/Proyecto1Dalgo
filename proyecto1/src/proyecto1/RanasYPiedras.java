package proyecto1;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class RanasYPiedras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numCasos = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        for (int caso = 0; caso < numCasos; caso++) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            int m = scanner.nextInt();
            String disposicion = scanner.next();

            Set<String> disposiciones = new HashSet<>();
            disposiciones.add(disposicion); // Agregar la disposición inicial al conjunto
            int resultado = contarDisposiciones(p, r, m, disposicion, disposiciones);
            System.out.println(resultado);
        }

        scanner.close();
    }

    public static int contarDisposiciones(int p, int r, int m, String disposicion, Set<String> disposiciones) {
        if (m == 0) {
            return 1;
        }

        int resultado = 0;

        for (int i = 0; i < p; i++) {
            if (disposicion.charAt(i) == 'r') {
                if (i > 0 && disposicion.charAt(i - 1) == 'p') {
                    String nuevaDisposicion = disposicion.substring(0, i - 1) + "rp" + disposicion.substring(i + 1);
                    if (disposiciones.add(nuevaDisposicion)) { // Verificar si es una disposición nueva
                        resultado += contarDisposiciones(p, r, m - 1, nuevaDisposicion, disposiciones);
                    }
                }
                if (i < p - 1 && disposicion.charAt(i + 1) == 'p') {
                    String nuevaDisposicion = disposicion.substring(0, i) + "pr" + disposicion.substring(i + 2);
                    if (disposiciones.add(nuevaDisposicion)) { // Verificar si es una disposición nueva
                        resultado += contarDisposiciones(p, r, m - 1, nuevaDisposicion, disposiciones);
                    }
                }
            }
        }

        return resultado;
    }
}
