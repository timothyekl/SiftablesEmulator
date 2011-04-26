package com.lithium3141.SifteoCubes.Games;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import com.lithium3141.SifteoCubes.Cube;
import com.lithium3141.SifteoCubes.Emulator;

public class AdjacencyTestGame implements Game {
	
	Map<Cube,Integer> cubeIDs;
	int newID;

	@Override
	public int getRequiredCubes() {
		return 2;
	}

	@Override
	public String getTitle() {
		return "Adjacency Test";
	}

	@Override
	public void load() {
		this.cubeIDs = new HashMap<Cube,Integer>();
		this.newID = 1;
		for(Cube cube : Emulator.getCubes()) {
			this.mapCubeID(cube);
		}
	}

	@Override
	public void unload() {
		this.cubeIDs = null;
		this.newID = -1;
	}

	@Override
	public void renderCube(Cube cube, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 128, 128);
		
		g.setColor(Color.RED);
		for(int i = 0; i < 4; i++) {
			if(cube.adjacencyAlong(i) != null) {
				g.fillRect(48, 48, 32, 32);
			}
		}
		
		g.setColor(Color.BLACK);
		DrawingUtils.drawCenteredString("" + this.cubeIDs.get(cube), 128, 128, g);
		
		g.setColor(new Color(255, 165, 0, 255));
		
		if(cube.adjacencyAlong(Cube.EDGE_NORTH) != null) {
			g.fillRect(16, 0, 96, 16);
		}
		if(cube.adjacencyAlong(Cube.EDGE_EAST) != null) {
			g.fillRect(112, 16, 16, 96);
		}
		if(cube.adjacencyAlong(Cube.EDGE_SOUTH) != null) {
			g.fillRect(16, 112, 96, 16);
		}
		if(cube.adjacencyAlong(Cube.EDGE_WEST) != null) {
			g.fillRect(0, 16, 16, 96);
		}
	}

	@Override
	public void cubesJoined(Cube c1, int e1, Cube c2, int e2) {
		System.out.println("Joined cubes: " + formatCubeEdge(c1, e1) + " to " + formatCubeEdge(c2, e2));
	}

	@Override
	public void cubesSeparated(Cube c1, int e1, Cube c2, int e2) {
		System.out.println("Separated cubes: " + formatCubeEdge(c1, e1) + " from " + formatCubeEdge(c2, e2));
	}
	
	private String formatCubeEdge(Cube cube, int edge) {
		String edgeString;
		switch(edge) {
		case Cube.EDGE_NORTH: edgeString = "N"; break;
		case Cube.EDGE_EAST: edgeString = "E"; break;
		case Cube.EDGE_SOUTH: edgeString = "S"; break;
		case Cube.EDGE_WEST: edgeString = "W"; break;
		default: edgeString = "?"; break;
		}
		return "(" + this.cubeIDs.get(cube) + "," + edgeString + ")";
	}

	@Override
	public void addedCube(Cube cube) {
		System.out.println("Added cube: " + cube);
		this.mapCubeID(cube);
	}
	
	public void mapCubeID(Cube cube) {
		assert(!this.cubeIDs.containsKey(cube));
		this.cubeIDs.put(cube, this.newID);
		this.newID++;
	}

	@Override
	public void removedCube(Cube cube) {
		System.out.println("Removed cube: " + cube);
		this.removeCubeID(cube);
	}
	
	public void removeCubeID(Cube cube) {
		assert(this.cubeIDs.containsKey(cube));
		this.cubeIDs.remove(cube);
	}

}
