package edu.luc.etl.cs313.android.shapes.model;

import static edu.luc.etl.cs313.android.shapes.model.Fixtures.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBoundingBox {

	protected BoundingBox v;

	@Before
	public void setUp() {
		v = new BoundingBox();
	}

	@After
	public void tearDown() {
		v = null;
	}

	@Test
	public void testCircle() {
		final Location b = new Circle(50).accept(v);
		final Rectangle r = (Rectangle) b.getShape();
		assertEquals(-50, b.getX());
		assertEquals(-50, b.getY());
		assertEquals(100, r.getWidth());
		assertEquals(100, r.getHeight());
	}

	@Test
	public void testRectangle() {
		final Location b = new Rectangle(80, 120).accept(v);
		final Rectangle r = (Rectangle) b.getShape();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		assertEquals(80, r.getWidth());
		assertEquals(120, r.getHeight());
	}

	@Test
	public void testLocation() {
		final Location b = simpleLocation.accept(v);
		final Rectangle r = (Rectangle) b.getShape();
		assertEquals(70, b.getX());
		assertEquals(30, b.getY());
		assertEquals(80, r.getWidth());
		assertEquals(120, r.getHeight());
	}

	@Test
	public void testFilled() {
		final Location b = simpleFill.accept(v);
		final Rectangle r = (Rectangle) b.getShape();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		assertEquals(80, r.getWidth());
		assertEquals(120, r.getHeight());
	}

	@Test
	public void testStroke() {
		final Location b = simpleStroke.accept(v);
		final Rectangle r = (Rectangle) b.getShape();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		assertEquals(80, r.getWidth());
		assertEquals(120, r.getHeight());
	}

	@Test
	public void testGroupSimple() {
		Location b = simpleGroup.accept(v);
		Rectangle r = (Rectangle) b.getShape(); // having issues with this test
		assertEquals(150, b.getX());
		assertEquals(50, b.getY());
		assertEquals(350, r.getWidth());
		assertEquals(300, r.getHeight()); // not passing??
		//faiz, check and see why this is not passing
	}

	@Test
	public void testGroupComplex() {
		final Location b = complexGroup.accept(v);
		final Rectangle r = (Rectangle) b.getShape();
		assertEquals(30, b.getX());
		assertEquals(80, b.getY());
		assertEquals(470, r.getWidth());
		assertEquals(320, r.getHeight());
	}

	@Test
	public void testOutline() {
		final Location b = simpleOutline.accept(v);
		final Rectangle r = (Rectangle) b.getShape();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		assertEquals(80, r.getWidth());
		assertEquals(120, r.getHeight());
	}

	@Test
	public void testPolygon() {
		final Location b = simplePolygon.accept(v);
		final Rectangle r = (Rectangle) b.getShape();
		assertEquals(50, b.getX());
		assertEquals(50, b.getY());
		assertEquals(70, r.getWidth());
		assertEquals(60, r.getHeight());
	}
}
