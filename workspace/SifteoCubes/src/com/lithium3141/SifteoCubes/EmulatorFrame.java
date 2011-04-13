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
public class EmulatorFrame extends JFrame implements ActionListener {
	
	private JMenuBar menuBar;
	
	/**
	 * Construct a new EmulatorFrame with new workspace and control panels.
	 */
	public EmulatorFrame() {
		super("Sifteo Cubes Emulator");
		
		// A little housekeeping
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		
		// Add content panels
		this.getContentPane().add(new WorkspacePanel(), BorderLayout.CENTER);
		this.getContentPane().add(new ControlPanel(), BorderLayout.EAST);
		
		// Build the menu
		this.menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		this.menuBar.add(fileMenu);
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		quitItem.addActionListener(this);
		fileMenu.add(quitItem);
		this.setJMenuBar(this.menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Quit")) {
			Emulator.quit();
		}
	}
}
