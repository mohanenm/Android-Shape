
package edu.luc.etl.cs313.android.shapes.model;
import java.util.*;
import java.util.Iterator;


/*
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
        int max_x = 0;
        int max_y = 0;
        int min_x = Integer.MAX_VALUE;
        int min_y= Integer.MAX_VALUE;

        // could have used while loop, as well
        for (Shape shape : g.getShapes()) {
            final Location local;
            local = shape.accept(this);
            int x1 = local.getX(); // based on testBoundingBox, don't know
            int y1 = local.getY(); // if this will work though; my psuedo-code
            // was somewhat wrong.
            int x2 = local.getX() + ((Rectangle) local.getShape()).getWidth();
            int y2 = local.getX() + ((Rectangle) local.getShape()).getHeight();
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
            // thinking this may actually be wrong


        }
        return new Location(min_x, min_y, new Rectangle(max_x-min_x,max_y-min_y));
    }


    @Override
        public Location onLocation ( final Location l){
        Location local = l.getShape().accept(this); // accepting final location from super
        int x = l.getX() +local.getX(); // get the final location from this method
        int y = l.getY() + local.getY();
        return new Location(x,y,local.getShape());
        //return null;
        }

        @Override
        public Location onRectangle ( final Rectangle r){
            final int width = r.getWidth(); // take width from super
            final int height = r.getHeight();
            return new Location(0,0, new Rectangle(width, height)); // this is finally making sense
        }

        @Override
        public Location onStroke ( final Stroke c){

            return c.getShape().accept(this); //accept visitor result!

        }

        @Override
        public Location onOutline ( final Outline o){
            return o.getShape().accept(this); //same as above
        }

        @Override
        public Location onPolygon ( final Polygon s){
            final Iterator<? extends Point> itr = s.getPoints().iterator();
            Point poly_point =itr.next();
            int min_x=poly_point.getX();
            int min_y=poly_point.getY();
            int max_x= min_x;
            int max_y=min_y;

            if (itr.hasNext()) {
                do {
                    poly_point = itr.next();
                    int x = poly_point.getX();
                    int y = poly_point.getY();

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


