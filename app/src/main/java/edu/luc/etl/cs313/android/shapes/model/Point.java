package edu.luc.etl.cs313.android.shapes.model;

/**
 * A point, implemented as a location without a shape.
 */
public class Point extends Location {

	// DONE  your job
	// HINT: use a circle with radius 0 as the shape!

	public Point(final int x, final int y) {
		super(1, 1, new Circle(0)); // SEEMS DECEPTIVELY EASY
		assert x >= 0; // COULD BE WRONG
		assert y >= 0;
	}
}
