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

import com.lithium3141.SifteoCubes.Games.Game;

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
		
		JMenu debugMenu = new JMenu("Debug");
		this.menuBar.add(debugMenu);
		JMenuItem adjacencyItem = new JMenuItem("Print adjacencies");
		adjacencyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		adjacencyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("=== ADJACENCIES ===");
				for(Cube cube : Emulator.getCubes()) {
					for(int i = 0; i < 4; i++) {
						System.out.println(Emulator.formatCubeEdge(cube, i) + ": " + cube.adjacencyAlong(i));
					}
				}
				System.out.println("=== ADJACENCIES ===");
			}
		});
		debugMenu.add(adjacencyItem);
		
		this.setJMenuBar(this.menuBar);
	}
	
	/**
	 * Notify the emulator UI of a new cube.
	 * @param cube the new Cube
	 */
	public void addedCube(Cube cube) {
		this.workspacePanel.addedCube(cube);
	}

	/**
	 * Notify the emulator UI that a cube went away.
	 * @param cube the Cube that was removed
	 */
	public void removedCube(Cube cube) {
		this.workspacePanel.removedCube(cube);
	}

	/**
	 * Notify the emulator UI of a new game.
	 * @param game the new Game
	 */
	public void registeredGame(Game game) {
		this.controlPanel.registeredGame(game);
	}

	/**
	 * Notify the emulator UI that a game went away.
	 * @param game the Game that was removed
	 */
	public void unregisteredGame(Game game) {
		this.controlPanel.unregisteredGame(game);
	}
}
