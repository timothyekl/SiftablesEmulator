package com.lithium3141.SifteoCubes;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * Primary workspace for dealing with emulated cubes. Handles display of the
 * cubes themselves.
 * 
 * @author ekltl
 */
@SuppressWarnings("serial")
public class WorkspacePanel extends JPanel {
	public WorkspacePanel() {
		super(new GridBagLayout());
		
		// TODO debugging; remove
		this.setBackground(Color.GREEN);
		
		
	}
}
