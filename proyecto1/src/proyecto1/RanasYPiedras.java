package proyecto1;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class RanasYPiedras {
	
	public static String disposicionOriginal;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numCasos = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el número de casos

        for (int caso = 0; caso < numCasos; caso++) {
            int p = scanner.nextInt();
            int r = scanner.nextInt();
            int m = scanner.nextInt();
            String disposicion = scanner.next();
            disposicionOriginal = new String (disposicion);

            Set<String> disposiciones = new HashSet<>();
            disposiciones.add(disposicion);

            int resultado = contarDisposiciones(p, r, m, disposicion, disposiciones);

            //Imprimir las disposiciones generadas para este caso
         System.out.println("Disposiciones generadas para el caso " + (caso + 1) + ":");
         for (String disp : disposiciones) {
               System.out.println(disp);
           }

            // Imprimir el número de disposiciones después de m movimientos
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
                    if (disposiciones.add(nuevaDisposicion) && esMovimientoValido(disposicionOriginal, nuevaDisposicion, i - 1, i)) {
                        resultado += contarDisposiciones(p, r, m - 1, disposicionOriginal, disposiciones);
                    }
                }
                if (i < p - 1 && disposicion.charAt(i + 1) == 'p') {
                    String nuevaDisposicion = disposicion.substring(0, i) + "pr" + disposicion.substring(i + 2);
                    if (disposiciones.add(nuevaDisposicion) && esMovimientoValido(disposicionOriginal, nuevaDisposicion, i, i + 1)) {
                        resultado += contarDisposiciones(p, r, m - 1, disposicionOriginal, disposiciones);
                    }
                }
            }
        }
        
        int respuesta = disposiciones.size() - 1;


        return respuesta;
    }

    // Función para verificar si un movimiento es válido
    public static boolean esMovimientoValido(String disposicionActual, String nuevaDisposicion, int ranaOriginal, int ranaNueva) {
        int distancia = Math.abs(ranaNueva - ranaOriginal);
        return distancia == 1;
    }
}
