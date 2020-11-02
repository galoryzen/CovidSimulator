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
    double translateX = 0, translateY = 0;
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
        boolean inArea = false;
            //Area panel is 1070, 510
            if (me.getX() < 40) {
                translateX += 2;
                inArea = true;
            } else if (me.getX() > 1030) {
                translateX -= 2;
                inArea = true;
            }

            if (me.getY() < 30) {
                translateY += 2;
                inArea = true;
            } else if (me.getY() > 480) {
                translateY -= 2;
                inArea = true;
            }
            System.out.println(translateX + "," + translateY);
            if (inArea) {
                graphics.clearRect(0, 0, 1070, 510);
                g.draw(graphics,scale, translateX, translateY);
            }
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
            g.draw(graphics, scale, translateX, translateY);
        }
    }
    
    /*
    public void mouseInArea(MouseEvent me){
        boolean inArea = false;
            //Area panel is 1070, 510
            if (me.getX() < 40) {
                translateX += 2;
                inArea = true;
            } else if (me.getX() > 1030) {
                translateX -= 2;
                inArea = true;
            }

            if (me.getY() < 30) {
                translateY += 2;
                inArea = true;
            } else if (me.getY() > 480) {
                translateY -= 2;
                inArea = true;
            }
            System.out.println(translateX + "," + translateY);
            if (inArea) {
                graphics.clearRect(0, 0, 1070, 510);
                g.draw(graphics,scale, translateX, translateY);
            }
    }
    */
    public void setG(Grafo g) {
        this.g = g;
    }

    public double getScale() {
        return scale;
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }
    
    public void initialize(){
        translateX=0;
        translateY=0;
        scale=1;
    }
    
}
