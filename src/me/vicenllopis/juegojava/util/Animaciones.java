package me.vicenllopis.juegojava.util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animaciones {
	private List<BufferedImage> imgs;
	private int imagenIndex = 0;
	private int timer;
	private long preTime;

	public Animaciones(int timer) {
		this.timer = timer;
		imgs = new ArrayList<>();

	}

	// para ir actualizando las imagenes con los milisegundo que tu quieras
	public void update() {

		if (System.currentTimeMillis() - preTime > timer) {
			imagenIndex++;
			if (imagenIndex >= imgs.size()) {
				imagenIndex = 0;
			}
			preTime = System.currentTimeMillis();
		}
	}

	// añade las imagenes en al arraylist
	public void addFrame(BufferedImage imagen) {
		imgs.add(imagen);
	}

	public BufferedImage getFrame() {
		if (imgs.size() > 0) {
			return imgs.get(imagenIndex);
		}
		return null;
	}
}
