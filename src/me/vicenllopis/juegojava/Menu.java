package me.vicenllopis.juegojava;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import me.vicenllopis.juegojava.juego.JuegoFrame;
import me.vicenllopis.juegojava.util.Animaciones;
import me.vicenllopis.juegojava.util.Resource;

public class Menu extends JFrame {
	private ImageIcon imagenControles = new ImageIcon("imagenes/controles.png");
	private ImageIcon imagenJugar = new ImageIcon("imagenes/jugar.png");
	private ImageIcon imageSalir = new ImageIcon("imagenes/salir.png");

	public Menu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		init();
	}

	public void init() {
		final Image logoImg = new ImageIcon("imagenes/anim1.png").getImage();
		final Image finalImg = logoImg.getScaledInstance(100, 190, Image.SCALE_SMOOTH);

		JPanel panel = new JPanel() {
			@Override
			public void paintComponent(java.awt.Graphics g) {
				g.drawImage(finalImg, 20, 75, this);
			}

		};
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// create a menu with 3 buttons (play, controls, exit) and add it to the panel
		JLabel jugar = new JLabel();
		jugar.setBounds(100, 100, 100, 100);
		jugar.setIcon(imagenJugar);
		jugar.setBorder(BorderFactory.createEmptyBorder(50, 170, 0, 50));
		panel.add(jugar);

		JLabel controles = new JLabel();
		controles.setBounds(100, 200, 100, 100);
		controles.setIcon(imagenControles);
		controles.setBorder(BorderFactory.createEmptyBorder(50, 150, 0, 50));
		panel.add(controles);

		JLabel salir = new JLabel();
		salir.setBounds(100, 300, 100, 100);
		salir.setIcon(imageSalir);
		salir.setBorder(BorderFactory.createEmptyBorder(50, 175, 20, 50));
		panel.add(salir);

		// Add credits for the author
		JLabel creditos = new JLabel("Juego creado por Vicen Llopis");
		creditos.setBounds(100, 400, 100, 100);
		creditos.setBorder(BorderFactory.createEmptyBorder(10, 80, 20, 50));
		panel.add(creditos);

		// add a mouse listener to the buttons
		jugar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				JuegoFrame juego = new JuegoFrame();
			}
		});

		controles.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ControlsFrame frame = new ControlsFrame();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		salir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		add(panel);
		pack();
	}

}