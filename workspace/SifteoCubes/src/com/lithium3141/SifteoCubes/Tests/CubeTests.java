package com.lithium3141.SifteoCubes.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lithium3141.SifteoCubes.Cube;

public class CubeTests {
	@Test
	public void testRotationProperty() {
		Cube target = new Cube();
		assertEquals(Cube.ROTATION_0, target.getRotation());
		
		target.setRotation(Cube.ROTATION_0);
		assertEquals(Cube.ROTATION_0, target.getRotation());
		target.setRotation(Cube.ROTATION_90);
		assertEquals(Cube.ROTATION_90, target.getRotation());
		target.setRotation(Cube.ROTATION_180);
		assertEquals(Cube.ROTATION_180, target.getRotation());
		target.setRotation(Cube.ROTATION_270);
		assertEquals(Cube.ROTATION_270, target.getRotation());
	}
	
	@Test
	public void testRotationDegrees() {
		Cube target = new Cube();
		
		double eps = 0.00001;
		
		target.setRotation(Cube.ROTATION_0);
		assertEquals(0.0, target.getRotationDegrees(), eps);
		target.setRotation(Cube.ROTATION_90);
		assertEquals(90.0, target.getRotationDegrees(), eps);
		target.setRotation(Cube.ROTATION_180);
		assertEquals(180.0, target.getRotationDegrees(), eps);
		target.setRotation(Cube.ROTATION_270);
		assertEquals(270.0, target.getRotationDegrees(), eps);
	}
	
	@Test
	public void testRotate() {
		Cube target = new Cube();
		
		target.rotateClockwise();
		assertEquals(Cube.ROTATION_90, target.getRotation());
		target.rotateClockwise();
		assertEquals(Cube.ROTATION_180, target.getRotation());
		target.rotateClockwise();
		assertEquals(Cube.ROTATION_270, target.getRotation());
		target.rotateClockwise();
		assertEquals(Cube.ROTATION_0, target.getRotation());
		
		target.rotateCounterclockwise();
		assertEquals(Cube.ROTATION_270, target.getRotation());
		target.rotateCounterclockwise();
		assertEquals(Cube.ROTATION_180, target.getRotation());
		target.rotateCounterclockwise();
		assertEquals(Cube.ROTATION_90, target.getRotation());
		target.rotateCounterclockwise();
		assertEquals(Cube.ROTATION_0, target.getRotation());
	}
	
	@Test
	public void testSideTranslationRotation0() {
		Cube target = new Cube();
		target.setRotation(Cube.ROTATION_0);
		
		assertEquals(Cube.EDGE_NORTH, target.absoluteEdgeForRelative(Cube.EDGE_TOP));
		assertEquals(Cube.EDGE_EAST, target.absoluteEdgeForRelative(Cube.EDGE_LEFT));
		assertEquals(Cube.EDGE_SOUTH, target.absoluteEdgeForRelative(Cube.EDGE_BOTTOM));
		assertEquals(Cube.EDGE_WEST, target.absoluteEdgeForRelative(Cube.EDGE_RIGHT));
		
		assertEquals(Cube.EDGE_TOP, target.relativeEdgeForAbsolute(Cube.EDGE_NORTH));
		assertEquals(Cube.EDGE_LEFT, target.relativeEdgeForAbsolute(Cube.EDGE_EAST));
		assertEquals(Cube.EDGE_BOTTOM, target.relativeEdgeForAbsolute(Cube.EDGE_SOUTH));
		assertEquals(Cube.EDGE_RIGHT, target.relativeEdgeForAbsolute(Cube.EDGE_WEST));
	}
	
	@Test
	public void testSideTranslationRotation90() {
		Cube target = new Cube();
		target.setRotation(Cube.ROTATION_90);
		
		assertEquals(Cube.EDGE_WEST, target.absoluteEdgeForRelative(Cube.EDGE_TOP));
		assertEquals(Cube.EDGE_NORTH, target.absoluteEdgeForRelative(Cube.EDGE_LEFT));
		assertEquals(Cube.EDGE_EAST, target.absoluteEdgeForRelative(Cube.EDGE_BOTTOM));
		assertEquals(Cube.EDGE_SOUTH, target.absoluteEdgeForRelative(Cube.EDGE_RIGHT));
		
		assertEquals(Cube.EDGE_LEFT, target.relativeEdgeForAbsolute(Cube.EDGE_NORTH));
		assertEquals(Cube.EDGE_BOTTOM, target.relativeEdgeForAbsolute(Cube.EDGE_EAST));
		assertEquals(Cube.EDGE_RIGHT, target.relativeEdgeForAbsolute(Cube.EDGE_SOUTH));
		assertEquals(Cube.EDGE_TOP, target.relativeEdgeForAbsolute(Cube.EDGE_WEST));
	}
	
	@Test
	public void testSideTranslationRotation180() {
		Cube target = new Cube();
		target.setRotation(Cube.ROTATION_180);
		
		assertEquals(Cube.EDGE_SOUTH, target.absoluteEdgeForRelative(Cube.EDGE_TOP));
		assertEquals(Cube.EDGE_WEST, target.absoluteEdgeForRelative(Cube.EDGE_LEFT));
		assertEquals(Cube.EDGE_NORTH, target.absoluteEdgeForRelative(Cube.EDGE_BOTTOM));
		assertEquals(Cube.EDGE_EAST, target.absoluteEdgeForRelative(Cube.EDGE_RIGHT));
		
		assertEquals(Cube.EDGE_BOTTOM, target.relativeEdgeForAbsolute(Cube.EDGE_NORTH));
		assertEquals(Cube.EDGE_RIGHT, target.relativeEdgeForAbsolute(Cube.EDGE_EAST));
		assertEquals(Cube.EDGE_TOP, target.relativeEdgeForAbsolute(Cube.EDGE_SOUTH));
		assertEquals(Cube.EDGE_LEFT, target.relativeEdgeForAbsolute(Cube.EDGE_WEST));
	}
	
	@Test
	public void testSideTranslationRotation270() {
		Cube target = new Cube();
		target.setRotation(Cube.ROTATION_270);
		
		assertEquals(Cube.EDGE_EAST, target.absoluteEdgeForRelative(Cube.EDGE_TOP));
		assertEquals(Cube.EDGE_SOUTH, target.absoluteEdgeForRelative(Cube.EDGE_LEFT));
		assertEquals(Cube.EDGE_WEST, target.absoluteEdgeForRelative(Cube.EDGE_BOTTOM));
		assertEquals(Cube.EDGE_NORTH, target.absoluteEdgeForRelative(Cube.EDGE_RIGHT));
		
		assertEquals(Cube.EDGE_RIGHT, target.relativeEdgeForAbsolute(Cube.EDGE_NORTH));
		assertEquals(Cube.EDGE_TOP, target.relativeEdgeForAbsolute(Cube.EDGE_EAST));
		assertEquals(Cube.EDGE_LEFT, target.relativeEdgeForAbsolute(Cube.EDGE_SOUTH));
		assertEquals(Cube.EDGE_BOTTOM, target.relativeEdgeForAbsolute(Cube.EDGE_WEST));
	}
}
