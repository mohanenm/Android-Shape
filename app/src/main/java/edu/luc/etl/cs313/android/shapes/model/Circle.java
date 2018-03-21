package edu.luc.etl.cs313.android.shapes.model;

/**
 * A circle with a specified radius.
 */
public class Circle implements Shape {

    protected final int radius;

    public Circle(final int radius) {
        assert radius >= 0;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public <Result> Result accept(final Visitor<Result> v) {
        return v.onCircle(this);
    }
}
