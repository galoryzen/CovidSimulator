package Principal;

import GUI.Frame;
import Estructuras.Pila;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raul
 */
public class Grafo {

    //Lista enlazada usada para almacenar todos los nodos
    LinkedList<Nodo> nodos;
    //Lista enlazada usada, para almacenar
    LinkedList<LinkedList<Nodo>> lista;
    //Vector con los nodos ya recorridos
    boolean visited[];
    static Random r = new Random();
    //Matriz de adyacencia
    int matriz[][];

    public Grafo(int nodes) {
        this.nodos = new LinkedList();
        this.lista = new LinkedList();
        //Crea los nodos
        createNodes(nodes);
        this.visited = new boolean[nodes];
        this.matriz = new int[nodes][nodes];
    }

    public void createNodes(int n) {
        Nodo.size = 850/n;
        if(Nodo.size<30){
            Nodo.size = 30;
        }
        Random r1 = new Random();
        for (int i = 0; i < n; i++) {
            if (nodos.isEmpty()) {
                int x = r1.nextInt(858-Nodo.size);
                int y = r1.nextInt(630-Nodo.size);
                nodos.add(new Nodo(i, x, y ));
            } else {
                
                int x = r1.nextInt(858-Nodo.size);
                int y = r1.nextInt(630-Nodo.size);
                while (intercede(x, y)) {
                    x = r1.nextInt(858-Nodo.size);
                    y = r1.nextInt(630-Nodo.size);
                }
                nodos.add(new Nodo(i, x, y));
            }
        }
    }

    private boolean intercede(int x, int y) {

        Rectangle newN, actual;
        newN = new Rectangle();
        //Hace un Rectangulo que cubre tanto arriba como debajo del dibujo
        newN.height =(int) (Nodo.size*0.25)+Nodo.size;
        newN.width =(int) (Nodo.size*0.25)+Nodo.size;
        newN.x = x - Nodo.size/4;
        newN.y = y - Nodo.size/4;

        for (Nodo nodo : nodos) {
            actual = new Rectangle();
            actual.x = nodo.getX() - Nodo.size/4;
            actual.y = nodo.getY() - Nodo.size/4;
            actual.width =(int) (Nodo.size*0.25)+Nodo.size;
            actual.height =(int) (Nodo.size*0.25)+Nodo.size;
            if (newN.intersects(actual) || actual.intersects(newN)) {
                return true;
            }
        }

        return false;
    }

    public static Grafo crearGrafo(int nodes, float p) {
        Grafo g = new Grafo(nodes);
        int w = -1;
        double lp = Math.log(1.0 - p);
        Random r = new Random();
        int v = 0;
        while (v < nodes) {
            double lr = Math.log(1 - r.nextFloat());
            w = (int) (w + 1 + (lr / lp));
            if (v == w) {
                w++;
            }
            while (w >= nodes && v < nodes) {
                w = w - nodes;
                v = v + 1;
                if (v == w) {
                    w++;
                }
            }
            if (v < nodes) {
                g.addArista(v, w);
                //Ingresa la nueva arista en la matriz de adyacencia
                g.matriz[v][w] = r.nextInt(4) + 1;
            }
        }
        return g;
    }

    //Crea una conexión entre los nodos
    public void crearArista(Nodo origen, Nodo destino) {
        origen.conexiones.add(destino);
    }

    //Crea una conexión entre los nodos, usando su valor
    public void addArista(int origen, int destino) {
        crearArista(this.encontrarNodo(origen), this.encontrarNodo(destino));
    }

    //Refleja los valores en la matriz de adyacencia
    public void checkMatriz() {
        for (int i = 0; i < nodos.size(); i++) {
            for (int j = 0; j < nodos.size(); j++) {
                if (this.matriz[i][j] != 0 && this.matriz[j][i] != 0) {
                    this.matriz[j][i] = this.matriz[i][j];
                }
            }
        }
    }

    //Muestra el grafo con sus conexiones
    public void displayGrafo() {
        for (int i = 0; i < nodos.size(); i++) {
            System.out.println("El nodo con valor " + nodos.get(i).valor + " está conectado con los nodos con valor:");
            for (int j = 0; j < nodos.get(i).conexiones.size(); j++) {
                System.out.print(nodos.get(i).conexiones.get(j).valor + " ");
            }
            System.out.println("");
        }
    }

    //Encuentra a un Nodo con respecto a su valor
    public Nodo encontrarNodo(int i) {
        for (int j = 0; j < nodos.size(); j++) {
            if (nodos.get(i).valor == i) {
                return nodos.get(i);
            }
        }
        return null;
    }

