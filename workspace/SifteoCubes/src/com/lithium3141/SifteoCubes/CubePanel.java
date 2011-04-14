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
	 * Call to indicate to the emulator framework that this cube's display panel
	 * needs to be repainted.
	 */
	public void needsRefresh() {
		this.displayPanel.repaint();
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
	
	/**
	 * Perform adjacency checking against all other active cubes. Send the
	 * appropriate notifications if adjacencies are made or broken.
	 */
	public void updateAdjacencies() {
		int OFFSET_FUZZ = 20;
		int DISTANCE_FUZZ = 30;
		
		Cube cube = this.getCube();
		for(Cube otherCube : Emulator.getCubes()) {
			if(otherCube != cube) {
				CubePanel panel = this;
				CubePanel otherPanel = otherCube.getPanel();
				
				// Check vertical alignment
				if(Math.abs(panel.getX() - otherPanel.getX()) < OFFSET_FUZZ && Math.abs(panel.getY() - otherPanel.getY()) < panel.getHeight() + DISTANCE_FUZZ) {
					// Aligned vertically
					System.out.println("Vertical alignment");
					
					if(panel.getY() < otherPanel.getY()) {
						// This panel is above the other panel
						Emulator.foundAdjacent(cube, cube.absoluteEdgeForRelative(Cube.EDGE_BOTTOM), otherCube, otherCube.absoluteEdgeForRelative(Cube.EDGE_TOP));
					} else {
						// This panel is below the other panel
						Emulator.foundAdjacent(cube, cube.absoluteEdgeForRelative(Cube.EDGE_TOP), otherCube, otherCube.absoluteEdgeForRelative(Cube.EDGE_BOTTOM));
					}
				} else {
					// Not aligned vertically
					
				}
				
				// Check horizontal alignment
				if(Math.abs(panel.getY() - otherPanel.getY()) < OFFSET_FUZZ && Math.abs(panel.getX() - otherPanel.getX()) < panel.getWidth() + DISTANCE_FUZZ) {
					// Aligned horizontally
					System.out.println("Horizontal alignment");
					
					if(panel.getX() < otherPanel.getX()) {
						// This panel is above the other panel
						Emulator.foundAdjacent(cube, cube.absoluteEdgeForRelative(Cube.EDGE_RIGHT), otherCube, otherCube.absoluteEdgeForRelative(Cube.EDGE_LEFT));
					} else {
						// This panel is below the other panel
						Emulator.foundAdjacent(cube, cube.absoluteEdgeForRelative(Cube.EDGE_LEFT), otherCube, otherCube.absoluteEdgeForRelative(Cube.EDGE_RIGHT));
					}
				} else {
					// Not aligned horizontally
					
				}
			}
		}
	}
	
	@Override
	public void setLocation(int x, int y) {
		super.setLocation(x, y);
		
		this.collisionDetect();
		this.updateAdjacencies();
	}
	
	@Override
	public void setLocation(Point p) {
		super.setLocation(p);
		
		this.collisionDetect();
		this.updateAdjacencies();
	}
	
	private class CubeDisplayPanel extends JPanel {
		private CubeDisplayPanel() {
			super();
			
			this.setLayout(null);
			this.setSize(128, 128);
			
			this.setBackground(Color.WHITE);
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Emulator.getActiveGame().renderCube(CubePanel.this.cube, g);
			Graphics2D g2 = (Graphics2D)g;
			g2.rotate(CubePanel.this.getCube().getRotationDegrees());
		}
	}
}
