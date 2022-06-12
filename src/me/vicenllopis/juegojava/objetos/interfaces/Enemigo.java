package me.vicenllopis.juegojava.objetos.interfaces;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics;

//Interfaz que define los metodos y variables del enemigo
public abstract class Enemigo {

	//Variables de los enemigos
	protected int posX;
	protected int posY;
	protected Rectangle rect;
	protected BufferedImage imgEnemigo;

	//Metodos de los enemigos
	public abstract Rectangle getBounds();

	public abstract void draw(Graphics g);

	public abstract void update();

	public abstract boolean estaMitad();

	public abstract boolean sumaPunto();

	public abstract void setSumaPunto(boolean siSumaPunto);

}
