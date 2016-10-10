package aGame;

import java.awt.Checkbox;
import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

import gamestates.GameState;
import gamestates.InGameState;
import gamestates.MenuState;
import gamestates.PausedState;

/**
 * utility class to manage gamestates, notably by stacking them & doing stuff
 * with the top of the stack.
 * 
 * @author asdf
 *
 */
public class GSManager {
	public static Deque<GameState> states = new ArrayDeque<>();

	private static GameState currentState;

	private GSManager() {

	}

	public static void tickState(double dt) {
		currentState = states.peek();

		currentState.tick(dt);
	}

	public static void renderState(Graphics2D g) {
		currentState.render(g);
	}

	public static void pushState(GameState gs) {
		states.push(gs);
	}

	public static void popCurrentState() {
		states.pop();
	}

	public static GameState getCurrentState() {
		return currentState;
	}

	// private static void checkStateChange(){
	// if(PausedState.pressedPause)
	// pushState(new PausedState());
	// }
}
