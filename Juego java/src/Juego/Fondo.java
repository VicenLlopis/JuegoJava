package Juego;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import util.Resource;

import java.awt.Graphics;

public class Fondo {
	private ArrayList<ImagenFondo> fondoLista;
	private BufferedImage imagenFondo1;
	//Le das el tama�o que quieras al fondo y lo a�ades a la lista para poder hacer que se mueva
	
	public Fondo(Juego juegito) {
		imagenFondo1=Resource.getSourceImage("imagenes/Cartoon_Forest_BG_01.png");
		 fondoLista =new  ArrayList<ImagenFondo>(); 
		 int tama�oFondo =20000/imagenFondo1.getWidth()+1;
			for(int i=0;i<tama�oFondo;i++) {		
				ImagenFondo imagenFondo = new ImagenFondo();
				imagenFondo.PosX = (int) (i * imagenFondo1.getWidth());
				imagenFondo.imagen= imagenFondo1;
				 fondoLista.add(imagenFondo);			
			}
	}
	//Para que actualice el fondo y se vaya moviendo
	public void update() {
		for(ImagenFondo imagenFondo : fondoLista) {
			 imagenFondo.PosX--;
		} 
		 
		//Mete la primera imagen que haya en el la lista y la pone al principio para que se cree el bucle del Fondo	
		ImagenFondo primertElemento =fondoLista.get(0);	
		if(fondoLista.get(0).PosX + imagenFondo1.getWidth()<0) {
			primertElemento.PosX=fondoLista.get(fondoLista.size()-1).PosX+imagenFondo1.getWidth();
			fondoLista.add(primertElemento);
			fondoLista.remove(0);
			
		}
	}
	//Para imprimir la imagen del ofndo en pantalla
	public void draw(Graphics g) {
		for(ImagenFondo imagenFondo : fondoLista) {
		g.drawImage(imagenFondo.imagen, imagenFondo.PosX, 0 ,null);
		}
	}
	
	private class ImagenFondo {
		 int PosX;	 
		 BufferedImage imagen;
		
	}

}
