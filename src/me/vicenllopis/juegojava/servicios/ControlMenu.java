package me.vicenllopis.juegojava.servicios;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.vicenllopis.juegojava.MenuFrame;
import me.vicenllopis.juegojava.juego.JuegoFrame;
import me.vicenllopis.juegojava.objetos.interfaces.AbrirMenu;



/**
 * MouseController
 */
public class ControlMenu extends MouseAdapter implements AbrirMenu {

    private MenuFrame menu;
    private JuegoFrame juego;

    public ControlMenu(MenuFrame menu) {
        this.menu = menu;
    }

    //Funcion implementada de la interface AbrirMenu
    // * Esta funcion se ejecuta cuando se hace click en el boton de escape
    // * Se abre el menu y se cierra el juego
    @Override
    public void abrir() {
        menu.setVisible(true);
        juego.dispose();
        juego = null;
    }
    
    //Cuando le das click al boton de jugar se ejecuta el juego
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        menu.setVisible(false);
        juego = new JuegoFrame(this);
        juego.startGame();
    }
}