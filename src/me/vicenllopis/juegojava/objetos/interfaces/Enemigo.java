package me.vicenllopis.juegojava.objetos.interfaces;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class Enemigo {

	protected int posX;
	protected int posY;
	protected Rectangle rect;
	protected BufferedImage imgEnemigo;

	public abstract Rectangle getBounds();

	public abstract void draw(Graphics g);

	public abstract void update();

	public abstract boolean estaMitad();

	public abstract boolean sumaPunto();

	public abstract void setSumaPunto(boolean siSumaPunto);

}
