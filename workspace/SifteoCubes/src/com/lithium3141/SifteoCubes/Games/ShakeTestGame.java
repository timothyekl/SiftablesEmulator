package com.lithium3141.SifteoCubes.Games;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import com.lithium3141.SifteoCubes.Cube;
import com.lithium3141.SifteoCubes.Emulator;

public class ShakeTestGame implements Game {
	
	Map<Cube,Boolean> cubeMap;

	@Override
	public int getRequiredCubes() {
		return 1;
	}

	@Override
	public String getTitle() {
		return "Shake Test";
	}

	@Override
	public void load() {
		this.cubeMap = new HashMap<Cube,Boolean>();
		for(Cube cube : Emulator.getCubes()) {
			this.cubeMap.put(cube, false);
		}
	}

	@Override
	public void unload() {
		this.cubeMap.clear();
		this.cubeMap = null;
	}

	@Override
	public void renderCube(Cube cube, Graphics g) {
		if(this.cubeMap.get(cube)) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(0, 0, 128, 128);
	}

	@Override
	public void cubesJoined(Cube c1, int e1, Cube c2, int e2) {}

	@Override
	public void cubesSeparated(Cube c1, int e1, Cube c2, int e2) {}

	@Override
	public void addedCube(Cube cube) {
		this.cubeMap.put(cube, false);
	}

	@Override
	public void removedCube(Cube cube) {
		this.cubeMap.remove(cube);
	}

	@Override
	public void shookCube(Cube cube) {
		System.out.println("Shook cube: " + cube);
		
		this.cubeMap.put(cube, true);
		cube.needsRefresh();
		
		(new Thread() {
			Cube c;
			
			public Thread setCube(Cube c) {
				this.c = c;
				return this;
			}
			
			@Override
			public void run() {
				try {
					Thread.sleep(250);
				} catch(InterruptedException e) {}
				ShakeTestGame.this.cubeMap.put(c, false);
				this.c.needsRefresh();
			}
		}).setCube(cube).start();
	}

}
