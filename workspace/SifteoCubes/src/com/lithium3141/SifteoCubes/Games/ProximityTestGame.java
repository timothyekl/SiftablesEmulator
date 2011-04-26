package com.lithium3141.SifteoCubes.Games;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lithium3141.SifteoCubes.Cube;
import com.lithium3141.SifteoCubes.Emulator;

public class ProximityTestGame implements Game {
	
	private Map<Cube, List<Cube>> proximityMap;

	@Override
	public int getRequiredCubes() {
		return 2;
	}

	@Override
	public String getTitle() {
		return "Proximity Test";
	}

	@Override
	public void load() {
		this.proximityMap = new HashMap<Cube, List<Cube>>();
		for(Cube cube : Emulator.getCubes()) {
			addCubeMapping(cube);
		}
	}

	@Override
	public void unload() {
		this.proximityMap.clear();
		this.proximityMap = null;
	}

	@Override
	public void renderCube(Cube cube, Graphics g) {
		if(!this.proximityMap.get(cube).isEmpty()) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(0, 0, 128, 128);
	}

	@Override
	public void cubesJoined(Cube c1, int e1, Cube c2, int e2) {
		this.proximityMap.get(c1).add(c2);
		this.proximityMap.get(c2).add(c1);
		
		(new Thread() {
			Cube c1;
			Cube c2;
			
			public Thread setCubes(Cube c1, Cube c2) {
				this.c1 = c1;
				this.c2 = c2;
				return this;
			}
			
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
				ProximityTestGame.this.proximityMap.get(this.c1).remove(this.c2);
				ProximityTestGame.this.proximityMap.get(this.c2).remove(this.c1);
			}
		}).setCubes(c1, c2).start();
	}

	@Override
	public void cubesSeparated(Cube c1, int e1, Cube c2, int e2) {
		this.proximityMap.get(c1).remove(c2);
		this.proximityMap.get(c2).remove(c1);
	}

	@Override
	public void addedCube(Cube cube) {
		this.addCubeMapping(cube);
	}

	@Override
	public void removedCube(Cube cube) {
		this.removeCubeMapping(cube);
	}

	private void addCubeMapping(Cube cube) {
		System.out.println("Adding mapping for cube " + cube);
		this.proximityMap.put(cube, new ArrayList<Cube>());
		for(int i = 0; i < 4; i++) {
			if(cube.adjacencyAlong(i) != null) {
				proximityMap.get(cube).add(cube.adjacencyAlong(i));
			}
		}
	}
	
	private void removeCubeMapping(Cube cube) {
		this.proximityMap.remove(cube);
	}
}
