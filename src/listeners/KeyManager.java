package listeners;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import aGame.GSManager;
import aGame.Main;
import entities.Player;
import gamestates.InGameState;
import gamestates.PausedState;
import inv.Weapon;

@Deprecated
public class KeyManager // implements KeyListener
{
	// public Player p;
	// public boolean pExists;
	//
	// public void setPlayerReference(Player p) {
	// this.p = p;
	// pExists = true;
	// }
	//
	// public KeyManager() {
	// }
	//
	// @Override
	// public void keyPressed(KeyEvent e) {
	//
	// // System.out.println(Main.loop.p1.pos);
	// if (GSManager.getCurrentState() instanceof InGameState) {
	// if (pExists) {
	// // System.out.println(p);
	// switch (e.getKeyCode()) {
	// case KeyEvent.VK_W:
	// p.up = true;
	// break;
	// case KeyEvent.VK_A:
	// p.left = true;
	// break;
	// case KeyEvent.VK_S:
	// p.down = true;
	// break;
	// case KeyEvent.VK_D:
	// p.right = true;
	// break;
	// case KeyEvent.VK_1:
	// p.setWeapon(Weapon.starter);
	// break;
	// case KeyEvent.VK_2:
	// p.setWeapon(Weapon.melee1);
	// break;
	// case KeyEvent.VK_3:
	// p.setWeapon(Weapon.laser);
	// break;
	// case KeyEvent.VK_ESCAPE:
	// GSManager.pushState(new PausedState(((InGameState)
	// GSManager.getCurrentState()).map));
	// break;
	// }
	// }
	//
	// } else if (GSManager.getCurrentState() instanceof PausedState) {
	// switch (e.getKeyCode()) {
	// case KeyEvent.VK_ESCAPE:
	// System.out.println("FALSEPRESSEDPAUSE");
	// GSManager.popCurrentState();
	// }
	// }
	// }
	//
	// @Override
	// public void keyReleased(KeyEvent e) {
	// if (GSManager.getCurrentState() instanceof InGameState) {
	// if (pExists)
	// switch (e.getKeyCode()) {
	// case KeyEvent.VK_W:
	// p.up = false;
	// break;
	// case KeyEvent.VK_A:
	// p.left = false;
	// break;
	// case KeyEvent.VK_S:
	// p.down = false;
	// break;
	// case KeyEvent.VK_D:
	// p.right = false;
	// break;
	// }
	// }
	// }
	//
	// @Override
	// public void keyTyped(KeyEvent arg0) {
	// // TODO Auto-generated method stub
	//
	// }

}
