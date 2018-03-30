package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/*
* Specifically, complete the code in the various Java source files within the src folder. Look in the Android Studio
* Implementing the missing classes Stroke, Outline, Point, and Polygon
* The Stroke decorator indicates the foreground color for drawing its Shape.
* The Outline decorator does the opposite of the Fill decorator: it indicates that its Shape should be drawn in outline (default) mode.
* A Point is a Location without a Shape. You should implement it using a Circle with radius 0 as its Shape and override any methods as needed.
* A (closed) Polygon is a Shape defined by a list of Points; the last Point should be connected to the first one to close the Polygon. Implement it as a special case of Group.
 */


/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {


	// done entirely your job (except onCircle)
	// almost done faiz, try to finish the polygon

	private final Canvas canvas;

	private final Paint paint;

	public Draw(final Canvas canvas, final Paint paint) {
		this.canvas = canvas;
		this.paint = paint;
		paint.setStyle(Style.STROKE);
	}

	@Override
	public Void onCircle(final Circle c) {
		canvas.drawCircle(0, 0, c.getRadius(), paint);
		return null;
	}

	@Override
	public Void onStroke(final Stroke c) {
		int color= paint.getColor();
		Style style=paint.getStyle();
		paint.setColor(c.getColor());
		paint.setStyle(Style.STROKE);
		c.getShape().accept(this);
		paint.setColor(color);
		paint.setStyle(style);
		return null;
	}

	@Override
	public Void onFill(final Fill f) {
		Style style = paint.getStyle();
		paint.setStyle(Style.FILL_AND_STROKE);
		f.getShape().accept(this);
		paint.setStyle(style);
		return null;

	}

	@Override
	public Void onGroup(final Group g) {
		final Iterator<? extends Shape> itr=g.getShapes().iterator();
		while(itr.hasNext())
			itr.next().accept(this);
		return null;
	}

	@Override
	public Void onLocation(final Location l) {
		canvas.translate(l.getX(), l.getY());
		l.getShape().accept(this);
		canvas.translate(-l.getX(), -l.getY());
		return null;
	}

	@Override
	public Void onRectangle(final Rectangle r) {
		canvas.drawRect(0,0,r.getWidth(),r.getHeight(),paint);
		return null;
	}

	@Override
	public Void onOutline(Outline o) {
		Style style =paint.getStyle();
		paint.setStyle(Style.STROKE);
		o.getShape().accept(this);
		paint.setStyle(style);
		return null;
	}
// changing for more explicit variables
	@Override
	public Void onPolygon(final Polygon s) {
		final List<Float> point_one;
		point_one = new LinkedList<>();
		int itr_add_pts; // for clarity when collaborating, not best practice though
		itr_add_pts = 0;
		final Float[] one_p;
		one_p = new Float[2];
		one_p[0]=(float) s.getPoints().get(0).getX();
		one_p[1]= (float) s.getPoints().get(0).getY();
		point_one.add(one_p[0]);
		point_one.add(one_p[1]);

		for (int i = 1; i < s.getPoints().size(); i++ )
		{
			Float xi=(float) s.getPoints().get(i).getX();
			Float yi=(float) s.getPoints().get(i).getY();
			point_one.add(xi);
			point_one.add(yi);
			point_one.add(xi);
			point_one.add(yi);
			if (i == ((s.getPoints().size())-1))
			{
				point_one.add(one_p[0]);
				point_one.add(one_p[1]);
				break;
			}
		}
		final float pnts[] = new float[point_one.size()+2];
		for ( float p : point_one )
		{
			pnts[itr_add_pts] = p;
			itr_add_pts++;
		}
		canvas.drawLines(pnts, paint);
		return null;
	}
}
