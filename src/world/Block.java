package world;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Assets;
import entities.Player;
import utils.AUtils;
import utils.Vector;

public class Block {

	public static ArrayList<Block> blocks = new ArrayList<>();
	public static int blockSize = 48;

	public BlockType type;
	public BufferedImage img;
	public Vector posInMap;
	public boolean isSolid;

	public boolean detects;

	public boolean destructible;

	//TODO flyweight
	public static final Block[] BLOCKS = {new Block(null, BlockType.FLOOR_1), new Block(null, BlockType.STONE_BRICK_1)};

	public Block(Vector posInMap, BlockType type) {
		this.posInMap = posInMap;
		this.type = type;


		switch (this.type) {
		case RED_BRICK_1:
			img = Assets.redbrick1;
			isSolid = true;
			break;
		case STONE_BRICK_1:
			img = Assets.stonebrick1;
			isSolid = true;
			break;
		case FLOOR_1:
			img = Assets.floor1;
			break;
		case PLAYER_SPAWN:
			img = Assets.playerSpawn;
			break;
		default:
			img = Assets.missing;
			break;
		}
		if (img != null)
			img = AUtils.getResizedImage(img, blockSize, blockSize);
		// block is now 48x48

	}

	public void tick(double dt) {

		

	}
	public Vector getPositionOnScreen(){
		return Vector.getVector(Player.cameraPos, posInMap);
	}
	public void render(Graphics2D g) {
		g.drawImage(img, (int) getPositionOnScreen().x, (int) getPositionOnScreen().y, null);

		if (isSolid) {
			// g.draw(getHitBox());
		}
	}

	public Rectangle getHitBoxInMap() {
		return new Rectangle((int) posInMap.x, (int) posInMap.y, img.getWidth(), img.getHeight());
	}


	public Point getCenterInMap() {
		return new Point((int) posInMap.x + img.getWidth() / 2, (int) posInMap.y + img.getHeight() / 2);
	}
}
