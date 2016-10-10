package world;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	BufferedImage img;
	int spriteW, spriteH;

	public SpriteSheet(BufferedImage img, int spriteW, int spriteH) {
		this.img = img;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
	}

	public BufferedImage getSprite(int tileX, int tileY) {
		return img.getSubimage(tileX * spriteW, tileY * spriteH, spriteW, spriteH);
	}
	public BufferedImage getMultiTileSprite(int tileX, int tileY, int numTilesX, int numTilesY){
		return img.getSubimage(tileX * spriteW, tileY * spriteH, spriteW*numTilesX, spriteH*numTilesY);
	}

}