package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import utils.AUtils;
import utils.Vector;

public class Projectile extends Entity {

	public float maxRange;
	private float distanceTraveled;
	public int dmg;
	public boolean piercing;
	public static ArrayList<Projectile> projs = new ArrayList<>();
	
	/**
	 * 
	 * @param theta
	 *            IN RADIANS
	 */
	// public static List<Projectile> projs = new ArrayList<>();

	public Projectile(BufferedImage img, int dmg, Team team, float speed, double theta, float maxRange, Vector posInMap) {
		super(img, speed, team, posInMap);
		this.dmg = dmg;
		this.team = team;
		this.speed = speed;
		this.maxRange = maxRange;
		this.img = AUtils.getRotatedImage(img, theta);
		dir = new Vector(new Double(speed), new Double(theta));

	}

	// public Projectile(float speed, double theta, float x, float y,
	// BufferedImage img){
	//
	// }
	@Override
	public void tick(double dt) {
		super.tick(dt);
		distanceTraveled += dir.scale(dt).magnitude();

		if (distanceTraveled >= maxRange) {
			// System.out.println("REACHED");
			shouldBeRemoved = true;
		}
		
		
	}
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		// TODO Auto-generated method stub
		//g.drawRect((int) pos.x, (int) pos.y, hb.width, hb.height);
		
	}

	@Override
	public String toString() {
		return "Projectile [dir=" + dir + ", speed=" + speed + ", pos=" + getPosOnScreen() + "]";
	}
	
	public static void checkCollisionWithProjectiles(Enemy e, double dt) {
		for (Projectile p : projs){
			if(e.getHitBoxInMap().intersects(p.getHitBoxInMap())){
				if(!e.team.equals(p.team)){
					e.hp -= p.dmg;
					if(!p.piercing)
						p.shouldBeRemoved = true;
					if(e.hp <= 0)
						e.isDead = true;
				}
			}
		}
	}
	public static void checkCollisionWithProjectiles(Player p1, double dt) {
		for (Projectile p : projs){
			if(p1.getHitBoxInMap().intersects(p.getHitBoxInMap())){
				if(!p1.team.equals(p.team)){
					p1.hp -= p.dmg;
					if(!p.piercing)
						p.shouldBeRemoved = true;
					if(p1.hp <= 0)
						p1.isDead = true;
				}
			}
		}
	}


	
}
