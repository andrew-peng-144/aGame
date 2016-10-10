package listeners;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import aGame.GSManager;
import aGame.Loop;
import aGame.Main;
import entities.Player;
import gamestates.InGameState;
import guistuff.ClickButton;
@Deprecated
public class MouseManager implements MouseListener, MouseMotionListener {
	public Point click = new Point();
	public Player p;



	public void setPlayerReference(Player p) {
		this.p = p;

	}

	public MouseManager() {
	}

	@Override
	public void mousePressed(MouseEvent e) {

//		if (e.getButton() == MouseEvent.BUTTON1) {
//			click.setLocation(e.getX(), e.getY());
//			System.out.println("PRESSED");
//
//			if (GSManager.getCurrentState() instanceof InGameState) {
//				System.out.println("PLAYER: " + p);
//				System.out.println("WEAPON: " + p.weapon);
//				p.weapon.isShooting = true;
//			}
//			for (ClickButton cb : ClickButton.cbtns)
//				if (cb.getMouseZone().contains(click))
//					cb.isPressed = true;
//
//		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		if (e.getButton() == MouseEvent.BUTTON1) {
//			if (GSManager.getCurrentState() instanceof InGameState)
//				p.weapon.isShooting = false;
//
//			for (ClickButton cb : ClickButton.cbtns) {
//				if (cb.getMouseZone().contains(click) && cb.isPressed) {
//					cb.isPressed = false;
//					cb.isReleased = true;
//				}
//			}
//		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
//		for (ClickButton cb : ClickButton.cbtns) {
//			if (cb.getMouseZone().contains(getMousePos()))
//				cb.isHovered = true;
//			else
//				cb.isHovered = false;
//		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
