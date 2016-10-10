package aGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import entities.Assets;
import gamestates.InGameState;
import gamestates.MenuState;
import listeners.MouseManager;
import utils.AUtils;

public class Loop extends JPanel implements Runnable {

	/**
	 * for saving later but i suck at programming so thats a no
	 */
	private static final long serialVersionUID = 1L;

	long ticks;
	long frames;
	double elapsedTime;

	// static public MouseManager mm;
	// public AMouseMotionListener mml = new AMouseMotionListener();

	static public Point centerOfScreen;

	static public GSManager gsm;
	static public int width;
	static public int height;

	public Loop(int w, int h) {
		width = w;
		height = h;
		setPreferredSize(new Dimension(w, h));

	}

	@Override
	public void addNotify() {
		super.addNotify();

		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	Thread t;

	public void init() {
		buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) buff.getGraphics();

		addMouseListener(Main.ih);
		addMouseMotionListener(Main.ih);

		// init models BEFORE making map
		// (and i guess before even the menu loads...)
		Assets.initSprites();

		// TODO push to menustate first
		GSManager.pushState(new MenuState());

		centerOfScreen = new Point(this.getWidth() / 2, this.getHeight() / 2);

	}

	/**
	 * displays the image which has all the graphics drawings
	 */
	void finalRender() {
		Graphics g2 = getGraphics();
		if (buff != null) {
			g2.drawImage(buff, 0, 0, null);
		}
		g2.dispose();
	}

	Graphics2D g;
	BufferedImage buff;

	public void run() {
		init();

		double deltatime;
		long currentTime;
		long lastTime = System.currentTimeMillis();

		while (true) {
			currentTime = System.currentTimeMillis();
			deltatime = currentTime - lastTime;
			if (deltatime > 300) {
				deltatime = 300;
			}
			tick(deltatime);
			render();
			elapsedTime += deltatime;

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lastTime = currentTime;
		}
	}

	// ArrayList<Projectile> projs = new ArrayList<Projectile>();

	void tick(double dt) {
		ticks++;
		GSManager.tickState(dt);
	}

	void render() {
		g.clearRect(0, 0, getWidth(), getHeight());
		//
		frames++;

		GSManager.renderState(g);

		g.drawString("FPS: " + (int) (((double) frames) / (elapsedTime / 1000)) + "", 10, 10);
		g.drawString("Current GameState: " + GSManager.getCurrentState().getClass().getName(), 10, 40);

		finalRender();
	}

}
