package com.lithium3141.SifteoCubes;

public class Emulator {

	private static EmulatorFrame emulatorFrame;
	
	/**
	 * @param args Command-line arguments. Ignored.
	 */
	public static void main(String[] args) {
		// Create the JFrame
		emulatorFrame = new EmulatorFrame();
		emulatorFrame.setVisible(true);
	}
	
	/**
	 * Clean up and exit the Emulator.
	 */
	public static void quit() {
		System.exit(0);
	}

}
