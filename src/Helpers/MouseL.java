/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Estructuras.Lista;
import GUI.Frame;
import Principal.Grafo;
import Principal.Nodo;
import Principal.Warshall;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Se implementa el MouseListener, el cual permite moverse alrededor del grafo.
 * @author Raul,Sebastian,German
 */
public class MouseL implements MouseListener, MouseMotionListener, MouseWheelListener {

    Grafo g;
    Graphics graphics;
    public static double scale = 1;
    int size = 0;
    int actualX, actualY;
    public static Lista<Nodo> camino ;

    public MouseL(Grafo g, Graphics gr) {
        this.g = g;
        this.graphics = gr;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (g != null) {
            graphics.clearRect(0, 0, 1070, 510);
            try {
                g.draw(graphics, scale, KeyboardL.translateX, KeyboardL.translateY);
            } catch (IOException ex) {
                Logger.getLogger(MouseL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            for (Nodo nodo : g.getNodos()) {
               
                if (nodo.getR().contains(me.getX(), me.getY()) && !nodo.isInfectado()) {
                    camino = Warshall.floydWarshall(g.getMatriz(), g, nodo);
                    Frame.isInfec = 0;
                    try {
                        g.createPath(graphics, camino);
                    } catch (IOException ex) {
                        Logger.getLogger(MouseL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(nodo.getR().contains(me.getX(), me.getY()) && nodo.isInfectado()){
                    Frame.isInfec = 1;
                    g.noMorePaths();
                    try {
                        g.createPath(graphics, nodo);
                    } catch (IOException ex) {
                        Logger.getLogger(MouseL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            
            
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        /* 
        if (g != null) {
            graphics.clearRect(0, 0, 1070, 510);
            g.draw(graphics, scale, KeyboardL.translateX, KeyboardL.translateY);
            
            for (Nodo nodo : g.getNodos()) {
                if (nodo.getR().contains(me.getX(), me.getY())) {
                    
                    graphics.setColor(Color.yellow);
                    graphics.drawString(String.valueOf(me.getX()), me.getX(), me.getY());
                    graphics.drawString(String.valueOf(me.getY()), me.getX() + 30, me.getY());
                    
                    graphics.setColor(Color.yellow);
                    graphics.fillRect(me.getX(), me.getY()-170, 140, 170);
                }
            }
            
        }
        */
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {

        double wheelR = mwe.getPreciseWheelRotation();
        if (g != null) {
            if (wheelR == 1) {
                scale -= 0.1;
                if (scale <= 0) {
                    scale = 0.1;
                }
            } else {
                scale += 0.1;
            }
            graphics.clearRect(0, 0, 1070, 510);
            try {
                g.draw(graphics, scale, KeyboardL.translateX, KeyboardL.translateY);
            } catch (IOException ex) {
                Logger.getLogger(MouseL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setG(Grafo g) {
        this.g = g;
    }

    public double getScale() {
        return scale;
    }

    public void initialize() {
        scale = 1;
    }

}
