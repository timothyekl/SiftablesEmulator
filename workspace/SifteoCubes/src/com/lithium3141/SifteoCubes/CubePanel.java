package com.lithium3141.SifteoCubes;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CubePanel extends JPanel {
	private Cube cube;
	
	private Color color;
	
	public CubePanel(Cube c) {
		super();
		
		this.setBackground(Color.RED);
		this.setSize(160, 160);
		
		this.cube = c;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
