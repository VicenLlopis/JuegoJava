package me.vicenllopis.juegojava.servicios;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.vicenllopis.juegojava.Menu;
import me.vicenllopis.juegojava.juego.JuegoFrame;
import me.vicenllopis.juegojava.objetos.interfaces.AbrirMenu;

/**
 * MouseController
 */
public class ControlMenu extends MouseAdapter implements AbrirMenu {

    private Menu menu;
    private JuegoFrame juego;

    public ControlMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void abrir() {
        menu.setVisible(true);
        juego.dispose();
        juego = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        menu.setVisible(false);
        juego = new JuegoFrame(this);
        juego.startGame();
    }
}