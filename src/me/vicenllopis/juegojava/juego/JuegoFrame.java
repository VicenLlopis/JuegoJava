package me.vicenllopis.juegojava.juego;

import javax.swing.JFrame;

public class JuegoFrame extends JFrame {

	private JuegoPanel juego;

	public JuegoFrame() {
		super("Juego Robot");
		setSize(1100, 905);
		setResizable(true);
		setDefaultCloseOperation(JuegoFrame.EXIT_ON_CLOSE);
		juego = new JuegoPanel();
		add(juego);
		addKeyListener(juego);
		setVisible(true);
		juego.startGame();
	}
}
