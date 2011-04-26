package com.lithium3141.SifteoCubes;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.lithium3141.SifteoCubes.Games.Game;

/**
 * Control sidebar for choosing cube games. Takes the place of the on-computer
 * Siftrunner application and manages physical hardware (Cube objects). Allows
 * users to select games, add and remove cubes, and provides instructions.
 *  
 * @author ekltl
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	private JComboBox gameSelectComboBox;
	
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
		
		// Game selector
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		
		this.gameSelectComboBox = this.buildGameSelectComboBox();
		this.gameSelectComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Emulator.setActiveGame(Emulator.getGames().get(ControlPanel.this.gameSelectComboBox.getSelectedIndex()));
			}
		});
		this.add(this.gameSelectComboBox, constraints);
	}
	
	private JComboBox buildGameSelectComboBox() {
		List<Game> games = Emulator.getGames();
		String[] titles = new String[games.size()];
		for(int i = 0; i < games.size(); i++) {
			titles[i] = games.get(i).getTitle();
		}
		
		JComboBox comboBox = new JComboBox(titles);
		return comboBox;
	}

	public void registeredGame(Game game) {
		this.gameSelectComboBox.addItem(game.getTitle());
	}

	public void unregisteredGame(Game game) {
		this.gameSelectComboBox.removeItem(game.getTitle());
	}
}
