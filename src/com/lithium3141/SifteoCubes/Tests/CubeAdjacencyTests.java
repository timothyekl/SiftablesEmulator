package com.lithium3141.SifteoCubes.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lithium3141.SifteoCubes.Cube;


public class CubeAdjacencyTests {
	@Test
	public void testInitialAdjacencyNull() {
		Cube target = new Cube();
		
		for(int i = 0; i < 4; i++) {
			assertNull(target.adjacencyAlong(i));
			assertNull(target.adjacencyAlongRelative(i));
		}
	}
	
	@Test
	public void testBasicAdjacency() {
		Cube target = new Cube();
		Cube other = new Cube();
		
		target.joinedCube(other, Cube.EDGE_NORTH);
		other.joinedCube(target, Cube.EDGE_SOUTH);
		
		assertEquals(other, target.adjacencyAlong(Cube.EDGE_NORTH));
		assertEquals(other, target.adjacencyAlongRelative(Cube.EDGE_TOP));
		
		target.separatedCube(other, Cube.EDGE_NORTH);
		other.separatedCube(target, Cube.EDGE_SOUTH);
		
		assertNull(target.adjacencyAlong(Cube.EDGE_NORTH));
		assertNull(target.adjacencyAlongRelative(Cube.EDGE_TOP));
	}
}
