package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import aGame.GSManager;
import aGame.Main;
import ai.AI;
import gamestates.InGameState;
import utils.Vector;

public class Enemy extends Entity {

	public AI ai;
	public int hp;
	public final int MAX_HP;
	public double visionRange;
	public static ArrayList<Enemy> enemies = new ArrayList<>();

	public Color mainColor = Color.WHITE;

	public HashMap<EnemyImgState, BufferedImage[]> imgs = new HashMap<>();

	public Enemy(BufferedImage img, float speed, double visionRange, int hp, Vector posInMap, Player p, AI ai) {
		super(img, speed, Team.ENEMY, posInMap);
		this.ai = ai;
		visible = true;
		this.hp = hp;
		this.MAX_HP = hp;
		this.visionRange = visionRange;
		this.player = p;
	}

	public void tick(double dt) {
		if (isDead) {
			shouldBeRemoved = true;
			Random r = new Random();
			for (int i = 0; i < 10; i++)
				Particle.particles.add(new Particle(mainColor, r.nextInt(10) + 5, .4f, r.nextDouble() * 2 * Math.PI,
						100d, this.posInMap));
		}
		ai.tick(dt, this);
		super.tick(dt);
		Projectile.checkCollisionWithProjectiles(this, dt);
	}

	public void render(Graphics2D g) {
		super.render(g);
		Color oc = g.getColor();

		if (hp < MAX_HP) {
			// draw teh hp bar!
			g.setColor(new Color(0, 127, 0, 64));
			g.fillRect((int) getPosOnScreen().x, (int) getPosOnScreen().y - 50, getHitBoxOnScreen().width, 20);
			g.setColor(new Color(0, 255, 0, 200));
			g.fillRect((int) getPosOnScreen().x, (int) getPosOnScreen().y - 50,
					(int) (((hp + 0.0) / MAX_HP) * getHitBoxOnScreen().width), 20);
		}
		g.setColor(oc);

	}

	public int pdmg;
	public float pspeed;
	public float pMaxRange;
	public BufferedImage pimg;
	public boolean isDead;

	public void setProjectileStats(int pdmg, float pspeed, float pMaxRange, BufferedImage pimg) {
		this.pdmg = pdmg;
		this.pspeed = pspeed;
		this.pMaxRange = pMaxRange;
		this.pimg = pimg;
	}

	public boolean canSeePlayer() {
		return getVecToPlayer().magnitude() <= visionRange;
	}

	public Vector getVecToPlayer() {
		return Vector.getVector(this.getCenterInMap(), player.getCenterInMap());
	}

	public Player player;
	
	/**
	 * for if i ever implement multiplayer which im probably not smart enough to do before 8 years
	 * @param player
	 */
	public void setPlayerReference(Player player) {
		this.player = player;
	}

	public void setMainColor(Color c) {
		mainColor = c;
	}

}
