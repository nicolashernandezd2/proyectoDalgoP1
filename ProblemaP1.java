//AUTORES:
//WILLIAM POLLOCK, 202221321
//NICOLÁS HERNÁNDEZ, 202322148

import java.util.Scanner;
import java.util.Arrays;

public class ProblemaP1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt(); 

        for(int casosPrueba = 0; casosPrueba < W; casosPrueba++) {
            int n = sc.nextInt();
            int j = sc.nextInt();
            int m = sc.nextInt();

            int[] arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = sc.nextInt();
            }

            
            int infinito = Integer.MAX_VALUE; 
            int[][][] DP = new int[ n+1][j+ 1][m+1];
            DP[0][0][0] = 0;



            
            for (int i = 0; i <= n; i++) {
                for (int k = 0; k <= j; k++) {
                    Arrays.fill(DP[i][k], infinito);
                }
            }


            for (int i = 1; i <= n; i++) {
                for (int k = 0; k <= Math.min(i, j); k++) {
                    for (int sUsados = 0; sUsados <= m; sUsados++) {
                        DP[i][k][sUsados] = Math.min(DP[i][k][sUsados], DP[i-1][k][sUsados]);
                        if (k > 0) {
                            int costoIntercambio = (i - k); 
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

            int respuesta = infinito;
            for (int sUsados = 0; sUsados <= m; sUsados++) {
                respuesta = Math.min(respuesta, DP[n][j][sUsados]);
            }

            System.out.println(respuesta);
        }

        sc.close();
    }
}
