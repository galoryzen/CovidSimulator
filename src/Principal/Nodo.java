package Principal;


import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raul
 */
public class Nodo {
    static int size = 30;
    int valor;
    LinkedList<Nodo> conexiones;
    int x;
    int y;
    boolean infectado = false;
    boolean mascarilla;
    
    public Nodo(int valor, int x, int y, boolean mascarilla){
        this.valor = valor;
        this.x = x;
        this.y = y;
        conexiones = new LinkedList();
        this.mascarilla = mascarilla;
    }
    
    public Nodo(int valor){
        this.valor = valor;
        conexiones = new LinkedList();
    }

    
    public LinkedList<Nodo> getConexiones() {
        return conexiones;
    }

    public int getValor() {
        return valor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInfectado() {
        return infectado;
    }
    
    public void setInfeccion(boolean n){
        infectado = n;
    }

    public boolean isMascarilla() {
        return mascarilla;
    }

    public void setInfectado(boolean infectado) {
        this.infectado = infectado;
    }

    public void draw(Graphics g) {
        if(infectado)
            g.setColor(Color.red);
        else
            g.setColor(Color.white);
        g.fillOval(this.x, this.y, size, size);
        drawConexiones(g);
    }

    private void drawConexiones(Graphics g) {
        for (Nodo conexion : conexiones) {
            if(infectado)
                g.setColor(Color.red);
            else
                g.setColor(Color.white);
            g.drawLine(x+size/2, y+size/2, conexion.getX()+size/2, conexion.getY()+size/2);
            
        }
    }
    
    
}
