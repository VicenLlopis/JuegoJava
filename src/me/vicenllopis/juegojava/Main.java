package me.vicenllopis.juegojava;

import java.awt.event.MouseListener;

import javax.swing.event.MouseInputAdapter;

import me.vicenllopis.juegojava.juego.JuegoPanel;

public class Main {
    public static void main(String[] args) {
        MouseListener listener = new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

            }
        };

        JuegoPanel juego;

        Menu menu = new Menu();
        menu.setVisible(true);
        menu.addMouseListener(listener);

    }
}
