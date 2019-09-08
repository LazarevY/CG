package common.primitives;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Line extends Line2D {

    private Point point1;
    private Point point2;

    public double getX1() {
        return point1.getX();
    }

    public double getY1() {
        return point1.getY();
    }

    public Point2D getP1() {
        return point1;
    }

    public double getX2() {
        return point2.getX();
    }

    public double getY2() {
        return point2.getY();
    }

    public Point2D getP2() {
        return point2;
    }

    public void setLine(double x1, double y1, double x2, double y2) {
        point1.setLocation(x1,y1);
        point2.setLocation(x2, y2);
    }

    public double getLength(){
        Point t = point1.binSub(point2);
        return Math.sqrt(Math.pow(t.getX(), 2) + Math.pow(t.getY(), 2));
    }

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Rectangle2D getBounds2D() {
        return null;
    }
}
