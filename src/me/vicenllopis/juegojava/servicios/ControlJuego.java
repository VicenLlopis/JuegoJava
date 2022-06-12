package me.vicenllopis.juegojava.servicios;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import me.vicenllopis.juegojava.juego.JuegoPanel;
import me.vicenllopis.juegojava.objetos.Personaje;
import me.vicenllopis.juegojava.objetos.Planta;
import me.vicenllopis.juegojava.objetos.interfaces.Enemigo;
import me.vicenllopis.juegojava.util.Resource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;


//Clase que controlo los enemigos, la puntuacion y otras funciones del juego
public class ControlJuego {

	//Variables 
	private List<Enemigo> enemigos;
	private Random random;
	private BufferedImage imgPlanta1;
	private BufferedImage imgPlanta2;
	private Personaje personaje;
	private JuegoPanel juego;

	//Constructor de la clase ControlJuego
	public ControlJuego(Personaje personaje, JuegoPanel juego) {
		this.juego = juego;
		this.personaje = personaje;
		enemigos = new ArrayList<>();
		imgPlanta1 = Resource.getSourceImage("imagenes/planta1.png");
		imgPlanta2 = Resource.getSourceImage("imagenes/planta2.png");
		random = new Random();
		enemigos.add(getRandomPlanta());

	}

	//Metodo para reiniciar los enmigos
	public void restartEnemigos() {
		enemigos.clear();
		enemigos.add(getRandomPlanta());
	}

	// Anade un enemigo cuando uno pase por la mitad del mapa
	public void update() {
		for (Enemigo e : enemigos) {
			e.update();
			//Para sume la puntuacion cuando el personaje este arriba del enemigo una vez
			if (estaEncima(e, personaje) && !e.sumaPunto()) {
				juego.sumarPuntos(10);
				e.setSumaPunto(true);
			}

			//Cuando colisionen que se muera el personaje
			if (e.getBounds().intersects(hack(personaje.getBounds()))) {
				personaje.setVivo(false);
			}
		}
		Enemigo primerEnemigo = enemigos.get(0);

		//Para que salga otro enemigo cuando el primero salga del mapa
		if (primerEnemigo.estaMitad()) {
			enemigos.remove(primerEnemigo);
			enemigos.add(getRandomPlanta());
		}
	}
	
	//Cambia el tamaño de la colision para que ajustarlo
	private Rectangle hack(Rectangle personaje) {
		return new Rectangle(personaje.x + 10, personaje.y + 10, personaje.width - 20, personaje.height - 20);
	}

	//Metodo para saber si el personaje esta encima del enemigo
	private boolean estaEncima(Enemigo e, Personaje p) {
		return p.getX() > e.getBounds().x;
	}

	// Para seleccionar una de las dos plantas
	private Planta getRandomPlanta() {
		Planta planta = new Planta();
		planta.setPosX(1600);
		if (random.nextBoolean()) {

			planta.setImgPlanta(imgPlanta1);
		} else {
			planta.setImgPlanta(imgPlanta2);

		}
		return planta;

	}

	//Metodo para dibujar los enemigos
	public void draw(Graphics g) {
		for (Enemigo e : enemigos) {
			e.draw(g);
		}
	}

}
