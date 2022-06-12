package Juego;

import javax.imageio.ImageIO;
import java.applet.*;


import javax.swing.*;

import util.Animaciones;
import util.Resource;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class PersonajeMain {
	//variable declaradas
	private static final int GROUNDY = 800;
	private static final float GRAVITY = 1f;
	private float x=150;
	private float y=600;
	private float speedy=0;	
	private Animaciones personajerun;
	private BufferedImage personajeJump;
	private Rectangle rect;
	private boolean vivo=true;
	
	public boolean getVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public PersonajeMain() {
		

		personajerun = new Animaciones(100);
		personajerun.addFrame(Resource.getSourceImage("imagenes/runbuena.png"));
		personajerun.addFrame(Resource.getSourceImage("imagenes/Run (2)buena.png"));
		personajerun.addFrame(Resource.getSourceImage("imagenes/Run (3)buena.png"));
		personajerun.addFrame(Resource.getSourceImage("imagenes/Run (5)buena.png"));
		personajerun.addFrame(Resource.getSourceImage("imagenes/Run (8)bueno.png"));

		personajeJump =Resource.getSourceImage("imagenes/jump.png");
			
		
		rect=new Rectangle();
	}
	
	public void update() {
		personajerun.update();
		//para que se queda quieto a la altura del suelo y siga bajando
		if(y>=GROUNDY- personajerun.getFrame().getHeight()) {
			speedy=0;
			y=GROUNDY-  personajerun.getFrame().getHeight();
		}else {
		speedy+=GRAVITY;
		y+=speedy;
		}
		rect.x=(int) x;
		 rect.y=(int) y;
		 rect.width=personajerun.getFrame().getWidth();
		 rect.height=personajerun.getFrame().getHeight();
	}
	public Rectangle getBounds() {
		return rect;
	}
	//Para que salte y poder cambiar la fuerza del salto
	public void jump() {	
		speedy= -14;
		y +=speedy;
	}
	
	//Para pasarle la imagen del personaje el paint Graphics
		public void draw(Graphics g) {
			g.setColor(Color.black);		
			//g.drawRect( (int) x,(int) y, personajerun.getFrame().getWidth(), personajerun.getFrame().getHeight());
			g.drawImage(personajerun.getFrame(), (int) x, (int) y,null);
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getSpeedy() {
		return speedy;
	}
	public void setSpeedy(float speedy) {
		this.speedy = speedy;
	}
	

}

