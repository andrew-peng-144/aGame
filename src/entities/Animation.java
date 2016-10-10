package entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	public ArrayList<BufferedImage> imgs = new ArrayList<>();
	public double delay;
	public boolean playing;
	public double timeCounter;
	public int currentFrame;

	public boolean looped;

	public Animation(ArrayList<BufferedImage> imgs, double delay, boolean looped) {
		this.imgs = imgs;
		this.delay = delay;
		this.looped = looped;
	}

	public void start() {
		playing = true;
		currentFrame = 0;
		timeCounter = 0;
	}

	public void stop() {
		playing = false;
		currentFrame = 0;
		timeCounter = 0;
	}
	
	public void pause(){
		playing = false;
	}
	public void resume(){
		playing = true;
	}

	public BufferedImage getCurrentImage() {
		return imgs.get(currentFrame);
	}

	public void nextFrame() {
		System.out.println(this);
		currentFrame++;
		if (currentFrame >= imgs.size()) {
			if (looped)
				currentFrame = 0;
			else
				stop();
		}
	}

	public void tick(double dt) {
		if(playing){
			timeCounter += dt;
			if(timeCounter >= delay){
				timeCounter -= delay;
				nextFrame();
			}
		} else {
			
		}
	}

	@Override
	public String toString() {
		return "Animation [delay=" + delay + ", playing=" + playing + ", timeCounter=" + timeCounter + ", currentFrame="
				+ currentFrame + ", looped=" + looped + "]";
	}

}
