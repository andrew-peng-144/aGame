package guistuff;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import aGame.InputHandler;
import cmd.Command;
import entities.Assets;
import utils.AUtils;

public class ClickButton {

	// public static ArrayList<ClickButton> cbtns = new ArrayList<>();
	//
	// public ButtonState btnstate;
	private BufferedImage currImg;
	private BufferedImage defaultImg, pressedImg, hoveredImg;
	/**
	 * the x and y values for where this button is located ON THE SCREEN.
	 * buttons shouldnt ever move lol
	 */
	public final int x, y;

	private String msg;
	private Font msgFont;

	public ClickButton(Command cmd, String imgPack, String msg, Font font, int x, int y) {
		this.x = x;
		this.y = y;
		this.cmd = cmd;
		this.msg = msg;
		this.msgFont = font;
		if (imgPack.equals("menu")) {
			System.out.println("MENUIMAGEPACK");
			this.defaultImg = AUtils.getScaledImage(Assets.menuBtnDefault, 3);
			this.pressedImg = AUtils.getScaledImage(Assets.menuBtnPressed, 3);
			this.hoveredImg = AUtils.getScaledImage(Assets.menuBtnHovered, 3);
		} else {
			System.err.println("CANT FIND DIS IMG PACK NOOB");
			defaultImg = Assets.missing;
			pressedImg = Assets.missing;
			hoveredImg = Assets.missing;

		}
		this.currImg = defaultImg;
	}

	/**
	 * construct a clickable button giving only one image parameter
	 */
	public ClickButton(Command cmd, BufferedImage defaultImg, String msg, Font font, int x, int y) {
		this.cmd = cmd;
		this.defaultImg = defaultImg;
		this.pressedImg = defaultImg;
		this.hoveredImg = defaultImg;
		this.currImg = defaultImg;
		// btnstate = ButtonState.DEFAULT;
		this.x = x;
		this.y = y;
		this.msg = msg;
		this.msgFont = font;
	}

	/**
	 * construct a clickable button while giving all three images represengin
	 * button state
	 */
	public ClickButton(Command cmd, BufferedImage defaultImg, BufferedImage pressedImg, BufferedImage hoveredImg,
			String msg, Font font, int x, int y) {
		this.cmd = cmd;
		this.defaultImg = defaultImg;
		this.pressedImg = pressedImg;
		this.hoveredImg = hoveredImg;
		this.currImg = defaultImg;
		// btnstate = ButtonState.DEFAULT;
		this.x = x;
		this.y = y;
		this.msg = msg;
		this.msgFont = font;

	}

	public void setMessageFont(Font font) {
		this.msgFont = font;
	}

	// public BufferedImage getPressedImg() {
	// return pressedImg;
	// }
	public void setPressedImg(BufferedImage pressedImg) {
		this.pressedImg = pressedImg;
	}

	// public BufferedImage getHoveredImg() {
	// return hoveredImg;
	// }
	public void setHoveredImg(BufferedImage hoveredImg) {
		this.hoveredImg = hoveredImg;
	}

	public Command cmd;

	public boolean hasClicked;

	public void tick(double dt) {

		// handle imagez
		if (this.getMouseZone().contains(InputHandler.getMousePosOnScreen())) {
			if (InputHandler.leftClick) {
				currImg = pressedImg;
				hasClicked = true;
			} else { // if stopped pressing down
				if (hasClicked) {
					cmd.exec(dt);
					hasClicked = false;
				}
				currImg = hoveredImg;
			}
		} else {
			currImg = defaultImg;
		}
	}

	public void render(Graphics2D g) {
		Font of = g.getFont();
		g.setFont(this.msgFont);
		g.drawImage(currImg, x, y, null);
		// draw message CENTERED
		Rectangle mz = this.getMouseZone();
		int msgW = AUtils.getStringWidth(msg, g);
		g.drawString(msg, (int) (mz.x + mz.getWidth() / 2 - msgW / 2), (int) (mz.y + mz.getHeight() / 2));

		g.setFont(of);
	}

	/**
	 * 
	 * @return the rectangle for click detection
	 */
	public Rectangle getMouseZone() {
		return new Rectangle(x, y, currImg.getWidth(), currImg.getHeight());
	}

}
