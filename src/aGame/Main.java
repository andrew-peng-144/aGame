package aGame;

import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import entities.Player;
import listeners.KeyManager;

public class Main {
	public static InputHandler ih;
	public static JFrame f;
	public static Loop loop;
	Main() {
		f = new JFrame();
		f.setTitle("TEST");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setPreferredSize(new Dimension(1000, 700));
		f.pack();
		f.setLocationRelativeTo(null);

		loop = new Loop(f.getWidth(), f.getHeight());
		// exists solely for pointer purposes: many objects that are initialized
		// in the beginning require player pointer, not sure if this is a viable
		// design or not
		f.addKeyListener(ih = new InputHandler());
		f.add(loop);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}
}