package guistuff;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import aGame.GSManager;
import entities.Assets;
import gamestates.GameState;
import utils.AUtils;
@Deprecated
public class GSButton //extends ClickButton
{

	// public GSButton(Command cmd, BufferedImage defaultImg, BufferedImage
	// pressedImg, BufferedImage hoveredImg, int x,
	// int y) {
	// super(cmd, defaultImg, pressedImg, hoveredImg, x, y);
	// // TODO Auto-generated constructor stub
	// }
//	GameState gs;
//	String name;
//
//	public GSButton(GameState gs, String name, int x, int y) {
//		super(AUtils.getScaledImage(Assets.menuBtnDefault, 3), AUtils.getScaledImage(Assets.menuBtnPressed, 3),
//				AUtils.getScaledImage(Assets.menuBtnHovered, 3), x, y);
//		this.gs = gs;
//		this.name = name;
//	}
//
//	public GSButton(GameState gs, String name, BufferedImage defaultImg, BufferedImage pressedImg,
//			BufferedImage hoveredImg, int x, int y) {
//		super(defaultImg, pressedImg, hoveredImg, x, y);
//		this.gs = gs;
//		this.name = name;
//	}
//	
//	@Override
//	public void render(Graphics2D g){
//		super.render(g);
//		int sw = AUtils.getStringWidth(name, g);
//		g.drawString(name, this.x + sw/2, this.y + this.getMouseZone().height/2);
//	}
//	
//	@Override
//	public void exec() {
//		GSManager.pushState(gs);
//	}

}
