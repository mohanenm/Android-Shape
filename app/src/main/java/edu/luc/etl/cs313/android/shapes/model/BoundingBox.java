package edu.luc.etl.cs313.android.shapes.model;

import java.util.Iterator;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

    // DONE entirely your job (except onCircle)
    /* trying to understand tests, simple groups, middle groups, etc
     * videos are helping with this much more
     * should be, need to finish this before I can move on: 7:10pm
     */
    @Override
    public Location onCircle(final Circle c) {
        final int radius = c.getRadius();
        return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
    }
    @Override
    public Location onFill(final Fill f) {
        return f.getShape().accept(this); // accept location where needs filling
    }
    @Override
    public Location onGroup(final Group g) {
        int max_x;  // for sake of readability!!!
        max_x = 0;
        int max_y;
        max_y = 0;
        int min_x;
        min_x = MAX_VALUE; // SO WE CAN ACCOUNT FOR SITUATIONS WHERE THERE IS NO VALUE
        int min_y;
        min_y = MAX_VALUE;

        final Iterator<? extends Shape> itr = g.getShapes().iterator();
        if (itr.hasNext()) {
            do {
                final Location s_local = itr.next().accept(this);
                int x_one = s_local.getX();
                int y_one = s_local.getY();
                int x_two = s_local.getX() + ((Rectangle) s_local.getShape()).getWidth();
                int y_two = s_local.getY() + ((Rectangle) s_local.getShape()).getHeight();

                if (max_x < x_two) {
                    max_x = x_two;
                }
                if (max_y < y_two) {
                    max_y = y_two;
                }
                if (min_x > x_one) {
                    min_x = x_one;
                }
                if (min_y > y_one) {
                    min_y = y_one;
                }
            } while (itr.hasNext());
        }
        return new Location(min_x, min_y, new Rectangle(max_x - min_x, max_y - min_y));

    }

    @Override
    public Location onLocation(final Location l) {
        Location s_local = l.getShape().accept(this);// accepting final location from super
        int x;
        x = l.getX() + s_local.getX();
        int y; // get the final location from this method
        y = l.getY() + s_local.getY();
        return new Location(x, y, s_local.getShape());
        //return null;
    }

    @Override
    public Location onRectangle(final Rectangle r) {
        return new Location(0, 0, new Rectangle(r.width, r.height)); // this is finally making sense
    }

    @Override
    public Location onStroke(final Stroke c) {
        return c.getShape().accept(this); //accept visitor result!

    }

    @Override
    public Location onOutline(final Outline o) {
        return o.getShape().accept(this);
    } // same as above

    /* issues in polygon had nothing to do with this code, or polygon code,
    * rather, the problem was the point class
    * spent waaaaaay too much time trying to figure that out
     */
    @Override
    public Location onPolygon(final Polygon s) { // i did not think this would work, but it does because polygon extends group!
        return this.onGroup(s);
    }
}
