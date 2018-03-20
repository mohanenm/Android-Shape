
package edu.luc.etl.cs313.android.shapes.model;
import java.util.Iterator;


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

        return f.getShape().accept(this);
    }

    @Override
    public Location onGroup(final Group g) {
        int max_x;  // for sake of readability!!!
        max_x = 0;
        int max_y;
        max_y = 0;
        int min_x;
        min_x = Integer.MAX_VALUE;
        int min_y;
        min_y = Integer.MAX_VALUE;

        final Iterator<? extends Shape> itr = g.getShapes().iterator();
        if (itr.hasNext()) {
            do {
                final Location s_local = itr.next().accept(this);
                int x1 = s_local.getX();
                int y1 = s_local.getY();
                int x2 = s_local.getX() + ((Rectangle) s_local.getShape()).getWidth();
                int y2 = s_local.getY() + ((Rectangle) s_local.getShape()).getHeight();

                if (max_x < x2) {
                    max_x = x2;
                }
                if (max_y < y2) {
                    max_y = y2;
                }
                if (min_x > x1) {
                    min_x = x1;
                }
                if (min_y > y1) {
                    min_y = y1;
                }
            } while (itr.hasNext());
        }
        return new Location(min_x,min_y,new Rectangle(max_x-min_x,max_y-min_y));

    }

    @Override
    public Location onLocation(final Location l) {
        Location s_local=l.getShape().accept(this);// accepting final location from super
        int x;
        x = l.getX()+ s_local.getX();
        int y; // get the final location from this method
        y = l.getY() +s_local.getY();
        return new Location(x, y, s_local.getShape());
        //return null;
    }

    @Override
    public Location onRectangle(final Rectangle r) {

        final int width = r.getWidth();// take width from super, wait......no
        final int height=r.getHeight();
        return new Location(0, 0, new Rectangle(width,height)); // this is finally making sense
    }

    @Override
    public Location onStroke(final Stroke c)
    {
        return c.getShape().accept(this); //accept visitor result!

    }

    @Override
    public Location onOutline(final Outline o)
    { return o.getShape().accept(this);
    } // same as above
/* issues in polygon had nothing to do with this code, or polygon code,
* rather, the problem was the point class
* spent waaaaaay too much time trying to figure that out
 */
    @Override
    public Location onPolygon(final Polygon s) {
        final Iterator<? extends Point> itr = s.getPoints().iterator();
        Point p=itr.next();
        int min_x=p.getX();
        int min_y=p.getY();
        int max_x= min_x;
        int max_y=min_y;

        if (itr.hasNext()) {
            do {
                p = itr.next();
                int x = p.getX();
                int y = p.getY();

                if (min_x > x) {
                    min_x = x;
                }
                if (min_y > y) {
                    min_y = y;
                }
                if (max_x < x) {
                    max_x = x;
                }
                if (max_y < y) {
                    max_y = y;
                }
            } while (itr.hasNext());
        }
        return new Location(min_x,min_y,new Rectangle(max_x-min_x,max_y-min_y));
    }
}
