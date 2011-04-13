package com.lithium3141.SifteoCubes;

import java.util.HashSet;
import java.util.Set;

public class Emulator {

	private static EmulatorFrame emulatorFrame;
	
	private static Set<Cube> cubes;
	
	/**
	 * @param args Command-line arguments. Ignored.
	 */
	public static void main(String[] args) {
		// Instantiate cube variables
		cubes = new HashSet<Cube>();
		
		// Create the JFrame
		emulatorFrame = new EmulatorFrame();
		emulatorFrame.setVisible(true);
	}
	
	/**
	 * Add a new cube to active emulation.
	 */
	public static void addCube() {
		Cube c = new Cube();
		cubes.add(c);
		emulatorFrame.addedCube(c);
	}
	
	/**
	 * @return the set of cubes in play.
	 */
	public static Set<Cube> getCubes() {
		return cubes;
	}
	
	/**
	 * Clean up and exit the Emulator.
	 */
	public static void quit() {
		System.exit(0);
	}

}
