package me.vicenllopis.juegojava.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import me.vicenllopis.juegojava.objetos.Fondo;
import me.vicenllopis.juegojava.objetos.Personaje;
import me.vicenllopis.juegojava.objetos.Suelo;
import me.vicenllopis.juegojava.objetos.interfaces.AbrirMenu;
import me.vicenllopis.juegojava.servicios.ControlJuego;
import me.vicenllopis.juegojava.util.Resource;

public class JuegoPanel extends JPanel implements Runnable, KeyListener {
	public static final float GRAVITY = 1f;
	public static final float FLOOR_LEVEL = 800;// por pixel
	public static final int GAME_PRIMER_ESTADO = 0;
	public static final int GAME_JUGAR_ESTADO = 1;
	public static final int GAME_FINAL_ESTADO = 2;
	private Thread thread = new Thread(this);
	private Personaje personaje;
	private Suelo suelo;
	private Fondo fondo;
	private ControlJuego controller;
	private BufferedImage gameOver;
	private int estadoJuego;
	private int puntos;
	private AbrirMenu abrirMenu;
	private Clip musica;
	private boolean running = true;
	private Random random = new Random();

	public JuegoPanel(AbrirMenu abrirMenu) {
		personaje = new Personaje();
		personaje.setX(150);
		personaje.setY(150);
		suelo = new Suelo();
		fondo = new Fondo();
		controller = new ControlJuego(personaje, this);
		gameOver = Resource.getSourceImage("imagenes/GameOver.png");
		this.abrirMenu = abrirMenu;

		try {
			int randomNumber = random.nextInt(2);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(
							new File("sonidos/canciones/cancion" + randomNumber + ".wav").getAbsoluteFile());
			musica = AudioSystem.getClip();
			musica.open(audioInputStream);
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}

	}

	public void startGame() {
		thread.start();

	}

	// lo que se ejecuta del juego
	@Override
	public void run() {
		while (running) {
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
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("sonidos/point.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
		this.puntos += puntos;
	}

	// Actualiza cada objeto
	public void update() {
		switch (estadoJuego) {
			case GAME_JUGAR_ESTADO:
				fondo.update();
				personaje.update();
				suelo.update();
				controller.update();
				musica.start();
				if (!personaje.getVivo()) {
					estadoJuego = GAME_FINAL_ESTADO;
				}
				break;
			case GAME_FINAL_ESTADO:
				musica.stop();
				musica.close();
				try {
					int randomNumber = random.nextInt(3);
					AudioInputStream audioInputStream = AudioSystem
							.getAudioInputStream(
									new File("sonidos/canciones/cancion" + randomNumber + ".wav").getAbsoluteFile());
					musica.open(audioInputStream);
				} catch (Exception ex) {
					System.out.println("Error with playing sound.");
					ex.printStackTrace();
				}
				break;
			case GAME_PRIMER_ESTADO:
				break;
			default:
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
				controller.draw(g);
				g.drawString("Puntuacion: " + puntos, 900, 200);
				break;
			case GAME_FINAL_ESTADO:
				fondo.draw(g);
				suelo.draw(g);
				personaje.draw(g);
				controller.draw(g);
				g.drawImage(gameOver, 300, 200, null);
				g.drawString("Puntuacion Final: " + puntos, 400, 150);
				break;
			default:
				break;
		}

	}

	private void reiniciar() {
		estadoJuego = GAME_FINAL_ESTADO;
		personaje.setVivo(true);
		personaje.setX(150);
		personaje.setY(150);
		controller.restartEnemigos();
		puntos = 0;
		fondo = new Fondo();
		estadoJuego = GAME_JUGAR_ESTADO;
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
			case KeyEvent.VK_ESCAPE:
				abrirMenu.abrir();
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No se necesita
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// No se necesita

	}
}