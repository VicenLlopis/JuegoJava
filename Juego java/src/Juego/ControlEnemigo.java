package Juego;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import util.Resource;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ControlEnemigo {
	
	private List<Enemigo> enemigos;
	private Random random;
	private BufferedImage imgPlanta1,imgPlanta2;
	private Rectangle rect;
	private PersonajeMain personajeMain;
	private Juego juego;
	
	public ControlEnemigo(PersonajeMain personajeMain,Juego juego) {
		this.juego=juego;
		this.personajeMain= personajeMain;
		enemigos = new ArrayList<Enemigo>();
		imgPlanta1= Resource.getSourceImage("imagenes/2.png");
		imgPlanta2= Resource.getSourceImage("imagenes/4.png");
		random = new Random();
		enemigos.add(getRandomPlanta());
	
	}
	public void reini() {
		enemigos.clear();
		enemigos.add(getRandomPlanta());
	}
	//Añade un enemigo cuando uno pase por la mitad del mapa
	public void update() {		
		for(Enemigo e:enemigos) {
			e.update();
			if(e.estaEncima()&& !e.siSumaPunto()) {
				juego.sumarPuntos(10);
				e.setsiSumaPunto(true);
			}
			if(e.getBounds().intersects(personajeMain.getBounds())) {
				personajeMain.setVivo(false);
			}		
		}Enemigo primerEnemigo =enemigos.get(0);
		if(primerEnemigo.estaMitad()) {
			enemigos.remove(primerEnemigo);
			enemigos.add(getRandomPlanta());
			
		}
		
	}
	//Para seleccionar una de las dos plantas 
	private Planta getRandomPlanta() {
		Planta planta= new Planta(personajeMain);
		planta.setPosX(1600);
		if(random.nextBoolean()) {

			planta.setImgPlanta(imgPlanta1);			
		}else {
			planta.setImgPlanta(imgPlanta2);
		
		}
		return planta;
		
	}
	
	public void draw(Graphics g) {
		for(Enemigo e: enemigos) {
			e.draw(g);
		}
	}
	public Rectangle getBounds() {
		return rect;
	}

}
