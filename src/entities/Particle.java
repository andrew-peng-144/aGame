package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import utils.Vector;

/**
 * almost an entity... represents a particle that flies off in a random
 * direction, very similar to projectile purely aesthetic
 * 
 * make it an entity later lel
 * 
 * @author asdf
 *
 */
public class Particle {
	public Particle(Color color, int size, float speed, double theta, double maxDist, Vector posInMap) {
		this.color = color;
		this.size = size;
		this.speed = speed;
		this.maxDist = maxDist;
		this.posInMap = posInMap;
		pos = Vector.getVector(Player.cameraPos, posInMap);
		dir = new Vector(new Double(speed), new Double(theta));
	}

	Color color;
	int size;
	float speed;
	double maxDist;
	double distTraveled;
	Vector posInMap;
	Vector pos;
	Vector dir;
	public boolean shouldBeRemoved;

	public static List<Particle> particles = new ArrayList<>();

	public void tick(double dt) {
		distTraveled += dir.scale(dt).magnitude();
		if (distTraveled >= maxDist) {
			shouldBeRemoved = true;
		}

		posInMap = posInMap.addTo(dir.scale(dt));
		pos = Vector.getVector(Player.cameraPos, posInMap);

	}

	public void render(Graphics2D g) {
		Color oc = g.getColor();

		g.setColor(color);
		System.out.println("TAIES");
		g.fillRect((int) pos.x, (int) pos.y, size, size);

		g.setColor(oc);
	}
}
