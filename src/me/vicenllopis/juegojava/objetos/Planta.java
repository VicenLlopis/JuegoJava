package me.vicenllopis.juegojava.objetos;

import java.awt.image.BufferedImage;

import me.vicenllopis.juegojava.objetos.interfaces.Enemigo;

import java.awt.Graphics;
import java.awt.Rectangle;
//Clase para implementar los metodos y las variables de las plantas
public class Planta extends Enemigo {

	//Variables
	private boolean siSumaPunto = false;

	public BufferedImage getImgPlanta() {
		return imgEnemigo;
	}

	public void setImgPlanta(BufferedImage imgEnemigo) {
		this.imgEnemigo = imgEnemigo;
	}

	public void setPosX(int x) {
		posX = x;
	}

	public void setPosY(int y) {
		posY = y;
	}
 	//Constructor de la clase Planta
	public Planta() {
		posX = 500;
		posY = 725;
		rect = new Rectangle();
	}

	//Metodo para actualar la posicion de la planta y las colisiones
	@Override
	public void update() {
		posX = posX - 20;
		rect.x = posX;
		rect.y = posY;
		rect.width = imgEnemigo.getWidth();
		rect.height = imgEnemigo.getHeight();
	}

	// rectangulo para las colisiones
	@Override
	public Rectangle getBounds() {
		return rect;
	}

	// Para devuelver al paint lo que tiene pintar
	@Override
	public void draw(Graphics g) {
		g.drawImage(imgEnemigo, posX, posY, null);
	}

	// Para decir cunado generar la siguiente planta
	@Override
	public boolean estaMitad() {
		return posX + imgEnemigo.getWidth() < 50;

	}

	// Para que solo sume una vez por enemigo
	@Override
	public boolean sumaPunto() {
		return siSumaPunto;
	}

	@Override
	public void setSumaPunto(boolean siSumaPunto) {
		this.siSumaPunto = siSumaPunto;
	}

}
