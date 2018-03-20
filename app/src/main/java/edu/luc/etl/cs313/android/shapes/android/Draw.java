package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;


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
		int c1= paint.getColor();
		Style s1=paint.getStyle();
		paint.setColor(c.getColor());
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
		Style s1=paint.getStyle();
		paint.setStyle(Style.STROKE);
		o.getShape().accept(this);
		paint.setStyle(s1);
		return null;
	}

	@Override
	public Void onPolygon(final Polygon s) {
		final List<Float> lp = new LinkedList<Float>();
		int index=0;

		final Float[] p1 = new Float[2];// to get x and y for 1st point
		p1[0]=(float) s.getPoints().get(0).getX();
		p1[1]= (float) s.getPoints().get(0).getY();
		lp.add(p1[0]);
		lp.add(p1[1]);

		for ( int i =1; i < s.getPoints().size(); i++ )
		{
			Float xi=(float) s.getPoints().get(i).getX();
			Float yi=(float) s.getPoints().get(i).getY();
			lp.add(xi);
			lp.add(yi);
			lp.add(xi);
			lp.add(yi);
			if (i == ((s.getPoints().size())-1))
			{
				lp.add(p1[0]);
				lp.add(p1[1]);
				break;
			}
		}
		final float pts[] = new float[lp.size()+2];

		for ( float p : lp )
		{
			pts[index] = p;
			index++;
		}
		canvas.drawLines(pts, paint);
		return null;
	}
}
