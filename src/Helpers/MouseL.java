/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Principal.Grafo;
import Principal.Nodo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author germa
 */
public class MouseL implements MouseListener, MouseMotionListener, MouseWheelListener {

    Grafo g;
    Graphics graphics;
    double scale = 1;
    int size = 0;
    int actualX, actualY;

    public MouseL(Grafo g, Graphics gr) {
        this.g = g;
        this.graphics = gr;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
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
        if (g != null) {
            for (Nodo nodo : g.getNodos()) {
                if (nodo.getR().contains(me.getX(), me.getY())) {
                    graphics.setColor(Color.yellow);
                    graphics.drawString(String.valueOf(me.getX()), me.getX(), me.getY());
                    graphics.drawString(String.valueOf(me.getY()), me.getX() + 30, me.getY());
                }
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {

        double wheelR = mwe.getPreciseWheelRotation();
        if (g != null) {
            if(wheelR==1){
                    scale -= 0.1;
                    if(scale<=0)
                        scale = 0.1;
            }else{
                scale += 0.1;
            }

            for (Nodo nodo : g.getNodos()) {
                nodo.setX((int) (nodo.getOriginalX() * scale));
                nodo.setY((int) (nodo.getOriginalY()* scale));
                size = (int) (Nodo.size * scale);
                nodo.setWidth(size);
                nodo.setHeight(size);
                nodo.setRect(nodo.getX(), nodo.getY(), size, size);
            }
            graphics.clearRect(0, 0, 1070, 510);
            g.draw(graphics);
        }
    }

    public void setG(Grafo g) {
        this.g = g;
    }

}
