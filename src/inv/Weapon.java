package inv;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.print.DocFlavor.STRING;

import aGame.InputHandler;
import aGame.Loop;
import entities.Assets;
import entities.Entity;
import entities.Projectile;
import utils.AUtils;
import utils.Vector;

/**
 * a representation of an offensive weapon that has damage, fire rate,
 * projectile image, etc. Makes it easier to give entities entire SETS of weapon
 * stats <br>
 * right now, only player can wield weapons
 * 
 * @author andrwe
 *
 */
public class Weapon extends Item {

	public Weapon(int dmg, float fireRate, float speed, float maxRange, String name, BufferedImage projImg) {
		this.dmg = dmg;
		this.fireRate = fireRate;
		this.speed = speed;
		this.maxRange = maxRange;
		this.projImg = projImg;
		this.name = name;
	}

	int dmg;
	public float fireRate;
	public float speed;
	public float maxRange;
	public BufferedImage projImg;
	public String name;
	// secondary fire
	// int dmg2;
	// float fireRate2;
	// float speed2;
	// float maxRange2;
	// BufferedImage projImg2;

	double tcShoot;
	// public boolean isShooting;

	// BufferedImage icon;


	public void tick(double dt, boolean isActivated, Entity e) {
		tcShoot += dt;
		if (isActivated) {
			if (tcShoot >= fireRate) {
				tcShoot = 0;
				shootPrimary(dt, e);
			}
		}
	}

	public void render(Graphics2D g) {

	}

	Point projSpawnPoint;

	public void setProjSpawnPoint(Point sp) {
		projSpawnPoint = sp;
	}

	private void shootPrimary(double dt, Entity e) {

		Vector v1 = Vector.pointToVector(e.getCenterOnScreen());
		Vector v2 = Vector.pointToVector(InputHandler.getMousePosOnScreen());
		double theta = Vector.getVector(v1, v2).getAngle();
		// System.out.println("theta (degrees) : " + Math.toDegrees(theta));
		if (projSpawnPoint == null)
			Projectile.projs.add(new Projectile(projImg, dmg, e.team, speed, theta, maxRange, new Vector(
					e.getCenterInMap().x - projImg.getWidth() / 2, e.getCenterInMap().y - projImg.getHeight() / 2)));
		else
			Projectile.projs.add(
					new Projectile(projImg, dmg, e.team, speed, theta, maxRange, Vector.pointToVector(projSpawnPoint)));

	}

	private void shootSecondary(double dt, Entity e) {

	}

	public static Weapon starter = new Weapon(4, 250f, 1f, 500f, "Flame Missile",
			AUtils.getScaledImage(Assets.playerproj_1, 3));
	public static Weapon melee1 = new Weapon(18, 350f, .6f, 100f, "Scimitar", AUtils.getScaledImage(Assets.melee_1, 5));
	public static Weapon laser = new Weapon(10, 10f, 2f, 1000f, "GAMMA RAY (DEBUG)",
			AUtils.getScaledImage(Assets.laser_1, 3));
}
