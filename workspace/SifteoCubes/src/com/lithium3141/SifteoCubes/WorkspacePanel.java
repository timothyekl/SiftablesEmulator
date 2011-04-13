package com.lithium3141.SifteoCubes;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
		this.setLayout(null);
		
		// Instantiate tracking variables
		this.cubePanelMap = new HashMap<Cube, CubePanel>();
		
		// Listen for mouse motion
		WorkspacePanelMouseListener l = new WorkspacePanelMouseListener();
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
	}

	/**
	 * Notify the workspace that a new cube was added.
	 * @param c the Cube that was added.
	 */
	public void addedCube(Cube c) {
		CubePanel p = new CubePanel(c);
		this.cubePanelMap.put(c, p);
		this.add(p);
		this.repaint();
	}
	
	private class WorkspacePanelMouseListener implements MouseListener, MouseMotionListener {
		private Point anchorPoint;
		private CubePanel draggingPanel;

		@Override
		public void mousePressed(MouseEvent event) {
			System.out.println("Mouse pressed at point: " + event.getPoint());
			
			for(CubePanel p : cubePanelMap.values()) {
				if(p.contains(SwingUtilities.convertPoint(WorkspacePanel.this, event.getPoint(), p))) {
					System.out.println("\tFound dragging panel: " + p);
					this.draggingPanel = p;
					this.anchorPoint = SwingUtilities.convertPoint(WorkspacePanel.this, event.getPoint(), p);
					System.out.println("\tUsing anchor point: " + this.anchorPoint);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			this.draggingPanel = null;
			this.anchorPoint = null;
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			System.out.println("Mouse dragged at point: (" + event.getX() + "," + event.getY() + ")");
			if(this.draggingPanel != null) {
				System.out.println("\tDragging panel: " + this.draggingPanel);
				this.draggingPanel.setLocation(event.getX() - this.anchorPoint.x, event.getY() - this.anchorPoint.y);
				WorkspacePanel.this.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
}
