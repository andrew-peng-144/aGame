package gamestates;

import java.awt.Graphics2D;

import aGame.GSManager;
import aGame.InputHandler;
import world.Map;

public class PausedState extends GameState {

	private Map map;

	public PausedState(Map map) {
		this.map = map;
	}

	public double pausedTime;

	@Override
	public void tick(double dt) {
		// TODO Auto-generated method stub
		pausedTime += dt;
		if (InputHandler.esc)
			GSManager.popCurrentState();
		new String(new String(new String(new String(new String(new String(new String()))))));
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		map.render(g);
	}

}
