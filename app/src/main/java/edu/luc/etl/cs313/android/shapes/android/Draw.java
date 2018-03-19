package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

	// TODO entirely your job (except onCircle)

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
        int c1= paint.getColor();
        Style s1=paint.getStyle();
        paint.setColor(c.getColor()); // don't know about this yet
        paint.setStyle(Style.STROKE);
        c.getShape().accept(this);
        paint.setColor(c1);
        paint.setStyle(s1);
		return null;
	}

	@Override
	public Void onFill(final Fill f) {
		Style s1=paint.getStyle();
		paint.setStyle(Style.FILL_AND_STROKE);
		f.getShape().accept(this);
		paint.setStyle(s1);
		return null;
	}

	@Override
	public Void onGroup(final Group g) {
		final Iterator<? extends Shape> itr=g.getShapes().iterator();
		while(itr.hasNext())
		    itr.next().accept(this)
		return null;
	}

	@Override
	public Void onLocation(final Location l) {
		canvas.translate(1.getX(), 1.getY());
		return null;
	}

	@Override
	public Void onRectangle(final Rectangle r) {

		return null;
	}

	@Override
	public Void onOutline(Outline o) {

		return null;
	}

	@Override
	public Void onPolygon(final Polygon s) {

		final float[] pts = null;

		canvas.drawLines(pts, paint);
		return null;
	}
}
