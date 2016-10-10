package ai;

import aGame.Main;
import entities.Enemy;
import entities.Player;
import entities.Projectile;
import entities.Team;
import gamestates.InGameState;
import utils.Vector;
/**
 * functional interface
 * @author asdf
 *
 */
public interface AI {
	
	public abstract void tick(double dt, Enemy e);
	
	public static void shootAtPlayer(Enemy e){
		Vector v1 = Vector.pointToVector(e.getCenterInMap());
		Vector v2 = Vector.pointToVector(e.player.getCenterInMap());
		double theta = Vector.getVector(v1, v2).getAngle();

		Projectile.projs.add(new Projectile(e.pimg, e.pdmg, Team.ENEMY, e.pspeed, theta, e.pMaxRange,
				new Vector(e.posInMap.x + e.img.getWidth() / 2 - e.pimg.getWidth() / 2,
						e.posInMap.y + e.img.getHeight() / 2 - e.pimg.getHeight() / 2)));
	}
	public static void shuffle(Enemy e) {
		//MOVE IN A RANDOM DIRECTION
		e.dir.setRTheta(e.speed, Math.random() * 360);
	}
}
