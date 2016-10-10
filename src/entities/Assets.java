package entities;

import java.awt.image.BufferedImage;

import aGame.Main;
import utils.AUtils;
import world.SpriteSheet;

public class Assets {
	static SpriteSheet enemies, player, projs, blocks, guistuff;
	/**
	 * entity asset
	 */
	public static BufferedImage player_1, shuffler_1, playerproj_1, shufflerproj_1, melee_1, laser_1, chargerproj_1,
			charger_1;

	/**
	 * block asset
	 */
	public static BufferedImage redbrick1, floor1, playerSpawn, stonebrick1;

	/**
	 * button
	 */
	public static BufferedImage menuBtnDefault, menuBtnHovered, menuBtnPressed;

	/**
	 * maps & esps
	 */
	public static BufferedImage map1, esp1;

	public static BufferedImage missing;

	public static void initSprites() {
		System.out.println("start initsprites");
		enemies = new SpriteSheet(AUtils.loadImageFrom(Assets.class, "/imgs/enemysheet.png"), 16, 16);
		player = new SpriteSheet(AUtils.loadImageFrom(Assets.class, "/imgs/playerSheet.png"), 16, 16);
		projs = new SpriteSheet(AUtils.loadImageFrom(Assets.class, "/imgs/projectilesheet.png"), 16, 16);
		guistuff = new SpriteSheet(AUtils.loadImageFrom(Assets.class, "/imgs/guistuff.png"), 16, 16);

		// init entities
		player_1 = player.getSprite(0, 0);
		shuffler_1 = enemies.getSprite(0, 0);
		playerproj_1 = projs.getSprite(0, 0);
		shufflerproj_1 = projs.getSprite(1, 0);
		melee_1 = projs.getSprite(0, 1);
		laser_1 = projs.getSprite(2, 2);
		chargerproj_1 = projs.getSprite(2, 0);
		charger_1 = enemies.getSprite(0, 1);
		blocks = new SpriteSheet(AUtils.loadImageFrom(Assets.class, "/imgs/blocks.png"), 16, 16);

		// init blocks
		redbrick1 = blocks.getSprite(0, 0);
		stonebrick1 = blocks.getSprite(1, 0);
		floor1 = blocks.getSprite(2, 0);
		playerSpawn = blocks.getSprite(3, 0);

		// init buttons
		menuBtnDefault = guistuff.getMultiTileSprite(0, 0, 2, 1);
		menuBtnHovered = guistuff.getMultiTileSprite(2, 0, 2, 1);
		menuBtnPressed = guistuff.getMultiTileSprite(4, 0, 2, 1);

		// init maps & esps
		map1 = AUtils.loadImageFrom(Assets.class, "/imgs/map.png");
		esp1 = AUtils.loadImageFrom(Assets.class, "/imgs/enemyspawnpoints.png");

		System.out.println("end initsprites");
	}
}
