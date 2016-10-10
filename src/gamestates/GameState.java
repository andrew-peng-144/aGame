package gamestates;

import java.awt.Graphics2D;

public abstract class GameState {

	// rip
	public abstract void tick(double dt);

	public abstract void render(Graphics2D g);
}
