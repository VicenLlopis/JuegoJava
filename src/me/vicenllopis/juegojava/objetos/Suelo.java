package me.vicenllopis.juegojava.objetos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.vicenllopis.juegojava.util.Resource;

//Clase para crear el Juego
public class Suelo {
	private static final int GROUNDY = 800;
	private ArrayList<ImagenSuelo> listImagen;
	private BufferedImage imgSuelo1;

	// Constructor de la clase Suelo 
	public Suelo() {
		imgSuelo1 = Resource.getSourceImage("imagenes/suelo.png");
		listImagen = new ArrayList<>();
		// Para que el suelo tenga el tamano deseado
		int tamanoSuelo = 1600 / imgSuelo1.getWidth() + 1;
		for (int i = 0; i < tamanoSuelo; i++) {
			ImagenSuelo imagenSuelo = new ImagenSuelo();
			imagenSuelo.posX = (int) (i * imgSuelo1.getWidth());
			imagenSuelo.imagen = imgSuelo1;
			listImagen.add(imagenSuelo);
		}
	}

	// para que el suelo se vaya moviendo
	public void update() {
		for (ImagenSuelo imagensuelo : listImagen) {
			// Para elegir lo rapido que se mueve el suelo
			imagensuelo.posX = imagensuelo.posX - 4;
		}
		//Para que el suelo no se acabe y se vaya creando
		ImagenSuelo primertElement = listImagen.get(0);
		if (listImagen.get(0).posX + imgSuelo1.getWidth() < 0) {
			primertElement.posX = listImagen.get(listImagen.size() - 1).posX + imgSuelo1.getWidth();
			listImagen.add(primertElement);
			listImagen.remove(0);

		}

	}

	//Para dibujar el suelo
	public void draw(Graphics g) {
		for (ImagenSuelo imagenSuelo : listImagen) {
			g.drawImage(imagenSuelo.imagen, imagenSuelo.posX, GROUNDY, null);
		}

	}
	//Guarda la imagen y la posicion en la que se encuentra
	private class ImagenSuelo {
		int posX;
		BufferedImage imagen;
	}

}
