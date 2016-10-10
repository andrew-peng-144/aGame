package gamestates;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import aGame.GSManager;
import aGame.InputHandler;
import entities.Enemy;
import entities.Player;
import entities.Projectile;
import utils.AUtils;
import world.Block;
import world.Map;

/**
 * while ur playign teh game!!
 * 
 * @author asdf
 *
 */
public class InGameState extends GameState {
	public world.Map map;

	public InGameState(Map map) {
		this.map = map;

	}

	@Override
	public void tick(double dt) {
		if (InputHandler.esc)
			GSManager.pushState(new PausedState(map));
		map.tick(dt);

	}

	@Override
	public void render(Graphics2D g) {
		map.render(g);

		// debug

		g.drawString("Player pos on screen: " + (map.p1.getPosOnScreen().toStringInt()), 10, 500);
		g.drawString("Player posInMap:" + (map.p1.posInMap.toStringInt()), 10, 520);
		g.drawString("Player cameraPos: " + Player.cameraPos.toStringInt(), 10, 540);

		g.drawString("# projectiles in world: " + Projectile.projs.size(), 10, 560);
		g.drawString("# enemies in world: " + Enemy.enemies.size(), 10, 580);
		g.drawString("# blocks in world: " + Block.blocks.size(), 10, 600);
	}
	// public static world.Map getMap(){
	// if (GSManager.getCurrentState() instanceof InGameState) {
	// InGameState igs = (InGameState) GSManager.getCurrentState();
	// return igs.map;
	// }
	// return null;
	// }
	// public static Player getPlayaer() {
	// //should always be true, but just incase i guess lol
	// return getMap().p1;
	// }
}
