package com.lithium3141.SifteoCubes.Games;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class DrawingUtils {
	/**
	 * Draw a string of text centered in the given Graphics object.
	 * @param s the String to draw
	 * @param w the width of the Graphics available
	 * @param h the height of the Graphics available
	 * @param g the Graphics object on which to draw
	 */
	public static void drawCenteredString(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	}
}
