package com.lithium3141.SifteoCubes.Games;

import java.awt.Color;
import java.awt.Graphics;

import com.lithium3141.SifteoCubes.Cube;

public class RGBStripeGame extends Game {

	@Override
	public int getRequiredCubes() {
		return 0;
	}

	@Override
	public String getTitle() {
		return "RGB Stripes";
	}

	@Override
	public void load() {}

	@Override
	public void unload() {}

	@Override
	public void renderCube(Cube cube, Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 128, 32);
		
		g.setColor(Color.GREEN);
		g.fillRect(0, 32, 128, 32);
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 64, 128, 32);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 96, 128, 32);
	}

	@Override
	public void cubesJoined(Cube c1, int e1, Cube c2, int e2) {}

	@Override
	public void cubesSeparated(Cube c1, int e1, Cube c2, int e2) {}

	@Override
	public void addedCube(Cube cube) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedCube(Cube cube) {
		// TODO Auto-generated method stub
		
	}

}
