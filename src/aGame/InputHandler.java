package aGame;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cmd.Command;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
	public static boolean w, a, s, d, esc, space, n1, n2, n3, n4, n5, n6, n7, n8, n9, n0;
	public static boolean leftClick, rightClick;

	public InputHandler() {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			leftClick = true;
			break;
		case MouseEvent.BUTTON2:
			rightClick = true;
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			leftClick = false;
			break;
		case MouseEvent.BUTTON2:
			rightClick = false;
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			w = true;
			break;
		case KeyEvent.VK_A:
			a = true;
			break;
		case KeyEvent.VK_S:
			s = true;
			break;
		case KeyEvent.VK_D:
			d = true;
			break;
		case KeyEvent.VK_ESCAPE:
			esc = true;
			break;
		case KeyEvent.VK_SPACE:
			space = true;
			break;
			
		case KeyEvent.VK_0:
			n0 = true;
			break;
		case KeyEvent.VK_1:
			n1 = true;
			break;
		case KeyEvent.VK_2:
			n2 = true;
			break;
		case KeyEvent.VK_3:
			n3 = true;
			break;
		case KeyEvent.VK_4:
			n4 = true;
			break;
		case KeyEvent.VK_5:
			n5 = true;
			break;
		case KeyEvent.VK_6:
			n6 = true;
			break;
		case KeyEvent.VK_7:
			n7 = true;
			break;
		case KeyEvent.VK_8:
			n8 = true;
			break;
		case KeyEvent.VK_9:
			n9 = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			w = false;
			break;
		case KeyEvent.VK_A:
			a = false;
			break;
		case KeyEvent.VK_S:
			s = false;
			break;
		case KeyEvent.VK_D:
			d = false;
			break;
		case KeyEvent.VK_ESCAPE:
			esc = false;
			break;
		case KeyEvent.VK_SPACE:
			space = false;
			break;
		case KeyEvent.VK_0:
			n0 = false;
			break;
		case KeyEvent.VK_1:
			n1 = false;
			break;
		case KeyEvent.VK_2:
			n2 = false;
			break;
		case KeyEvent.VK_3:
			n3 = false;
			break;
		case KeyEvent.VK_4:
			n4 = false;
			break;
		case KeyEvent.VK_5:
			n5 = false;
			break;
		case KeyEvent.VK_6:
			n6 = false;
			break;
		case KeyEvent.VK_7:
			n7 = false;
			break;
		case KeyEvent.VK_8:
			n8 = false;
			break;
		case KeyEvent.VK_9:
			n9 = false;
			break;
		}
	}

	public static Point getMousePosOnScreen() {
		Point p1 = MouseInfo.getPointerInfo().getLocation();
		Point p2 = Main.loop.getLocationOnScreen();
		return new Point(p1.x - p2.x, p1.y - p2.y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
