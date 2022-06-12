package me.vicenllopis.juegojava;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame {
	private ImageIcon imagenControles;
	private ImageIcon imagenJugar;
	private ImageIcon imageSalir;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Menu() {

		setTitle("Menu Juego");
		setSize(400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		JPanel panelmenu = new JPanel();

		panelmenu.setLayout(null);
		setContentPane(panelmenu);

		JLabel labeltitulo = new JLabel("Juego Magico");
		panelmenu.add(labeltitulo);
		labeltitulo.setBounds(125, 10, 120, 40);
		labeltitulo.setVisible(true);

		// opcion jugar del menu
		imagenJugar = new ImageIcon("imagenes/button (5).png");
		JLabel botonjugar = new JLabel(imagenJugar);
		panelmenu.add(botonjugar);
		botonjugar.setBounds(115, 90, 150, 40);
		botonjugar.setVisible(true);

		// opcion de Salir del menu
		imageSalir = new ImageIcon("imagenes/button (1).png");
		JLabel botonsalir = new JLabel(imageSalir);
		panelmenu.add(botonsalir);
		botonsalir.setBounds(115, 290, 150, 40);
		botonsalir.setVisible(true);

		// Opcion para ver los Contoles en el menu
		imagenControles = new ImageIcon("imagenes/button (2).png");
		JLabel botoncontroles = new JLabel(imagenControles);
		panelmenu.add(botoncontroles);
		botoncontroles.setBounds(115, 190, 150, 40);
		botoncontroles.setVisible(true);

		botonjugar.addMouseListener(new MouseAdapter() {
			public void mouseCliked(MouseEvent ev) {

				panelmenu.setVisible(false);

			}
		});

	}

}