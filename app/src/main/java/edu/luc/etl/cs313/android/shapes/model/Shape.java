package edu.luc.etl.cs313.android.shapes.model;

/**
 * A graphical shape.
 */
public interface Shape {
    <Result> Result accept(Visitor<Result> v);
}
