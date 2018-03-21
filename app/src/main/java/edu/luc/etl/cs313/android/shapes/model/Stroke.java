package edu.luc.etl.cs313.android.shapes.model;

/**
 * A decorator for specifying the stroke (foreground) color for drawing the
 * shape.
 */
public class Stroke implements Shape {
    private final int color;
    private final Shape shape;

    // done entirely your job
    public Stroke(final int color, final Shape shape) {
        this.color = color;
        this.shape = shape;
    }

    public int getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public <Result> Result accept(Visitor<Result> v) {
        return v.onStroke(this);
    }
}
