//AUTORES:
//WILLIAM POLLOCK, 202221321
//NICOLÁS HERNÁNDEZ, 202322148

import java.util.Scanner;
import java.util.Arrays;

public class ProblemaP1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); 

        for(int testCase = 0; testCase < T; testCase++) {
            int n = sc.nextInt();
            int j = sc.nextInt();
            int m = sc.nextInt();

            
            int[] arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = sc.nextInt();
            }

            // DP(i, r, s): mínima suma al escoger r elementos de los
            // primeros i jugadores, gastando exactamente s swaps.
            // Dimensiones: (n+1) x (j+1) x (m+1)
            int INF = Integer.MAX_VALUE / 2;
            int[][][] DP = new int[n+1][j+1][m+1];


            for (int i = 0; i <= n; i++) {
                for (int r = 0; r <= j; r++) {
                    Arrays.fill(DP[i][r], INF);
                }
            }

            // Caso base: no hemos tomado ningún elemento (r=0)
            // y no hemos gastado swaps (s=0)
            DP[0][0][0] = 0;

            // Llenar la DP
            for (int i = 1; i <= n; i++) {
                for (int r = 0; r <= Math.min(i, j); r++) {
                    for (int sUsed = 0; sUsed <= m; sUsed++) {
                        // 1) NO escoger al jugador i
                        DP[i][r][sUsed] = Math.min(DP[i][r][sUsed], DP[i-1][r][sUsed]);

                        // 2) Escoger al jugador i (si r>0)
                        if (r > 0) {
                            // Costo de mover el jugador i a la posición r sin alterar el orden relativo
                            int cost = (i - r); 
                            if (sUsed >= cost) {
                                int prevSwaps = sUsed - cost;
                                if (DP[i-1][r-1][prevSwaps] != INF) {
                                    int candidate = DP[i-1][r-1][prevSwaps] + arr[i];
                                    DP[i][r][sUsed] = Math.min(DP[i][r][sUsed], candidate);
                                }
                            }
                        }
                    }
                }
            }

            // Tomar la mejor respuesta (suma mínima) para r=j y 0 <= s <= m
            int ans = INF;
            for (int sUsed = 0; sUsed <= m; sUsed++) {
                ans = Math.min(ans, DP[n][j][sUsed]);
            }

            // Imprimir el resultado
            System.out.println(ans);
        }

        sc.close();
    }
}
