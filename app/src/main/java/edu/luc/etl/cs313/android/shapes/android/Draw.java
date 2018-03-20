package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;
import java.util.Iterator;
import java.util.*;


/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

	// TODO entirely your job (except onCircle)
    // almost done faiz, try to finish the polygon

	private final Canvas canvas;

	private final Paint paint;

	public Draw(final Canvas canvas, final Paint paint) {
		this.canvas = canvas; // DONE
		this.paint = paint; // DONE
		paint.setStyle(Style.STROKE);
	}

	@Override
	public Void onCircle(final Circle c) {
		canvas.drawCircle(0, 0, c.getRadius(), paint);
		return null;
	}


	@Override
	public Void onStroke(final Stroke c) {
        int c_one;
		c_one = paint.getColor();
		Style s_one;
		s_one = paint.getStyle();
		paint.setColor(c.getColor()); // don't know about this yet
        paint.setStyle(Style.STROKE);
        c.getShape().accept(this);
        paint.setColor(c_one);
        paint.setStyle(s_one);
		return null;
	}

	@Override
	public Void onFill(final Fill f) {
		Style s_one; // looks nicer to me
		s_one = paint.getStyle();
		paint.setStyle(Style.FILL_AND_STROKE);
		f.getShape().accept(this);
		paint.setStyle(s_one);
		return null;
	}
	@Override
	public Void onGroup(final Group g) {
		for (Shape shape : g.getShapes()) shape.accept(this);
		return null;
	}

	@Override
	public Void onLocation(final Location locate) {
		canvas.translate(locate.getX(), locate.getY());
		locate.getShape().accept(this);
		canvas.translate(-locate.getX(), -locate.getY());
		return null;
	}

	@Override
	public Void onRectangle(final Rectangle r) {
        canvas.drawRect(0,0,r.getWidth(),r.getHeight(),paint);
		return null;
	}

	@Override
	public Void onOutline(Outline out) {
		Style style_one= paint.getStyle();
		paint.setStyle(Style.STROKE);
		out.getShape().accept(this);
		paint.setStyle(style_one);
		return null;
	}

	@Override
	public Void onPolygon(final Polygon s) {
		final float[] pts = null;

		canvas.drawLines(pts, paint);
		return null;
	}
}
