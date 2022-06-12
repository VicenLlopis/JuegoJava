package Juego;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import me.vicenllopis.juegojava.objetos.Planta;
import me.vicenllopis.juegojava.util.Resource;

public class Juego extends JPanel implements Runnable, KeyListener {
	public static final float GRAVITY = 1f;
	public static final float GROUNDY = 800;// por pixel
	public static final int GAME_PRIMER_ESTADO = 0;
	public static final int GAME_JUGAR_ESTADO = 1;
	public static final int GAME_FINAL_ESTADO = 2;
	private static Thread thread;
	private Personaje personaje;
	private Suelo suelo;
	private Fondo fondo;
	private Planta planta;
	private ControlEnemigo controlEnemigos;
	private BufferedImage gameOver;
	private int estadoJuego;
	private int puntos;

	private AudioClip sumarPuntosAudio;

	public Juego() {
		thread = new Thread(this);
		personaje = new Personaje();
		personaje.setX(150);
		personaje.setY(150);
		suelo = new Suelo();
		fondo = new Fondo(this);
		controlEnemigos = new ControlEnemigo(personaje, this);
		gameOver = Resource.getSourceImage("imagenes/GameOver.png");

	}

	public static void startGame() {
		thread.start();

	}

	// lo que se ejecuta del juego
	@Override
	public void run() {
		while (true) {

			try {
				update();
				repaint();
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void sumarPuntos(int puntos) {
		this.puntos += puntos;
	}

	// Actualiza cada objeto
	public void update() {
		switch (estadoJuego) {
			case 1:
				fondo.update();
				personaje.update();
				suelo.update();
				controlEnemigos.update();
				if (!personaje.getVivo()) {
					estadoJuego = GAME_FINAL_ESTADO;
				}

				break;

		}

	}

	@Override
	public void paint(Graphics g) {
		// color del fondo
		g.setColor(Color.white);
		// cogen el widht y el height del frame
		g.fillRect(0, 0, getWidth(), getHeight());
		// color del objeto
		g.setColor(Color.red);
		switch (estadoJuego) {
			// Para imprimir en plantalla segun en que estado este el juego
			case GAME_PRIMER_ESTADO:
				fondo.draw(g);
				personaje.draw(g);
				suelo.draw(g);
				break;
			case GAME_JUGAR_ESTADO:
				fondo.draw(g);
				suelo.draw(g);
				personaje.draw(g);
				controlEnemigos.draw(g);
				g.drawString("Puntuacion: " + String.valueOf(puntos), 900, 200);
				break;
			case GAME_FINAL_ESTADO:
				fondo.draw(g);
				suelo.draw(g);
				personaje.draw(g);
				controlEnemigos.draw(g);
				g.drawImage(gameOver, 300, 200, null);
				g.drawString("Puntuacion Final: " + String.valueOf(puntos), 400, 150);

				break;
		}

	}

	private void reiniciar() {
		personaje.setVivo(true);
		personaje.setX(150);
		personaje.setY(150);
		controlEnemigos.restartEnemigos();
		estadoJuego = GAME_JUGAR_ESTADO;
		puntos = 0;
	}

	@Override
	public void keyPressed(KeyEvent ev) {
		switch (ev.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				if (estadoJuego == GAME_PRIMER_ESTADO)
					estadoJuego = GAME_JUGAR_ESTADO;
				else if (estadoJuego == GAME_JUGAR_ESTADO)
					personaje.jump();
				else if (estadoJuego == GAME_FINAL_ESTADO)
					reiniciar();
				break;
			case KeyEvent.VK_UP:
				if (estadoJuego == GAME_PRIMER_ESTADO)
					estadoJuego = GAME_JUGAR_ESTADO;
				else if (estadoJuego == GAME_JUGAR_ESTADO)
					personaje.jump();
				else if (estadoJuego == GAME_FINAL_ESTADO)
					reiniciar();
				break;
			case KeyEvent.VK_R:
				reiniciar();
				break;
		}
	}

	@Override
	// Para que el juego no empiece hasta que no le des al espacio
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}