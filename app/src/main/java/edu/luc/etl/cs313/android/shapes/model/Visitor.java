package edu.luc.etl.cs313.android.shapes.model;

/**
 * A visitor on edu.luc.etl.cs313.android.shapes.model. Instances represent functions from Shape to
 * a generic result type.
 */
public interface Visitor<Result> {
	Result onCircle(Circle c);
	Result onRectangle(Rectangle r);
	Result onGroup(Group g);
	Result onStroke(Stroke c);
	Result onFill(Fill c);
	Result onLocation(Location l);
	Result onOutline(Outline o);
	Result onPolygon(Polygon p);
}
