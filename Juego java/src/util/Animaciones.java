package util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animaciones {
	private List <BufferedImage> imagenes;
	private int imagenIndex=0;
	private int timer;
	private long pretime;
	
	public Animaciones(int timer) {
		this.timer= timer;
		imagenes= new ArrayList<BufferedImage>();
		
	}
	//para ir actualizando las imagenes con los milisegundo que tu quieras
	public void  update() {
		
		if(System.currentTimeMillis()-pretime >timer) {	
		imagenIndex ++;
		if(imagenIndex >= imagenes.size()) {
			imagenIndex=0;
		}
		pretime =System.currentTimeMillis();
	}
}
	//añade las imagenes en al arraylist
	public void addFrame(BufferedImage imagen) {
		imagenes.add(imagen);
	}

	
	public BufferedImage getFrame() {
		if(imagenes.size()>0) {
			return imagenes.get(imagenIndex);
		}
		return null;
	}
}
