package edu.luc.etl.cs313.android.shapes.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A composite for grouping edu.luc.etl.cs313.android.shapes.model.
 */
public class Group implements Shape {

	protected final List<? extends Shape> shapes;

	public Group(final Shape... shapes) {
		this.shapes = Arrays.asList(shapes);
	}

	public List<? extends Shape> getShapes() {
		return Collections.unmodifiableList(shapes);
	}

	@Override
	public <Result> Result accept(final Visitor<Result> v) {
		return v.onGroup(this);
	}

}