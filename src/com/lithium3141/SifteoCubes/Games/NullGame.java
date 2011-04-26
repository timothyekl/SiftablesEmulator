package com.lithium3141.SifteoCubes.Games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.lithium3141.SifteoCubes.Cube;

public class NullGame implements Game {
	@Override
	public int getRequiredCubes() {
		return 0;
	}
	
	@Override
	public String getTitle() {
		return "";
	}
	
	@Override
	public void load() {}
	
	@Override
	public void unload() {}
	
	@Override
	public void renderCube(Cube cube, Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.BLACK);
		Dimension d = new Dimension(128, 128);
		g2.setFont(new Font("Courier", Font.BOLD, 12));
		DrawingUtils.drawCenteredString("SELECT GAME", d.width, d.height, g);
	}

	@Override
	public void cubesJoined(Cube c1, int e1, Cube c2, int e2) {}

	@Override
	public void cubesSeparated(Cube c1, int e1, Cube c2, int e2) {}

	@Override
	public void addedCube(Cube cube) {}

	@Override
	public void removedCube(Cube cube) {}
	
	@Override
	public void shookCube(Cube cube) {}
}
