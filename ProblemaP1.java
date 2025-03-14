//AUTORES:
//WILLIAM POLLOCK, 202221321
//NICOLÁS HERNÁNDEZ, 202322148

import java.util.Scanner;
import java.util.Arrays;

public class ProblemaP1 {
    public static void main(String[] args) {
        //Usamos la clase Scanner para leer el archivo con las entradas del problema
        Scanner sc = new Scanner(System.in);
        //Acá, la variable W es el primer numero del archivo, que es el numero de casos de prueba en el archivo
        int W = sc.nextInt(); 
        //Iteramos para leer todas las lineas. Cada linea es un caso de prueba
        for(int casosPrueba = 0; casosPrueba < W; casosPrueba++) {
            int n = sc.nextInt();
            int j = sc.nextInt();
            int m = sc.nextInt();
            //Creamos un array con los pesos de los jugadores 
            int[] arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = sc.nextInt();
            }

            //Creamos una matriz tridimensional, para resolver el problema con Programacion Dinamica
            // el elemento DP[i][k][s] va a ser la mínima suma en una lista de i jugadores,
            // en la que suman solo los primeros k, gastando a lo sumo s swaps.
            // Dimensiones de la matriz: (n+1) x (j+1) x (m+1)
            int infinito = Integer.MAX_VALUE; 
            int[][][] DP = new int[ n+1][j+ 1][m+1];



            //asignamos un valor muy grande en las posiciones de la matriz, y luego
            //lo iremos modificando acorde al problema
            for (int i = 0; i <= n; i++) {
                for (int k = 0; k <= j; k++) {
                    Arrays.fill(DP[i][k], infinito);
                }
            }
            //Caso base
            //si n=j=m=0
            DP[0][0][0] = 0;

            //Caso recursivo
            for (int i = 1; i <= n; i++) {
                for (int k = 0; k <= Math.min(i, j); k++) {
                    for (int sUsados = 0; sUsados <= m; sUsados++) {
                        //aca tenemos 2 opciones
                        //opcion 1): si no escogemos al jugador i
                        DP[i][k][sUsados] = Math.min(DP[i][k][sUsados], DP[i-1][k][sUsados]);
                        //opcion 2): si escogemos al jugador i
                        if (k > 0) {
                            // costo de mover el jugador i a la posición k sin alterar el orden relativo del array
                            int costoIntercambio = (i - k); 
                            //si aún tenemos swaps disponibles, movemos al jugador y actualizamos el valor mínimo.
                            if (sUsados >=costoIntercambio) {
                                int intercAnteriores = sUsados -costoIntercambio;
                                if (DP[i-1][k-1][intercAnteriores] != infinito) {
                                    int jugEscogido = DP[i-1][k-1][intercAnteriores] + arr[i];
                                    DP[i][k][sUsados] = Math.min(DP[i][k][sUsados], jugEscogido);
                                }
                            }
                        }
                    }
                }
            }
            //tomamos la mejor solucion con n jugadores, de los cuales suman j, 
            //y usando a lo sumo m intercambios
            int respuesta = infinito;
            for (int sUsados = 0; sUsados <= m; sUsados++) {
                respuesta = Math.min(respuesta, DP[n][j][sUsados]);
            }
            //imprimimos la respuesta en pantalla, y listo!
            System.out.println(respuesta);
        }

        sc.close();
    }
}
