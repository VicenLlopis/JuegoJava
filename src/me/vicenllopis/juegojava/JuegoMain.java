package me.vicenllopis.juegojava;

import Juego.JuegoFrame;

public class JuegoMain {
	static JuegoFrame juegoFrame;

	public static void main(String[] args) {
		juegoFrame = new JuegoFrame();
		juegoFrame.startGame();
		juegoFrame.setVisible(true);
	}

}
