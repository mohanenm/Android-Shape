package edu.luc.etl.cs313.android.shapes.model;

/**
 * A visitor to compute the number of basic shapes in a (possibly complex)
 * shape.
 */
public class Size implements Visitor<Integer> {

	// TODO entirely your job

	@Override
	public Integer onPolygon(final Polygon p) {
		int numberOfShape = 0;
		numberOfShape++;
		return numberOfShape;
	}

	@Override
	public Integer onCircle(final Circle c) {
		return -1;
	}

	@Override
	public Integer onGroup(final Group g) {
		return -1;
	}

	@Override
	public Integer onRectangle(final Rectangle q) {
		return -1;
	}

	@Override
	public Integer onOutline(final Outline o) {
		return -1;
	}

	@Override
	public Integer onFill(final Fill c) {
		return -1;
	}

	@Override
	public Integer onLocation(final Location l) {
		return -1;
	}

	@Override
	public Integer onStroke(final Stroke c) {
		return -1;
	}
}
