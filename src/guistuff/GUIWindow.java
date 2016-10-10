package guistuff;

import java.awt.Color;
import java.awt.Graphics2D;

public class GUIWindow {
	
	public final int TRANSPARENCY = 127;
	public boolean isVisible;
	
	public void tick(double dt){
		if(isVisible){
			//handleinput
		}
	}
	public void render(Graphics2D g){
		if(isVisible){
		Color oc = g.getColor();
		g.draw3DRect(50, 50, 50, 50, true);
		g.setColor(oc);
		}
	}
}
