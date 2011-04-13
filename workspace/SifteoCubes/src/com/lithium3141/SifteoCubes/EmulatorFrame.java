package com.lithium3141.SifteoCubes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Main JFrame for the Sifteo Cubes emulator. Handles display of both the active
 * workspace and the cubes controller. Automatically sets up the panels needed
 * for both (a WorkspacePanel and ControlPanel, respectively).
 * 
 * @author ekltl
 */
@SuppressWarnings("serial")
public class EmulatorFrame extends JFrame {
	
	private JMenuBar menuBar;
	
	private WorkspacePanel workspacePanel;
	private ControlPanel controlPanel;
	
	/**
	 * Construct a new EmulatorFrame with new workspace and control panels.
	 */
	public EmulatorFrame() {
		super("Sifteo Cubes Emulator");
		
		// A little housekeeping
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		
		// Add content panels
		this.workspacePanel = new WorkspacePanel();
		this.controlPanel = new ControlPanel();
		this.getContentPane().add(this.workspacePanel, BorderLayout.CENTER);
		this.getContentPane().add(this.controlPanel, BorderLayout.EAST);
		
		// Build the menu
		this.menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		this.menuBar.add(fileMenu);
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		quitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Emulator.quit();
			}
		});
		fileMenu.add(quitItem);
		this.setJMenuBar(this.menuBar);
	}
	
	/**
	 * Notify the emulator UI of a change.
	 */
	public void addedCube(Cube cube) {
		this.workspacePanel.addedCube(cube);
	}

	public void removedCube(Cube cube) {
		this.workspacePanel.removedCube(cube);
	}
}
