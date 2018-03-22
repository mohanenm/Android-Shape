package edu.luc.etl.cs313.android.shapes.model;

/**
 * A visitor to compute the number of basic shapes in a (possibly complex)
 * shape.
 */
public class Size implements Visitor<Integer> {
    // done entirely your job
    // should be done
    // shape-methods: count
    // interface=methods: get and accept states

    @Override
    public Integer onPolygon(final Polygon p) {
        return 1;
      //can be done with counter, does

    }

    @Override
    public Integer onCircle(final Circle c) {
        return 1;  // FAIZ, WHY DO WE NOT HAVE TO COUNT THESE WITH A COUNTER//????????!!!!!!!!!1
    }

    @Override
    public Integer onGroup(final Group g) {
        int group_num = 0;
        for (Shape shape : g.getShapes()) { // (shapes) implement shape, polygon extends group, so you only need to count group
            group_num = group_num  + shape.accept(this);{
            }
        }
        return group_num;
    }

    @Override
    public Integer onRectangle(final Rectangle q) { // BOTH OF THESE WAYS WORK FINE
      return 1;
        // we can do this with a counter as well, but is not necessary
    }

    @Override
    public Integer onOutline(final Outline o) {
        return o.getShape().accept(this);
    }

    @Override
    public Integer onFill(final Fill c) {
        return c.getShape().accept(this);

    }
    @Override
    public Integer onLocation(final Location l) {
        return l.getShape().accept(this);
    }

    @Override
    public Integer onStroke(final Stroke c) {
        return c.getShape().accept(this);

    }
}
