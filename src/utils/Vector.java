package utils;

import java.awt.Point;

/**
 * a 2 dimensional vector made with 2 floats can represent position or movement
 * of objects, such as the player or gravity
 * 
 * TODO position of all entities and movement should be represented by vector
 */
public class Vector {
	public double x, y;

	/**
	 * sets x & y to 0
	 */
	public Vector() {
		this.x = 0;
		this.y = 0;
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param r
	 * @param theta
	 *            must be in radians
	 */
	public Vector(Double r, Double theta) {
		x = r * Math.cos(theta);
		y = r * Math.sin(theta);
	}

	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * get resultant
	 * 
	 * @param v1
	 * @param v2
	 * @return The resultant vector
	 */
	public static Vector add(Vector v1, Vector v2) {
		return new Vector(v1.x + v2.x, v1.y + v2.y);

	}

	public Vector scale(double scalar) {
		return new Vector(x * scalar, y * scalar);
	}

	public Vector addTo(Vector other) {
		return new Vector(this.x + other.x, this.y + other.y);
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * @return angle in radians from -pi to pi
	 */
	public double getAngle() {
		return Math.atan2(y, x);
	}

	public boolean equals(Vector other) {
		return this.x == other.x && this.y == other.y;
	}

	public boolean isZero() {
		return this.x == 0 && this.y == 0;
	}

	/**
	 * 
	 * @param r
	 * @param theta
	 *            in radians PLZ
	 */
	public void setRTheta(double r, double theta) {
		x = r * Math.cos(theta);
		y = r * Math.sin(theta);
	}

	public String toString() {
		return "<" + x + "," + y + ">";
	}

	public String toStringInt() {
		return "<" + ((int) x) + "," + ((int) y) + ">";
	}

	/**
	 * rotates the vector 180 degrees, or flips the vector across the origin.
	 */
	public void flip() {
		setPos(-x, -y);
	}

	public static Vector pointToVector(Point p) {
		return new Vector(p.getX(), p.getY());
	}

	public static Vector getVector(Vector v1, Vector v2) {
		return new Vector(v2.x - v1.x, v2.y - v1.y);
	}

	public static Vector getVector(Point p1, Point p2) {
		return new Vector(p2.x - p1.x, p2.y - p1.y);
	}
}
