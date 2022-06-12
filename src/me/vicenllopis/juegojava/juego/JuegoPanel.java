package me.vicenllopis.juegojava.juego;

import java.awt.Color;
import java.awt.Font;
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

	// Variables declaradas
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

	// Constructor de la clase JuegoPanel y recibe un callback para abrir el menu
	public JuegoPanel(AbrirMenu abrirMenu) {
		personaje = new Personaje();
		personaje.setX(150);
		personaje.setY(150);
		suelo = new Suelo();
		fondo = new Fondo();
		controller = new ControlJuego(personaje, this);
		gameOver = Resource.getSourceImage("imagenes/GameOver.png");
		this.abrirMenu = abrirMenu;

		// Carga la musica del juego de forma random
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

	// Inicia el hilo del juego
	public void startGame() {
		thread.start();
	}

	// Metodo que se ejecuta cuando inicias el hilo
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

	//Metodo que suma los puntos y carga el sonido de cuando consigues puntos
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

	// Actualiza cada objeto del juego
	public void update() {
		switch (estadoJuego) {

			// Si el estado del juego es jugar se actualizan los objetos y inica la musica
			case GAME_JUGAR_ESTADO:
				fondo.update();
				personaje.update();
				suelo.update();
				controller.update();
				musica.start();

				//Cuando muere el personaje se carga el sonido de muerte y se cambia el estado del juego a estado final
				if (!personaje.getVivo()) {
					try {
						AudioInputStream audioInputStream = AudioSystem
								.getAudioInputStream(
										new File("sonidos/death.wav").getAbsoluteFile());
						Clip clip = AudioSystem.getClip();
						clip.open(audioInputStream);
						clip.start();
					} catch (Exception ex) {
						System.out.println("Error with playing sound.");
						ex.printStackTrace();
					}
					estadoJuego = GAME_FINAL_ESTADO;
				}
				break;

			//Estado final cuando el personaje muere y para que vuelva a cargar otra cancion de fondo
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

	// Metodo dibuja en el panel los graficos del juego
	@Override
	public void paint(Graphics g) {

		// Para imprimir en plantalla segun en que estado este el juego
		switch (estadoJuego) {
			
			// Si esta en el primer estado se dibuja el fondo, el personaje y el suelo
			case GAME_PRIMER_ESTADO:
				fondo.draw(g);
				personaje.draw(g);
				suelo.draw(g);
				break;
			//Si esta estado jugar dibuja las siguientes cosas
			case GAME_JUGAR_ESTADO:
				fondo.draw(g);
				suelo.draw(g);
				personaje.draw(g);
				controller.draw(g);
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.setColor(Color.white);
				g.drawString("Puntuacion: " + puntos, 850, 50);
				break;

			//Si esta en el estado final se dibuja el game over y la puntuacion final
			case GAME_FINAL_ESTADO:
				fondo.draw(g);
				g.setColor(Color.white);
				g.fillOval(250, 80, gameOver.getWidth() + 270, gameOver.getHeight() + 170);
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.setColor(Color.black);
				g.drawString("Puntuacion Final: " + puntos, 400, 180);
				g.drawImage(gameOver, 380, 200, null);
				break;
			default:
				break;
		}

	}
	//Metodo para reinicar el juego
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

	//Metodo que se encarga de gestionar las pulsaciones del teclado
	@Override
	public void keyPressed(KeyEvent ev) {
		switch (ev.getKeyCode()) {

			//Si se pulsa la tecla
			case KeyEvent.VK_SPACE:
				if (estadoJuego == GAME_PRIMER_ESTADO)
					estadoJuego = GAME_JUGAR_ESTADO;
				else if (estadoJuego == GAME_JUGAR_ESTADO)
					personaje.jump();
				else if (estadoJuego == GAME_FINAL_ESTADO)
					reiniciar();
				break;

				//Si se pulsa la flecha de arriba
			case KeyEvent.VK_UP:
				if (estadoJuego == GAME_PRIMER_ESTADO)
					estadoJuego = GAME_JUGAR_ESTADO;
				else if (estadoJuego == GAME_JUGAR_ESTADO)
					personaje.jump();
				else if (estadoJuego == GAME_FINAL_ESTADO)
					reiniciar();
				break;

				//Si se pulsa la R
			case KeyEvent.VK_R:
				reiniciar();
				break;

				//Si de pulsa el escape
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