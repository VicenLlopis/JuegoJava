package Juego;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import me.vicenllopis.juegojava.objetos.Planta;
import me.vicenllopis.juegojava.objetos.interfaces.Enemigo;
import me.vicenllopis.juegojava.util.Resource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ControlEnemigo {

	private List<Enemigo> enemigos;
	private Random random;
	private BufferedImage imgPlanta1;
	private BufferedImage imgPlanta2;
	private Personaje personaje;
	private Juego juego;

	public ControlEnemigo(Personaje personajeMain, Juego juego) {
		this.juego = juego;
		this.personaje = personajeMain;
		enemigos = new ArrayList<>();
		imgPlanta1 = Resource.getSourceImage("imagenes/2.png");
		imgPlanta2 = Resource.getSourceImage("imagenes/4.png");
		random = new Random();
		enemigos.add(getRandomPlanta());

	}

	public void restartEnemigos() {
		enemigos.clear();
		enemigos.add(getRandomPlanta());
	}

	// A�ade un enemigo cuando uno pase por la mitad del mapa
	public void update() {
		for (Enemigo e : enemigos) {
			e.update();
			if (estaEncima(e, personaje) && !e.sumaPunto()) {
				juego.sumarPuntos(10);
				e.setSumaPunto(true);
			}
			if (e.getBounds().intersects(personaje.getBounds())) {
				personaje.setVivo(false);
			}
		}
		Enemigo primerEnemigo = enemigos.get(0);
		if (primerEnemigo.estaMitad()) {
			enemigos.remove(primerEnemigo);
			enemigos.add(getRandomPlanta());

		}

	}

	private boolean estaEncima(Enemigo e, Personaje p) {
		// Check if the player passed the enemy by the top.
		if (p.getY() < e.getBounds().y + e.getBounds().height && p.getY() > e.getBounds().y) {
			// Check if the player passed the enemy by the left.
			if (p.getX() < e.getBounds().x + e.getBounds().width && p.getX() > e.getBounds().x) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	// Para seleccionar una de las dos plantas
	private Planta getRandomPlanta() {
		Planta planta = new Planta(personaje);
		planta.setPosX(1600);
		if (random.nextBoolean()) {

			planta.setImgPlanta(imgPlanta1);
		} else {
			planta.setImgPlanta(imgPlanta2);

		}
		return planta;

	}

	public void draw(Graphics g) {
		for (Enemigo e : enemigos) {
			e.draw(g);
		}
	}

}
