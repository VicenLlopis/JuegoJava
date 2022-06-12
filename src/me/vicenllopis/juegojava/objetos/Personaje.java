package me.vicenllopis.juegojava.objetos;

import me.vicenllopis.juegojava.util.*;
import java.awt.Rectangle;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.Color;
import java.awt.Graphics;

public class Personaje {
	// variable declaradas
	private static final int FLOOR_ALTITUDE = 800;
	private static final float GRAVITY = 1f;
	private int x = 150;
	private int y = 600;
	private float speedy = 0;
	private Animaciones anim;
	private Rectangle rect = new Rectangle();
	private boolean vivo = true;

	// Constructores
	public boolean getVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	//Contructor de la clase Personaje que recibe una animacion
	public Personaje() {
		loadAnimation();
	}

	//Añade las imagenes a la animacion
	private void loadAnimation() {
		anim = new Animaciones(100);
		anim.addFrame(Resource.getSourceImage("imagenes/anim1.png"));
		anim.addFrame(Resource.getSourceImage("imagenes/anim2.png"));
		anim.addFrame(Resource.getSourceImage("imagenes/anim3.png"));
		anim.addFrame(Resource.getSourceImage("imagenes/anim4.png"));
		anim.addFrame(Resource.getSourceImage("imagenes/anim5.png"));
	}

	//Metodo que devuelve la animacion del personaje
	public void update() {
		anim.update();
		// para que se queda quieto a la altura del suelo y siga bajando
		if (y >= FLOOR_ALTITUDE - anim.getFrame().getHeight()) {
			speedy = 0;
			y = FLOOR_ALTITUDE - anim.getFrame().getHeight();
		} else {
			speedy += GRAVITY;
			y += speedy;
		}
		//Variables para las colisiones
		rect.x = x;
		rect.y = y;
		rect.width = anim.getFrame().getWidth();
		rect.height = anim.getFrame().getHeight();
	}

	public Rectangle getBounds() {
		return rect;
	}

	// Para que salte y poder cambiar la fuerza del salto
	public void jump() {
		//Añade audio al salto y salta si el personaje no esta por los aires
		if (y >= FLOOR_ALTITUDE - anim.getFrame().getHeight()) {
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(new File("sonidos/jump.wav").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
			speedy = -14;
		} else {
			speedy = 0;
		}
		y += speedy;
	}

	// Para pasarle la imagen del personaje el paint Graphics
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawOval((int) x, (int) y, (int) this.getBounds().getWidth(),
				(int) this.getBounds().getHeight());
		g.drawImage(anim.getFrame(), (int) x, (int) y, null);
	}

	//Getters y Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSpeedy() {
		return speedy;
	}

	public void setSpeedy(float speedy) {
		this.speedy = speedy;
	}

}
