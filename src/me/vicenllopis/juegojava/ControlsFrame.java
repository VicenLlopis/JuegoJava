package me.vicenllopis.juegojava;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Image;

public class ControlsFrame extends JFrame {
    public ControlsFrame() {
        super("Controles");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        init();
    }

    public void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        addComponets(panel);
        add(panel);
        pack();
    }

    void addComponets(JPanel panel) {
        Image logoImg = new ImageIcon("imagenes/anim1.png").getImage();
        logoImg = logoImg.getScaledInstance(50,80, Image.SCALE_SMOOTH);

        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(logoImg));
        logo.setBorder(BorderFactory.createEmptyBorder(50, 120, 0, 50));
        panel.add(logo);

        JLabel space = new JLabel("Usa espacio para saltar");
        space.setBounds(100, 100, 100, 100);
        space.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 50));
        panel.add(space);

        JLabel r = new JLabel("Usa R para recargar");
        r.setBounds(100, 200, 100, 100);
        r.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 50));
        panel.add(r);

        JLabel esc = new JLabel("Usa ESC para salir");
        esc.setBounds(100, 300, 100, 100);
        esc.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 50));
        panel.add(esc);

        JLabel creditos = new JLabel("Juego creado por Vicen Llopis");
        creditos.setBounds(100, 400, 100, 100);
        creditos.setBorder(BorderFactory.createEmptyBorder(100, 50, 20, 50));
        panel.add(creditos);
    }

}
