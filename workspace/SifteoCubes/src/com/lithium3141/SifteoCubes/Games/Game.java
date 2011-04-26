package com.lithium3141.SifteoCubes.Games;

import java.awt.FontMetrics;
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
	
	/**
	 * Add a new Cube to the game.
	 * @param cube the Cube to add
	 */
	public abstract void addedCube(Cube cube);
	
	/**
	 * Remove a Cube from the game.
	 * @param cube the Cube to remove
	 */
	public abstract void removedCube(Cube cube);
	

	/**
	 * Draw a string of text centered in the given Graphics object.
	 * @param s the String to draw
	 * @param w the width of the Graphics available
	 * @param h the height of the Graphics available
	 * @param g the Graphics object on which to draw
	 */
	public void drawCenteredString(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	}
}
