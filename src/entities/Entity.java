package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utils.Vector;
import world.Block;

/**
 * all moving AND destructible things
 */
public abstract class Entity {
	// public static ArrayList<Entity> entities= new ArrayList<>();
	public Team team;
	/**
	 * TODO change this to "disp" for displacement vector.
	 */
	public Vector dir;

	public Vector posInMap;
	public BufferedImage img;
	/** default to TRUE */
	public boolean visible = true;
	/** default to false */
	public boolean shouldBeRemoved;
	// public Vector pos;
	public float speed;

	/**
	 * for acceleration later.
	 */
	public final float MAX_SPEED = 0;

	public Entity(BufferedImage img, float speed, Team team, Vector posInMap) {

		this.img = img;
		this.posInMap = posInMap;
		this.team = team;
		this.speed = speed;

		dir = new Vector();
	}

	public void tick(double dt) {

		posInMap = posInMap.addTo(dir.scale(dt));

		checkCollisionWithBlocks(dt);
	}

	private void checkCollisionWithBlocks(double dt) {
		for (Block b : Block.blocks)
			if (b.isSolid) {
				if (this.getHitBoxInMap().intersects(b.getHitBoxInMap())
						|| b.getHitBoxInMap().intersects(this.getHitBoxInMap())) {
					System.out.println("COLLIDE!: " + this);
					if (this instanceof Projectile) {
						shouldBeRemoved = true;
					} else {
						System.out.println(getDirectionWRTblock(b));
						// if entity is above block, move entity up
						double theta = 0;
						Rectangle bhb = b.getHitBoxInMap();
						switch (getDirectionWRTblock(b)) {
						case 0:
							posInMap.x = bhb.getMaxX() + 2;
							theta = 0;
							break;
						case 1:
							posInMap.y = bhb.getMaxY() + 2;
							theta = Math.PI / 2;
							break;
						case 2:
							posInMap.x = bhb.x - this.getHitBoxInMap().getWidth() - 2;
							theta = Math.PI;
							break;
						case 3:
							posInMap.y = bhb.y - this.getHitBoxInMap().getHeight() - 2;
							theta = -Math.PI / 2;
							break;
						default:
						}
						// dir.setRTheta(speed, theta);
						// posInMap = posInMap.addTo(dir.scale(dt));
					}
				}
			}
	}

	/**
	 * 
	 * @param b
	 *            TEH BLOQUE
	 * @return 0 is right, 1 is down, 2 is left, 3 is up
	 */
	private int getDirectionWRTblock(Block b) {
		double ang = Vector.getVector(b.getCenterInMap(), this.getCenterInMap()).getAngle();
		ang = Math.toDegrees(ang);
		System.out.println(ang);
		if (-45 <= ang && ang < 45) {
			return 0;
		} else if (45 <= ang && ang < 135) {
			return 1;
		} else if (135 <= ang && ang < 180 || -180 <= ang && ang < -135) {
			return 2;
		} else {
			return 3;
		}
	}

	public void render(Graphics2D g) {
		Color oc = g.getColor();
		g.drawImage(img, (int) getPosOnScreen().x, (int) getPosOnScreen().y, null);

		// g.draw(getHitBox());

		// draw hp bar

	}

	public void setPositionInMap(double x, double y) {
		posInMap.setPos(x, y);
	}

	public void stop() {
		dir.setPos(0, 0);
	}

	public Rectangle getHitBoxInMap() {
		return new Rectangle((int) posInMap.x, (int) posInMap.y, img.getWidth(), img.getHeight());
	}

	public Rectangle getHitBoxOnScreen() {
		return new Rectangle((int) getPosOnScreen().x, (int) getPosOnScreen().y, img.getWidth(), img.getHeight());
	}

	public Point getCenterInMap() {
		return new Point((int) posInMap.x + img.getWidth() / 2, (int) posInMap.y + img.getHeight() / 2);
	}

	public Point getCenterOnScreen() {
		return new Point((int) getPosOnScreen().x + img.getWidth() / 2, (int) getPosOnScreen().y + img.getHeight() / 2);
	}

	public Vector getPosOnScreen() {
		return Vector.getVector(Player.cameraPos, posInMap);
	}

}
