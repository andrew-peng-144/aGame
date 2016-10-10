package utils;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
/**
 * AGAME
 * @author anrewd
 *
 */
public class AUtils {

	public static BufferedImage loadImageFrom(Class<?> classFile, String path) {
		
		BufferedImage img = null;
		
		try{
			img = ImageIO.read(classFile.getResourceAsStream(path));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return img;
	}

	
	public static BufferedImage getResizedImage(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		// acts as the buffer to draw the Image onto
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
	public static BufferedImage getScaledImage(BufferedImage img, int scale) {
//		int newW = img.getWidth()*scale;
//		int newH = img.getHeight()*scale;
//		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
//		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
//		// acts as the buffer to draw the Image onto
//		Graphics2D g2d = dimg.createGraphics();
//		g2d.drawImage(tmp, 0, 0, null);
//		g2d.dispose();
//
//		return dimg;
		return getResizedImage(img, img.getWidth()*scale, img.getHeight()*scale);
	}

	/**
	 * ONLY WORKS FOR SQUARE IMAGES -- OR ELSE IT WILL BE CUT OFF.
	 * 
	 * @param img
	 * @param theta
	 * @return
	 */
	public static BufferedImage getRotatedImage(BufferedImage img, double theta) {
		AffineTransform at = new AffineTransform();
		at.rotate(theta, img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img, null);
	}

	public static int getStringWidth(String str, Graphics2D g) {
		FontMetrics fm = g.getFontMetrics();
		return fm.stringWidth(str);
	}

	public static BufferedImage replaceColor(BufferedImage image, int prevColor, int finalColor) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage newImg = new BufferedImage(width, height, image.getType());
		int color;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				color = image.getRGB(i, j);
				if (color == prevColor) {
					newImg.setRGB(i, j, finalColor);
				} else {
					newImg.setRGB(i, j, color);
				}
			}
		}

		return newImg;
	}

}
