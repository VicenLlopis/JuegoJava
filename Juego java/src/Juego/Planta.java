package Juego;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import util.Resource;
import java.awt.Rectangle;
public class Planta extends Enemigo {
	
	private BufferedImage imgPlanta;
	private int PosX,PosY;
	private Rectangle rect;
	private PersonajeMain personajeMain;
	private boolean siSumaPunto=false;
	
	public BufferedImage getImgPlanta() {
		return imgPlanta;
	}
	public void setImgPlanta(BufferedImage imgPlanta) {
		this.imgPlanta = imgPlanta;
	}

	public void setPosX(int x) {
		PosX = x;
	}
;
	
	public void setPosY(int y) {
		PosY = y;
	}
	public Planta(PersonajeMain personajeMain)
	{
		this.personajeMain=personajeMain;
		PosX=500;
		PosY=720;
		rect= new Rectangle();
		rect.intersects(rect);
		
	}
	@Override
	public void update() {
		PosX=PosX-20;
		rect.x = PosX;
		rect.y=PosY;
		rect.width=imgPlanta.getWidth();
		rect.height=imgPlanta.getHeight();
	}
	//rectangulo para las colisiones
	@Override
	public Rectangle getBounds() {
		return rect;
	}
	//Para devuelver al paint lo que tiene pintar
	@Override
	public void draw(Graphics g) {
		g.drawImage(imgPlanta,PosX,PosY,null);
	}
	//Para decir cunado generar la siguiente planta
	@Override
	public boolean estaMitad() {
		return PosX+imgPlanta.getWidth()<50;
		
	}

	@Override
	public boolean estaEncima() {
		return personajeMain.getX()>PosX;
	}
	//Para que solo sume una vez por enemigo
	@Override
	public boolean siSumaPunto() {	
		return siSumaPunto;
	}
	@Override
	public void setsiSumaPunto(boolean siSumaPunto) {
		this.siSumaPunto=siSumaPunto;
		
	}
	

	
	
}
