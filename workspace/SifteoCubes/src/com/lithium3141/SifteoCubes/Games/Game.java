package com.lithium3141.SifteoCubes.Games;

import java.awt.Graphics;

import com.lithium3141.SifteoCubes.Cube;

public abstract class Game {
	public static final int LEFT_TO_RIGHT = 0;
	public static final int TOP_TO_BOTTOM = 0;
	
	public abstract int getRequiredCubes();
	public abstract String getTitle();
	
	public abstract void load();
	public abstract void unload();
	
	/**
	 * Draw the screen for the given Cube. The screen is fixed at 128x128 pixels.
	 * 
	 * @param cube The Cube for which to draw
	 * @param g The Graphics object to use when drawing
	 */
	public abstract void renderCube(Cube cube, Graphics g);
	
	/**
	 * Receive notification that two Cubes became adjacent.
	 * @param c1 The first Cube involved
	 * @param e1 The edge of the first Cube joined
	 * @param c2 The second Cube involved
	 * @param e2 The edge of the second Cube joined
	 */
	public abstract void cubesJoined(Cube c1, int e1, Cube c2, int e2);
	
	/**
	 * Receive notification that two Cubes are no longer adjacent.
	 * @param c1 The first Cube involved
	 * @param e1 The edge of the first Cube separated
	 * @param c2 The second Cube involved
	 * @param e2 The edge of the second Cube separated
	 */
	public abstract void cubesSeparated(Cube c1, int e1, Cube c2, int e2);
}
