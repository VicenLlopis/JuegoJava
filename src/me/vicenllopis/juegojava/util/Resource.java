package me.vicenllopis.juegojava.util;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Resource {

	private Resource() {
	}

	public static BufferedImage getSourceImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		}catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
