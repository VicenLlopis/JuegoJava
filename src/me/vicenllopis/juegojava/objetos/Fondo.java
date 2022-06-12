package me.vicenllopis.juegojava.objetos;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Random;

import me.vicenllopis.juegojava.util.Resource;

public class Fondo {
	private ArrayList<ImagenFondo> fondoLista;
	private BufferedImage imagenFondo1;
	// Le das el tama�o que quieras al fondo y lo aades a la lista para poder hacer
	// que se mueva

	public Fondo() {
		// generate a random number between 0 and 3
		Random r = new Random();
		int randomNumber = r.nextInt(4);
		imagenFondo1 = Resource.getSourceImage("imagenes/back" + randomNumber + ".png");
		fondoLista = new ArrayList<>();
		int tamanoFondo = imagenFondo1.getWidth();
		for (int i = 0; i < tamanoFondo; i++) {
			ImagenFondo imagenFondo = new ImagenFondo();
			imagenFondo.PosX = (int) (i * imagenFondo1.getWidth());
			imagenFondo.imagen = imagenFondo1;
			fondoLista.add(imagenFondo);
		}
	}

	// Para que actualice el fondo y se vaya moviendo
	public void update() {
		for (ImagenFondo imagenFondo : fondoLista) {
			imagenFondo.PosX--;
		}
	}

	// Para imprimir la imagen del fondo en pantalla
	public void draw(Graphics g) {
		for (ImagenFondo imagenFondo : fondoLista) {
			g.drawImage(imagenFondo.imagen, imagenFondo.PosX, 0, null);
		}
	}

	private class ImagenFondo {
		int PosX;
		BufferedImage imagen;

	}

}
