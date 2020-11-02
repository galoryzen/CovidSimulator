package Principal;

import Estructuras.Lista;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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

    public static int size = 30;
    int valor;
    Lista<Nodo> conexiones;
    int x;
    int OriginalX;
    int y;
    int OriginalY;
    int width;
    int height;
    boolean infectado = false;
    boolean mascarilla;
    Rectangle rect;

    public Nodo(int valor, int x, int y, boolean mascarilla) {
        this.valor = valor;
        this.x = x;
        this.y = y;
        this.OriginalX = x;
        this.OriginalY = y;
        width = size;
        height = size;
        conexiones = new Lista();
        this.mascarilla = mascarilla;
        
        //Crea la hitbox
        rect = new Rectangle();
        rect.x = x;
        rect.y = y;
        rect.width = size;
        rect.height = size;
    }

    public Nodo(int valor) {
        this.valor = valor;
        conexiones = new Lista();
    }

    public Lista<Nodo> getConexiones() {
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

    public void setInfeccion(boolean n) {
        infectado = n;
    }

    public boolean isMascarilla() {
        return mascarilla;
    }

    public void setMascarilla(boolean n) {
        this.mascarilla = n;
    }

    public void setInfectado(boolean infectado) {
        this.infectado = infectado;
    }

    public void draw(Graphics g, double sc, double tx, double ty) {
        if (infectado) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        g.fillOval(this.x, this.y, width, height);
        //g.setColor(Color.yellow);
        //g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
    public void drawConexiones(Graphics g) {
        for (Nodo conexion : conexiones) {
            if (infectado) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.drawLine(x + width / 2, y + height / 2, conexion.getX() + conexion.getWidth() / 2, conexion.getY() + conexion.getHeight() / 2);

        }
    }

    public Rectangle getR() {
        return rect;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOriginalX() {
        return OriginalX;
    }

    public int getOriginalY() {
        return OriginalY;
    }
    
    public void setRect(int x, int y, int width, int height) {
        Rectangle rect2 = new Rectangle();
        rect2.x = x;
        rect2.y = y;
        rect2.width = width;
        rect2.height = height;
        this.rect = rect2;
    }

    
    
}
