package com.lithium3141.SifteoCubes.Games;

import java.awt.Color;
import java.awt.Graphics;

import com.lithium3141.SifteoCubes.Cube;

public class ColorBlockGame implements Game {

	@Override
	public int getRequiredCubes() {
		return 0;
	}

	@Override
	public String getTitle() {
		return "Static Color Block";
	}

	@Override
	public void load() {

	}

	@Override
	public void unload() {

	}

	@Override
	public void renderCube(Cube cube, Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(-64, -64, 256, 256);
		
		g.setColor(Color.RED);
		g.fillOval(60, 60, 8, 8);
	}

	@Override
	public void cubesJoined(Cube c1, int e1, Cube c2, int e2) {

	}

	@Override
	public void cubesSeparated(Cube c1, int e1, Cube c2, int e2) {

	}

	@Override
	public void addedCube(Cube cube) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedCube(Cube cube) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void shookCube(Cube cube) {}

}
