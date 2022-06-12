package Juego;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class JuegoMain extends JFrame{
	public static void main(String[] args) {
		JuegoMain jm = new JuegoMain();
		jm.setVisible(true);
		jm.startGame();
	
	}
	private Juego juegito;
	
	public JuegoMain() {
		super("Juego Robot");
		setSize(1100,905);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juegito =new Juego();
		add(juegito);
		addKeyListener(juegito);
	}
	public void startGame() {
		juegito.startGame();
		
	}
	/*public void paint (Graphics g) {
		super.paint(g);
		BufferedImage image=null;
		try {
			image= ImageIO.read(new File(""));
					g.drawImage(image, 100, 100, null);
		}catch(IOException e) {
			e.printStackTrace(); 
		}
	}*/
	
}
