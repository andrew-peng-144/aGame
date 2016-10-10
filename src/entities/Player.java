package entities;

import java.util.ArrayList;
import javax.swing.*;

import aGame.InputHandler;
import aGame.Loop;
import aGame.Main;
import inv.Weapon;
import utils.AUtils;
import utils.Vector;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class Player extends Entity {

	public static Vector cameraPos = new Vector();
	public int hp, maxhp;

	// public Animation moveDown, moveUp, moveLeft, moveRight;
	public Animation[] anims;
	public int currentAnimation;
	public boolean isDead;

	// public int pdmg;
	//
	// public float pspeed;
	//
	// public float pMaxRange;
	//
	// public BufferedImage pimg;

	// public boolean isShooting;

	public Player(BufferedImage img, float speed, int hp, Vector posInMap) {
		super(img, speed, Team.RED, posInMap);
		this.hp = hp;
		this.maxhp = hp;

		// now fill BI arraylists with animation frames and create animations
		// for all movement directions...
		// store all movement animations in one animation array
		anims = new Animation[4];

		double mvmt = 50 / speed; // movement animation delay
		for (int i = 0; i < anims.length; i++) {
			ArrayList<BufferedImage> imgs = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				imgs.add(AUtils.getScaledImage(Assets.player.getSprite(j, i),3));
			}
			anims[i] = new Animation(imgs, mvmt, true);
			// IMPORTANT: arraylist is pass by value so must create new
			// arraylist at beginning of outer loop to make new pointer
		}

	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	@Override
	public void tick(double dt) {
		move(dt);

		if (InputHandler.n1) // TODO switch out weapons
			setWeapon(Weapon.starter);
		else if (InputHandler.n2)
			setWeapon(Weapon.melee1);
		else if (InputHandler.n3) {
			setWeapon(Weapon.laser);
		}
		if (weapon != null)
			weapon.tick(dt, InputHandler.leftClick, this);
		super.tick(dt);

		cameraPos = posInMap.addTo(
				new Vector(-Loop.width / 2 + this.img.getWidth() / 2, -Loop.height / 2 + this.img.getHeight() / 2));
				// assumes player always in middle

		// TODO move the mouse, move the camera 1/2 that distance from mouse to
		// center of screen
		// TODO all mouse listening is on one thread i think so there has to be
		// a way to click & move mouse at same time

		Projectile.checkCollisionWithProjectiles(this, dt);

		if (InputHandler.s)
			currentAnimation = 0;
		if (InputHandler.d)
			currentAnimation = 1;
		if (InputHandler.w)
			currentAnimation = 2;
		if (InputHandler.a)
			currentAnimation = 3;
		if (!InputHandler.s || dir.isZero())
			anims[0].stop();
		if (!InputHandler.d || dir.isZero())
			anims[1].stop();
		if (!InputHandler.w || dir.isZero())
			anims[2].stop();
		if (!InputHandler.a || dir.isZero())
			anims[3].stop();

		if (!anims[currentAnimation].playing)
			anims[currentAnimation].start();
		img = anims[currentAnimation].getCurrentImage();
		anims[currentAnimation].tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
		Color oc = g.getColor();
		if (weapon != null)
			weapon.render(g);
		super.render(g);
		// draw hp bar
		g.setColor(new Color(0, 255, 0, 200));
		g.fillRect(50, 50, (int) (((hp + 0.0) / maxhp) * 300), 40);
		g.setColor(Color.white);
		g.drawRect(50, 50, 300, 40);
		g.drawString("HP: " + hp + "/" + maxhp, 50, 50);
		g.drawString("CURRENT WEAPON: " + weapon.name, 100, 100);
		// g.drawString("downanimsize" + anims[0].imgs.size(), 500, 500);
		// g.drawString("rightanimsize" + anims[1].imgs.size(), 500, 520);
		// g.drawString("upanimsize" + anims[2].imgs.size(), 500, 540);
		// g.drawString("leftanimsize" + anims[3].imgs.size(), 500, 560);
		g.setColor(oc);
	}

	// public double pShootRate;
	// public double tcShoot;
	public Weapon weapon;

	private void move(double dt) {

		dir.setPos(0, 0);
		int deg = 0;

		if (InputHandler.w && InputHandler.a)
			deg = -135;
		else if (InputHandler.w && InputHandler.d)
			deg = -45;
		else if (InputHandler.s && InputHandler.a)
			deg = 135;
		else if (InputHandler.s && InputHandler.d)
			deg = 45;
		else if (InputHandler.w)
			deg = -90;
		else if (InputHandler.s)
			deg = 90;
		else if (InputHandler.a)
			deg = 180;
		else if (InputHandler.d)
			deg = 0;

		if (InputHandler.w && InputHandler.s || InputHandler.a && InputHandler.d) {
		} else if (InputHandler.w || InputHandler.s || InputHandler.a || InputHandler.d)
			dir = new Vector(new Double(speed), new Double(Math.toRadians(deg)));

	}

}
