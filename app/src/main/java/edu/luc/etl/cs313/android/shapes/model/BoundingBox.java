
package edu.luc.etl.cs313.android.shapes.model;

import java.util.Iterator;

/*
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */

public class BoundingBox implements Visitor<Location> {

    // TODO entirely your job (except onCircle)
    /* trying to understand tests, simple groups, middle groups, etc

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
        int min_y = 0;
        // int minX = integer.MAX_VALUE;
        // int minY= integer.MAX_VALUE;

        final Iterator<? extends Shape> itr = g.getShapes().iterator();
        while (itr.hasNext()) {
            final Location local = itr.next().accept(this);
            int x1 = local.getX(); // based on testBoundingBox, don't know
            int y1 = local.getY(); // if this will work though; my psuedo-code
            // was somewhat wrong.
            int x2 = local.getX() + ((Rectangle) local.getShape()).getWidth();
            int y2 = local.getX() + ((Rectangle) local.getShape()).getHeight();


        }
        return Location;
    }

        @Override
        public Location onLocation ( final Location l){

            return null;
        }

        @Override
        public Location onRectangle ( final Rectangle r){
            return null;
        }

        @Override
        public Location onStroke ( final Stroke c){
            return null;
        }

        @Override
        public Location onOutline ( final Outline o){
            return null;
        }

        @Override
        public Location onPolygon ( final Polygon s){
            return null;
        }
    }

