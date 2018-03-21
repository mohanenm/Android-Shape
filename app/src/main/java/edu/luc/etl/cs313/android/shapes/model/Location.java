package edu.luc.etl.cs313.android.shapes.model;

/**
 * A decorator for specifying a shape's location.
 */
public class Location implements Shape {

    protected final int x, y;

    protected final Shape shape;

    public Location(final int x, final int y, final Shape shape) {
        this.x = x;
        this.y = y;
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public <Result> Result accept(final Visitor<Result> v) {
        return v.onLocation(this);
    }
}
