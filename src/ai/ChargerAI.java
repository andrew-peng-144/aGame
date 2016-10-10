package ai;

import aGame.Main;
import entities.Enemy;
import entities.Player;
import gamestates.InGameState;
import utils.Vector;

/**
 * hurls at the enemy
 * 
 * @author asdf
 *
 */
public class ChargerAI implements AI {
	float moveRate;
	float shootRate;
	
	float currentMoveRate;
	float currentShootRate;
	
	/**
	 * The rate at which a moving method is invoked, when charging
	 */
	final float chargeMoveRate;

	public ChargerAI(float moveRate, float shootRate, float chargeMoveRate, float chargeShootRate) {
		this.moveRate = moveRate;
		this.shootRate = shootRate;
		this.chargeMoveRate = chargeMoveRate;
		this.chargeShootRate = chargeShootRate;
	}
	//TODO
	float currentSpeed;
	float normalSpeed;
	float chargeSpeed;

	/**
	 * the shooting rate when charging
	 */
	final float chargeShootRate;

	/**
	 * must be between 0 and 1, signifies at what percentage/100 will the enemy
	 * <br>
	 * start charging, defaults to half
	 */
	float chargingRangeMultiplier = .5f;

	boolean isCharging;

	private double tcMove;
	private double tcShoot;

	@Override
	public void tick(double dt, Enemy e) {
		tcShoot += dt;
		tcMove += dt;

		if (e.getVecToPlayer().magnitude() <= e.visionRange * chargingRangeMultiplier) {
			isCharging = true;
		} else {
			isCharging = false;
		}

		if (isCharging) {
			// then increase the action rates (decrease amount of time between
			// actions)
			currentMoveRate = chargeMoveRate;
			currentShootRate = chargeShootRate;
		} else {
			currentMoveRate = moveRate;
			currentShootRate = shootRate;
		}

		if (tcMove >= currentMoveRate) {
			tcMove = 0;
			if (isCharging) {
				chargeTowardPlayer(e);
			} else if (e.canSeePlayer()) { // non-charging
				AI.shuffle(e);
			}

		}
		if (tcShoot >= currentShootRate) {
			tcShoot = 0;
			if (e.canSeePlayer()) {
				AI.shootAtPlayer(e);
			}
		}

	}

	private void chargeTowardPlayer(Enemy e) {
		e.dir.setRTheta(e.speed, Vector.getVector(e.getCenterInMap(), e.player.getCenterInMap()).getAngle());
	}

}
