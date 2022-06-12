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

		JPanel panelMenu = new JPanel();

		panelMenu.setLayout(null);
		setContentPane(panelMenu);

		JLabel labelTitulo = new JLabel("Juego Magico");
		panelMenu.add(labelTitulo);
		labelTitulo.setBounds(125, 10, 120, 40);
		labelTitulo.setVisible(true);

		// opcion jugar del menu
		imagenJugar = new ImageIcon("imagenes/button (5).png");
		JLabel botonJugar = new JLabel(imagenJugar);
		panelMenu.add(botonJugar);
		botonJugar.setBounds(115, 90, 150, 40);
		botonJugar.setVisible(true);

		// opcion de Salir del menu
		imageSalir = new ImageIcon("imagenes/button (1).png");
		JLabel botonSalir = new JLabel(imageSalir);
		panelMenu.add(botonSalir);
		botonSalir.setBounds(115, 290, 150, 40);
		botonSalir.setVisible(true);

		// Opcion para ver los Contoles en el menu
		imagenControles = new ImageIcon("imagenes/button (2).png");
		JLabel botonControles = new JLabel(imagenControles);
		panelMenu.add(botonControles);
		botonControles.setBounds(115, 190, 150, 40);
		botonControles.setVisible(true);

		botonJugar.addMouseListener(new MouseAdapter() {
			public void mouseCliked(MouseEvent ev) {

				panelMenu.setVisible(false);

			}
		});

	}

}