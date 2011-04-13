package com.lithium3141.SifteoCubes;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Control sidebar for choosing cube games. Takes the place of the on-computer
 * Siftrunner application.
 *  
 * @author ekltl
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	public ControlPanel() {
		super(new GridBagLayout());
		
		this.setBackground(new Color(192, 192, 255, 255));
		
		// Add/remove cube buttons
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(Emulator.getCubes().size() < 6) {
					Emulator.addCube();
					Emulator.setInteractionMode(Emulator.InteractionMode.Normal);
				}
			}
		});
		this.add(addButton, constraints);
		
		constraints.gridx = GridBagConstraints.RELATIVE;
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Emulator.setInteractionMode(Emulator.InteractionMode.Removing);
			}
		});
		this.add(removeButton, constraints);
	}
}
