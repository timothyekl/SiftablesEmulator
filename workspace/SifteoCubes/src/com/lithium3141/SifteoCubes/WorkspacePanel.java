package com.lithium3141.SifteoCubes;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
	
	public WorkspacePanel() {
		super();
		
		this.setLayout(null);
		
		// Listen for mouse motion
		WorkspacePanelMouseListener l = new WorkspacePanelMouseListener();
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
	}

	/**
	 * Notify the workspace that a new Cube was added.
	 * @param cube the Cube that was added.
	 */
	public void addedCube(Cube cube) {
		CubePanel panel = new CubePanel(cube);
		this.add(panel);
		panel.collisionDetect();
		this.repaint();
	}
	
	/**
	 * Notify the workspace that a Cube was removed.
	 * @param cube the Cube that was removed.
	 */
	public void removedCube(Cube cube) {
		this.remove(cube.getPanel());
		this.repaint();
	}
	
	private class WorkspacePanelMouseListener implements MouseListener, MouseMotionListener {
		private Point anchorPoint;
		private CubePanel draggingPanel;

		@Override
		public void mousePressed(MouseEvent event) {
			if(Emulator.getInteractionMode() == Emulator.InteractionMode.Normal) { 
				for(Cube c : Emulator.getCubes()) {
					CubePanel p = c.getPanel();
					if(p.contains(SwingUtilities.convertPoint(WorkspacePanel.this, event.getPoint(), p))) {
						this.draggingPanel = p;
						this.anchorPoint = SwingUtilities.convertPoint(WorkspacePanel.this, event.getPoint(), p);
					}
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
			if(this.draggingPanel != null) {
				this.draggingPanel.setLocation(event.getX() - this.anchorPoint.x, event.getY() - this.anchorPoint.y);
				WorkspacePanel.this.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent event) {
			if(Emulator.getInteractionMode() == Emulator.InteractionMode.Removing) {
				for(Cube c : Emulator.getCubes()) {
					CubePanel p = c.getPanel();
					if(p.contains(SwingUtilities.convertPoint(WorkspacePanel.this, event.getPoint(), p))) {
						Emulator.removeCube(c);
						Emulator.setInteractionMode(Emulator.InteractionMode.Normal);
						break;
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
}
