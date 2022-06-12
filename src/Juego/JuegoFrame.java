package Juego;

import javax.swing.JFrame;

public class JuegoFrame extends JFrame{
    
	private Juego juego;

	public JuegoFrame() {
		super("Juego Robot");
		setSize(1100, 905);
        setResizable(false);
		setDefaultCloseOperation(JuegoFrame.EXIT_ON_CLOSE);
		juego = new Juego();
		add(juego);
		addKeyListener(juego);
	}

	public void startGame() {
		juego.startGame();

	}
}
