/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import static Principal.Grafo.INF;

/**
 *
 * @author Raul
 */
public class Warshall {
    
    public static String floydWarshall(int graph[][], Grafo g, Nodo n) {
        double matrix[][] = new double[g.nodos.size()][g.nodos.size()];
        int i, j, k;
        int next[][] = new int[g.nodos.size()][g.nodos.size()];

        for (i = 0; i < g.nodos.size(); i++) {
            for (j = 0; j < g.nodos.size(); j++) {
                if (graph[i][j] != INF) {
                    matrix[i][j] = g.checkFloatProbability(g.nodos.get(i), g.nodos.get(j), graph[i][j]);
                    next[i][j] = j;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        // Adding vertices individually
        for (k = 0; k < g.nodos.size(); k++) {
            for (i = 0; i < g.nodos.size(); i++) {
                for (j = 0; j < g.nodos.size(); j++) {
                    if (g.matriz[i][j] != 0) {
                        if (matrix[i][k] * matrix[k][j] > matrix[i][j]) {
                            matrix[i][j] = matrix[i][k] * matrix[k][j];
                            next[i][j] = next[i][k];
                        }
                    }
                }
            }
        }
        printMatrix(matrix, g);
        printMatrix(next, g);
        Nodo max = null;
        for (Nodo infectado : g.listInfectados()) {
            if (infectado == g.listInfectados().get(0)) {
                max = infectado; continue;
            }
            if (matrix[infectado.getValor()][n.getValor()] > matrix[max.getValor()][n.getValor()]) {
                max = infectado;
            }
        }
        return Warshall.printPath(g, max, n, next);
//        System.out.println(Warshall.printPath(g, g.nodos.get(4), g.nodos.get(0), next));
    }
    
    public static String printPath(Grafo g, Nodo origen, Nodo destino, int next[][]){
        StringBuilder sb = new StringBuilder();
        Nodo n = origen;
        sb.append(n.getValor());
        while (n != destino) {
            n = g.nodos.get(next[n.getValor()][destino.getValor()]);
            sb.append(n.getValor());
        }
        
        return sb.toString();
    }
    
    public static void printMatrix(double matrix[][], Grafo g) {
        for (int i = 0; i < g.nodos.size(); ++i) {
            for (int j = 0; j < g.nodos.size(); ++j) {
                if (matrix[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(matrix[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }
    
    public static void printMatrix(int matrix[][], Grafo g) {
        for (int i = 0; i < g.nodos.size(); ++i) {
            for (int j = 0; j < g.nodos.size(); ++j) {
                if (matrix[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(matrix[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }
}
