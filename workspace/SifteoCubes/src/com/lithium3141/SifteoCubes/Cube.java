package com.lithium3141.SifteoCubes;

public class Cube {
	private CubePanel panel;
	
	public static final int EDGE_NORTH = 0;
	public static final int EDGE_EAST  = 1;
	public static final int EDGE_SOUTH = 2;
	public static final int EDGE_WEST  = 3;
	
	public static final int ROTATION_0   = 0;
	public static final int ROTATION_90  = 1;
	public static final int ROTATION_180 = 2;
	public static final int ROTATION_270 = 3;
	
	public static final int EDGE_TOP    = 0;
	public static final int EDGE_LEFT   = 1;
	public static final int EDGE_BOTTOM = 2;
	public static final int EDGE_RIGHT  = 3;
	
	private Cube[] adjacent = new Cube[4];
	private int rotation = ROTATION_0;

	public void setPanel(CubePanel panel) {
		this.panel = panel;
	}

	public CubePanel getPanel() {
		return panel;
	}
	
	/**
	 * Indicates to the emulator framework that this cube needs to have its
	 * virtual screen refreshed. Call after making any change that impacts
	 * the current graphics. (Usually called from within a Game subclass.)
	 */
	public void needsRefresh() {
		this.panel.needsRefresh();
	}
	
	public int getRotation() {
		return this.rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public double getRotationDegrees() {
		return this.rotation * 90.0;
	}
	
	public void rotateClockwise() {
		this.rotation = (this.rotation + 1) % 4;
		this.needsRefresh();
	}
	public void rotateCounterclockwise() {
		this.rotation = (this.rotation - 1 + 4) % 4;
		this.needsRefresh();
	}
	
	/**
	 * Get the Cube adjacent to this Cube along the given absolute edge
	 * @param edge The edge to check
	 * @return the adjacent Cube, if any
	 */
	public Cube adjacencyAlong(int edge) {
		return this.adjacent[edge];
	}
	
	/**
	 * Get the Cube adjacent to this Cube along the given relative edge
	 * @param edge The edge to check
	 * @return the adjacent Cube, if any
	 */
	public Cube adjacencyAlongRelative(int edge) {
		return this.adjacent[(edge - this.rotation + 4) % 4];
	}
	
	/**
	 * Convert the given relative edge into an absolute edge. Example:
	 * "what edge is at the top right now?"
	 * @param edge Edge to convert
	 * @return converted edge
	 */
	public int absoluteEdgeForRelative(int edge) {
		return (edge - this.rotation + 4) % 4; 
	}
	
	/**
	 * Convert the given absolute edge into an relative edge. Example:
	 * "what way is the north edge facing right now?"
	 * @param edge Edge to convert
	 * @return converted edge
	 */
	public int relativeEdgeForAbsolute(int edge) {
		return (edge + this.rotation) % 4; 
	}
	
	/**
	 * Notify this cube that it became adjacent to another Cube along some 
	 * absolute edge.
	 */
	public void joinedCube(Cube cube, int edge) {
		this.adjacent[edge] = cube;
	}
	
	/**
	 * Notify this cube that it is no longer adjacent to another Cube along an 
	 * absolute edge.
	 */
	public void separatedCube(Cube cube, int edge) {
		assert(this.adjacent[edge] == cube || this.adjacent[edge] == null);
		this.adjacent[edge] = null;
	}
}
