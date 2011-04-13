package com.lithium3141.SifteoCubes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

/**
 * Primary workspace for dealing with emulated cubes. Handles display of the
 * cubes themselves.
 * 
 * @author ekltl
 */
@SuppressWarnings("serial")
public class WorkspacePanel extends JPanel {
	private Map<Cube, CubePanel> cubePanelMap;
	
	public WorkspacePanel() {
		super();
		
		// TODO debugging; remove
		this.setBackground(Color.GREEN);
		
		// Instantiate tracking variables
		this.cubePanelMap = new HashMap<Cube, CubePanel>();
	}

	/**
	 * Notify the workspace that a new cube was added.
	 * @param c the Cube that was added.
	 */
	public void addedCube(Cube c) {
		CubePanel p = new CubePanel(c);
		this.cubePanelMap.put(c, p);
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("Painting workspace!");
		
		Graphics2D g2 = (Graphics2D)g;
		
		for(Cube cube : Emulator.getCubes()) {
			CubePanel panel = this.cubePanelMap.get(cube);
			g2.setColor(panel.getColor());
			g2.fillRect(panel.getX(), panel.getY(), panel.getSize().width, panel.getSize().height);
		}
	}
}
