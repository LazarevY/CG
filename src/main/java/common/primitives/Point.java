package common.primitives;

import java.awt.geom.Point2D;

public class Point extends Point2D {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(){
        this(0,0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point binSub(Point other){
        return new Point(x - other.x, y - other.y);
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
