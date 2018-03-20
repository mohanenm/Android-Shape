package edu.luc.etl.cs313.android.shapes.model;
import java.util.Iterator;

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
    public Integer onPolygon(final Polygon p) {int num_of_shape =0;
        num_of_shape++;
        return num_of_shape;

    }

    @Override
    public Integer onCircle(final Circle c) {int num_of_shape =0;
        num_of_shape++;
        return num_of_shape;
    }

    @Override
    public Integer onGroup(final Group g) {int num_of_shape =0;
        for(Shape shape : g.getShapes()){
            num_of_shape += shape.accept(this);
        }
        return num_of_shape;
    }

    @Override
    public Integer onRectangle(final Rectangle q) {int num_of_shape =0;
        num_of_shape++;
        return num_of_shape;
    }

    @Override
    public Integer onOutline(final Outline o) {
        return o.getShape().accept(this);
    }

    @Override
    public Integer onFill(final Fill c) {return c.getShape().accept(this);

    }

    @Override
    public Integer onLocation(final Location l) {
        return l.getShape().accept(this);
    }

    @Override
    public Integer onStroke(final Stroke c) {return c.getShape().accept(this);

    }
}
