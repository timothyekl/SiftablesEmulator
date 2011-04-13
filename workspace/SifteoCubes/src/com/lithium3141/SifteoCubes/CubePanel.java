package com.lithium3141.SifteoCubes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CubePanel extends JPanel {
	private Cube cube;
	private CubeDisplayPanel displayPanel;
	
	public CubePanel(Cube c) {
		super();
		
		this.setSize(192, 192);
		this.setLayout(null);
		this.setBackground(new Color(0, 0, 0, 0));
		
		this.cube = c;
		this.cube.setPanel(this);
		this.displayPanel = new CubeDisplayPanel();
		
		this.displayPanel.setLocation(32, 32);
		this.add(this.displayPanel);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(4));
		g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 12, 12);
		
		g2.setColor(Color.BLACK);
		g2.fillRoundRect(6, 6, this.getWidth() - 12, this.getHeight() - 12, 12, 12);
		
		super.paint(g);
	}

	public Cube getCube() {
		return this.cube;
	}
	
	/**
	 * Perform collision detection against all other active cubes. Move them if
	 * a collision is found.
	 */
	public void collisionDetect() {
		for(Cube c : Emulator.getCubes()) {
			CubePanel p = c.getPanel();
			if(this != p && this.getBounds().intersects(p.getBounds())) {
				Rectangle intersection = this.getBounds().intersection(p.getBounds());
				if(intersection.width < intersection.height) {
					// Move in the x direction
					if(this.getX() > p.getX()) {
						p.setLocation(p.getX() - (intersection.width), p.getY());
					} else {
						p.setLocation(p.getX() + (intersection.width), p.getY());
					}
				} else {
					// Move in the y direction
					if(this.getY() > p.getY()) {
						p.setLocation(p.getX(), p.getY() - (intersection.height));
					} else {
						p.setLocation(p.getX(), p.getY() + (intersection.height));
					}
				}
			}
		}
	}
	
	@Override
	public void setLocation(int x, int y) {
		super.setLocation(x, y);
		
		this.collisionDetect();
	}
	
	@Override
	public void setLocation(Point p) {
		super.setLocation(p);
		
		this.collisionDetect();
	}
	
	private class CubeDisplayPanel extends JPanel {
		private CubeDisplayPanel() {
			super();
			
			this.setLayout(null);
			this.setSize(128, 128);
			
			this.setBackground(Color.RED);
		}
	}
}
