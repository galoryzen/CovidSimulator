/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Principal.Grafo;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;

/**
 * Se implementa el KeyboardListener, el cual permite moverse alrededor del grafo.
 * @author Raul,Sebastian,German
 */
public class KeyboardL implements KeyListener {

    boolean teclas[];
    public static int translateX, translateY;
    Grafo g;
    Graphics graphics;

    public KeyboardL(Grafo g, Graphics gr) {
        teclas = new boolean[256];
        translateX = 0;
        translateY = 0;
        this.graphics = gr;
        this.g = g;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("hi");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        teclas[ke.getKeyCode()] = true;
        int move = 3;
        if (g != null) {
            if (teclas[KeyEvent.VK_RIGHT]) {
                translateX -=move;
                graphics.clearRect(0, 0, 1070, 510);
                try {
                    g.draw(graphics, MouseL.scale, translateX, translateY);
                } catch (IOException ex) {
                    Logger.getLogger(KeyboardL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (teclas[KeyEvent.VK_LEFT]) {
                translateX += move;
                graphics.clearRect(0, 0, 1070, 510);
                try {
                    g.draw(graphics, MouseL.scale, translateX, translateY);
                } catch (IOException ex) {
                    Logger.getLogger(KeyboardL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (teclas[KeyEvent.VK_UP]) {
                translateY += move;
                graphics.clearRect(0, 0, 1070, 510);
                try {
                    g.draw(graphics, MouseL.scale, translateX, translateY);
                } catch (IOException ex) {
                    Logger.getLogger(KeyboardL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (teclas[KeyEvent.VK_DOWN]) {
                translateY -= move;
                graphics.clearRect(0, 0, 1070, 510);
                try {
                    g.draw(graphics, MouseL.scale, translateX, translateY);
                } catch (IOException ex) {
                    Logger.getLogger(KeyboardL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        teclas[ke.getKeyCode()] = false;
    }

    public void setG(Grafo g) {
        this.g = g;
    }

    public void initialize() {
        translateX = 0;
        translateY = 0;
    }

}
