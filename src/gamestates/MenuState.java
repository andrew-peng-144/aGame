package gamestates;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import aGame.GSManager;
import aGame.Loop;
import entities.Assets;
import guistuff.ClickButton;
import guistuff.GSButton;
import utils.AUtils;
import world.Map;

public class MenuState extends GameState {

	BufferedImage menuBkgd;

	ClickButton playBtn;
	ClickButton quitBtn;

	public MenuState() {

		menuBkgd = AUtils.getResizedImage(AUtils.loadImageFrom(getClass(), "/imgs/menubkgd.png"), Loop.width,
				Loop.height);
		playBtn = new ClickButton((double d) -> GSManager.pushState(new InGameState(new Map(Assets.map1, Assets.esp1))),
				"menu", "PLAY", new Font(Font.SANS_SERIF, Font.BOLD, 15), 400, 400);
		quitBtn = new ClickButton((double d) -> System.exit(0), "menu", "QUIT",
				new Font(Font.SANS_SERIF, Font.BOLD, 15), 500, 500);
		// ClickButton.cbtns.add(playBtn);
	}

	@Override
	public void tick(double dt) {
		// TODO Auto-generated method stub
		playBtn.tick(dt);
		quitBtn.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(menuBkgd, 0, 0, null);
		playBtn.render(g);
		quitBtn.render(g);
	}

}
