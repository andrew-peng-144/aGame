package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import aGame.Loop;
import aGame.Main;
import ai.AI;
import ai.ChargerAI;
import ai.ShufflerAI;
import entities.Assets;
import entities.Enemy;
import entities.Entity;
import entities.Particle;

import entities.Player;
import entities.Projectile;
import inv.Weapon;
import utils.AUtils;
import utils.Vector;

public class Map {

	BufferedImage map;
	/**
	 * enemy spawn points
	 */
	BufferedImage esp;

	public Player p1;
	public int[][] blockMap;

	public Map(BufferedImage map, BufferedImage esp) {
		blockMap = new int[map.getWidth()][map.getHeight()];
		this.map = map;
		this.esp = esp;
		initWorld();
	}

	/**
	 * spawn player then blocks then enemies!
	 */
	public void initWorld() {
		BufferedImage p1img = Assets.player_1;
		p1img = AUtils.getScaledImage(p1img, 2);
		int w = p1img.getWidth();
		int h = p1img.getHeight();
		p1 = new Player(p1img, 0.5f, 100, new Vector(Loop.width / 2 - w / 2, Loop.height / 2 - h / 2));
		p1.setWeapon(Weapon.starter);
		// Main.km.setPlayerReference(p1);
		// Loop.mm.setPlayerReference(p1);

		for (int x = 0; x < map.getWidth(); x++) { // loop through all pixels of
													// map and add a block
			for (int y = 0; y < map.getHeight(); y++) {
				int rgb = map.getRGB(x, y); // getting RGB of all pixels
				System.out.println(rgb);
				switch (rgb & 0xFFFFFF) { // bitwise AND operator on white
				case 0xFF0000:
					Block.blocks.add(new Block(new Vector(x, y).scale(Block.blockSize), BlockType.RED_BRICK_1));
					// System.out.println("stone added");
					break;
				case 0xC0C0C0:
					Block.blocks.add(new Block(new Vector(x, y).scale(Block.blockSize), BlockType.FLOOR_1));
					break;
				case 0x808080:
					Block.blocks.add(new Block(new Vector(x, y).scale(Block.blockSize), BlockType.STONE_BRICK_1));
					// System.out.println("brick added");
					break;
				case 0x0026FF: // blue- player spawnpoint
					p1.setPositionInMap(x * Block.blockSize, y * Block.blockSize);
					Block.blocks.add(new Block(new Vector(x, y).scale(Block.blockSize), BlockType.PLAYER_SPAWN));
					break;
				// case 0xFF0000:
				// blockMap[x][y] = 1;
				// break;
				// case 0xC0C0C0:
				// blockMap[x][y] = 0;
				// break;
				// case 0x808080:
				// blockMap[x][y] = 1;
				// break;
				// case 0x0026FF:
				// blockMap[x][y] = 0;
				// break;
				// default:
				// blockMap[x][y] = -1;
				}
				// TODO flyweight-- optimization
			}
		}

		// now loop through all pixels in enemyspawnpoints and create an enemy
		// per coded pixel
		BufferedImage eimg;
		BufferedImage pimg;
		Enemy e;
		for (int x = 0; x < esp.getWidth(); x++) {
			for (int y = 0; y < esp.getHeight(); y++) {
				int rgb = esp.getRGB(x, y);
				switch (rgb & 0xFFFFFF) {
				case 0x00C116: // charger spawn point
					System.out.println("CHARGERSPAWNED");
					eimg = AUtils.getScaledImage(Assets.charger_1, 4);
					pimg = AUtils.getScaledImage(Assets.chargerproj_1, 3);
					e = new Enemy(eimg, .3f, 500f, 40, new Vector(x, y).scale(Block.blockSize), p1,
							new ChargerAI(1000, 500, 20, 200));
					e.setProjectileStats(15, .5f, 700f, pimg);
					Enemy.enemies.add(e);
					break;
				case 0x00FFFF: // shuffler spawn point
					System.out.println("SHUFFLERSPAWNED");
					eimg = AUtils.getScaledImage(Assets.shuffler_1, 4);
					pimg = AUtils.getScaledImage(Assets.shufflerproj_1, 3);
					e = new Enemy(eimg, .25f, 600f, 50, new Vector(x, y).scale(Block.blockSize), p1,
							new ShufflerAI(200, 1300));
					e.setProjectileStats(15, .5f, 700f, pimg);
					Enemy.enemies.add(e);
					break;

				}
			}
		}
	}

	public void tick(double dt) {
		{
			Iterator<Enemy> it = Enemy.enemies.iterator();
			while (it.hasNext())
				if (it.next().shouldBeRemoved)
					it.remove();
		}
		{
			Iterator<Projectile> it = Projectile.projs.iterator();
			while (it.hasNext())
				if (it.next().shouldBeRemoved)
					it.remove();
		}
		{
			Iterator<Particle> it = Particle.particles.iterator();
			while (it.hasNext())
				if (it.next().shouldBeRemoved)
					it.remove();
		}

		for (Block b : Block.blocks)
			b.tick(dt);
		for (Enemy e : Enemy.enemies)
			e.tick(dt);
		for (Projectile p : Projectile.projs)
			p.tick(dt);
		for (Particle p : Particle.particles)
			p.tick(dt);

		p1.tick(dt);

	}

	public void render(Graphics2D g) {

		for (Block b : Block.blocks)
			b.render(g);
		for (Enemy e : Enemy.enemies)
			if (e.visible)
				e.render(g);
		for (Projectile p : Projectile.projs)
			if (p.visible)
				p.render(g);
		for (Particle p : Particle.particles)
			p.render(g);

		p1.render(g);

	}
}
