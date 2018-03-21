package edu.luc.etl.cs313.android.shapes.model;

/**
 * A rectangle with specified width and height.
 */
public class Rectangle implements Shape {

    protected final int width, height;

    public Rectangle(final int width, final int height) {
        assert width >= 0;
        assert height >= 0;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public <Result> Result accept(final Visitor<Result> v) {
        return v.onRectangle(this);
    }
}
