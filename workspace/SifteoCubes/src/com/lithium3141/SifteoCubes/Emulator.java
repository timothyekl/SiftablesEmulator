package com.lithium3141.SifteoCubes;

import java.util.HashSet;
import java.util.Set;

public class Emulator {

	private static EmulatorFrame emulatorFrame;
	
	private static Set<Cube> cubes;
	
	public static enum InteractionMode {
		Normal,
		Removing
	};
	
	private static InteractionMode interactionMode;
	
	/**
	 * @param args Command-line arguments. Ignored.
	 */
	public static void main(String[] args) {
		// Instantiate cube variables
		cubes = new HashSet<Cube>();
		interactionMode = InteractionMode.Normal;
		
		// Create the JFrame
		emulatorFrame = new EmulatorFrame();
		emulatorFrame.setVisible(true);
	}
	
	/**
	 * Add a new cube to active emulation.
	 */
	public static void addCube() {
		Cube cube = new Cube();
		cubes.add(cube);
		emulatorFrame.addedCube(cube);
	}
	
	public static void removeCube(Cube cube) {
		cubes.remove(cube);
		emulatorFrame.removedCube(cube);
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

	public static void setInteractionMode(InteractionMode interactionMode) {
		Emulator.interactionMode = interactionMode;
	}

	public static InteractionMode getInteractionMode() {
		return interactionMode;
	}

}
