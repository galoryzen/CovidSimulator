/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Estructuras.Lista;
import static Principal.Grafo.INF;

/**
 * En esta clase se realiza el método del camino más rápido de contagio.
 * @author Raul,Sebastian,Germán
 */
public class Warshall {
    
     public static Lista<Nodo> floydWarshall(int graph[][], Grafo g, Nodo n) {
        double matrix[][] = new double[g.nodos.size()][g.nodos.size()];
        int i, j, k;
        int next[][] = new int[g.nodos.size()][g.nodos.size()];
        graph = readyWarshall(graph);
        for (i = 0; i < g.nodos.size(); i++) {
            for (j = 0; j < g.nodos.size(); j++) {
                if (graph[i][j] != INF) {
                    if(i!=j)    matrix[i][j] = g.checkFloatProbability(g.nodos.get(i), g.nodos.get(j), graph[i][j]);
                    next[i][j] = j;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        
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
        if(max == null) return null;
        return Warshall.printPath(g, max, n, next);
    }
    
    public static Lista<Nodo> printPath(Grafo g, Nodo origen, Nodo destino, int next[][]){
        Lista<Nodo> path = new Lista();
        Nodo n = origen;
        System.out.print(n.getValor()+" ");
        path.add(n);
        while (n != destino) {
            n = g.nodos.get(next[n.getValor()][destino.getValor()]);
            System.out.print(n.getValor()+" ");
            path.add(n);
        }
        return path;
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

    private static int[][] readyWarshall(int matrix[][]) {
        for (int i = 0; i<matrix.length; i++) {
            for (int j = 0; j<matrix.length; j++) {
                if (i!=j) {
                    if (matrix[i][j]==0) {
                        matrix[i][j] = INF;
                    }
                }
            }
        }
        return matrix;
    }
}
