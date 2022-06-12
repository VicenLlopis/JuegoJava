package me.vicenllopis.juegojava.juego;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import me.vicenllopis.juegojava.objetos.interfaces.AbrirMenu;

public class JuegoFrame extends JFrame {

	private JuegoPanel juego;

	public JuegoFrame(AbrirMenu abrirMenu) {
		super("Juego Robot");
		setSize(1100, 905);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		juego = new JuegoPanel(abrirMenu);
		add(juego);
		addKeyListener(juego);
		setVisible(false);
	}

	public void startGame() {
		setVisible(true);
		juego.startGame();
	}
}
