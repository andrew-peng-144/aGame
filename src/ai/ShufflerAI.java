package ai;

import java.util.Random;

import aGame.Main;
import entities.Enemy;
import entities.Player;
import entities.Projectile;
import entities.Team;
import utils.Vector;

/**
 * easiest ai 2k16 just moves around in random directions!
 * (becomes hardest ai 2k16)
 * 
 * @author adnrew
 *
 */
public class ShufflerAI implements AI {
	float shootRate;
	float moveRate;

	public ShufflerAI(float moveRate, float shootRate) {
		this.shootRate = shootRate;
		this.moveRate = moveRate;
	}

	private double tcShoot;
	private double tcMove;

	public void tick(double dt, Enemy e) {
		tcShoot += dt;
		tcMove += dt;
		if (tcShoot >= shootRate) {
			tcShoot -= shootRate;
			if (e.canSeePlayer())
				AI.shootAtPlayer(e);
		}
		if (tcMove >= moveRate) {
			tcMove -= moveRate;
			AI.shuffle(e);
		}
	}




}