    //Verifica si el grafo es fuertemente conexo, es decir, todo nodo tiene un camino hacía todos los nodos
    public boolean isStronglyConnected() {
        for (int i = 0; i < this.nodos.size(); i++) {
            dfs(i);

            for (int j = 0; j < this.nodos.size(); j++) {
                if (!visited[j]) {
                    return false;
                }
            }

            //Rellena el vector con falsos
            Arrays.fill(visited, false);
        }
        return true;
    }

    //Implementa el algoritmo dfs
    public void dfs(int inicio) {
        Pila<Integer> pila = new Pila<>();
        pila.push(inicio);
        visited[inicio] = true;

        while (!pila.isEmpty()) {
            int valor = pila.pop();

            Nodo n = this.nodos.get(valor);

            for (Nodo nodo : n.getConexiones()) {
                if (!visited[nodo.valor]) {
                    pila.push(nodo.valor);
                    visited[nodo.valor] = true;
                }
            }
        }
    }

    //Encuentra cuales nodos están infectados
    public boolean findInfectados() {
        for (Nodo nodo : nodos) {
            if (!nodo.infectado) {
                return false;
            }
        }
        return true;
    }

    //Crea una lista con todos los nodos ya infectados
    public LinkedList<Nodo> listInfectados() {
        LinkedList l = new LinkedList();
        for (Nodo nodo : nodos) {
            if (nodo.infectado) {
                l.add(nodo);
            }
        }
        return l;
    }

    //Empieza la simulación y genera las iteraciones
    public static void generarIteraciones(Grafo g, Graphics gr) {

        int i = 0;
        while (!g.findInfectados()) {
            g.lista.add(new LinkedList());
            switch (i) {
                case 0:
                    i++;
                    continue;
                case 1:
                    int a = r.nextInt(g.nodos.size());
                    Nodo n = g.nodos.get(a);
                    n.infectado = true;
                    n.draw(gr);
                    g.lista.get(i).add(n);
                    break;
                default:
                    for (Nodo infectado : g.listInfectados()) {
                        for (Nodo nodo : infectado.getConexiones()) {
                            if (!nodo.infectado) {
                                nodo.infectado = g.checkProbability(infectado, nodo, g.matriz[infectado.valor][nodo.valor]);
                                if (nodo.infectado) {
                                    g.lista.get(i).add(nodo);
                                }
                            }
                        }
                    }
                    break;
            }
            i++;
        }
    }

    //Muestra las iteraciones
    public void displayIteraciones() {
        int i = 0;
        for (LinkedList<Nodo> list : this.lista) {
            if (!list.isEmpty()) {
                System.out.print("Iteracion " + i + " [");
                for (Nodo nodo : list) {
                    if (list.getLast() == nodo) {
                        System.out.print(nodo.valor);
                    } else {
                        System.out.print(nodo.valor + ", ");
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

    //Retorna la probabilidad de infección de cada nodo con respecto a otro
    public boolean checkProbability(Nodo origen, Nodo destino, int distancia) {
        if (origen.mascarilla) {
            if (destino.mascarilla) {
                if (distancia > 2) {
                    return checkProbability(20);
                }
                return checkProbability(30);
            } else {
                if (distancia > 2) {
                    return checkProbability(30);
                }
                return checkProbability(40);
            }
        } else {
            if (destino.mascarilla) {
                if (distancia > 2) {
                    return checkProbability(40);
                }
                return checkProbability(60);
            } else {
                if (distancia > 2) {
                    return checkProbability(80);
                }
                return checkProbability(90);
            }
        }
    }

    public boolean checkProbability(int i) {
        return r.nextInt(100) < i;
    }

    public void displayMatriz() {
        for (int i = 0; i < nodos.size(); i++) {
            System.out.print("[ ");
            for (int j = 0; j < nodos.size(); j++) {
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.print("]");
            System.out.println("");
        }
    }

    /*
    public static void main(String[] args) throws InterruptedException {
        Grafo g = crearGrafo(10, 0.09f);
        //Mientras el grafo que se crea no sea fuertemente conexo, no dejará de generarlos
        while (!g.isStronglyConnected()) {
            g = crearGrafo(300, 0.09f);
        }
        generarIteraciones(g);
        g.checkMatriz();
        g.displayIteraciones();
        g.displayGrafo();
        g.displayMatriz();
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.drawGrafo(g);
    }
     */
    public LinkedList<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(LinkedList<Nodo> nodos) {
        this.nodos = nodos;
    }

    public LinkedList<LinkedList<Nodo>> getLista() {
        return lista;
    }

    public void setLista(LinkedList<LinkedList<Nodo>> lista) {
        this.lista = lista;
    }

    public boolean[] getVisited() {
        return visited;
    }

    public void setVisited(boolean[] visited) {
        this.visited = visited;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    
    public void draw(Graphics graphics) {
        for (Nodo nodo : nodos) {
            nodo.draw(graphics);
        }
    }

}
