/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Estructuras.Lista;
import Principal.Grafo;

/**
 * Se utilizo esta clase para probar los métodos realizados en este proyecto.
 * @author Raul,Sebastian,German
 */
public class Displayer {
    
    public static void displayGrafo(Grafo g) {
        for (int i = 0; i < g.nodos.size(); i++) {
            System.out.println("El nodo con valor " + g.nodos.get(i).getValor() + " está conectado con los nodos con valor:");
            for (int j = 0; j < g.nodos.get(i).conexiones.size(); j++) {
                System.out.print(g.nodos.get(i).conexiones.get(j).getValor() + " ");
            }
            System.out.println("");
        }
    }
    
    public static void displayMascarillas(Grafo g) {
        System.out.print("Los nodos con mascarilla son : ");
        for (Nodo nodo : g.nodos) {
            System.out.println("Nodo " + nodo.getValor() + " " + nodo.isMascarilla());
        }
    }
    
    public static void displayMatriz(Grafo g) {
        for (int i = 0; i < g.nodos.size(); i++) {
            System.out.print("[ ");
            for (int j = 0; j < g.nodos.size(); j++) {
                System.out.print(g.matriz[i][j] + " ");
            }
            System.out.print("]");
            System.out.println("");
        }
    }
    
    public static void displayIteraciones(Grafo g) {
        int i = 0;
        for (Lista<Nodo> list : g.iteraciones) {
            if (!list.isEmpty()) {
                System.out.print("Iteracion " + i + " [");
                for (Nodo nodo : list) {
                    if (list.getLast() == nodo) {
                        System.out.print(nodo.getValor());
                    } else {
                        System.out.print(nodo.getValor() + ", ");
                    }
                }
                System.out.print("]");
                System.out.println("");
            } else {
                System.out.println("Iteracion " + i + " [ ]");
            }
            i++;
        }
    }
}
