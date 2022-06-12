package Juego;
import java.awt.Rectangle;
import java.awt.Graphics;
public abstract class Enemigo {
	public abstract Rectangle getBounds();
	public abstract void draw(Graphics g);
	public abstract void update();
	public abstract boolean estaMitad();
	public abstract boolean estaEncima();
	public abstract boolean siSumaPunto();
	public abstract void setsiSumaPunto(boolean siSumaPunto);
	

}
