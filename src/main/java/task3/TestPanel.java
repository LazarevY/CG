package task3;

import javax.swing.*;
import java.awt.*;

public class TestPanel extends JPanel {

    private Vector3[] points;
    private Matrix3x3 model;

    public TestPanel() {
        points = new Vector3[] {new Vector3(2,2, 1), new Vector3(-2, 2, 1), new Vector3(-2, -2, 1),
        new Vector3(2, -2, 1)};
        model = Matrix3x3.rotate(45);
        model = Matrix3x3.multiple(Matrix3x3.scale(30,30), model);
        model = Matrix3x3.multiple(Matrix3x3.translate(100, 200), model);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Vector3[] pointsT = new Vector3[points.length];
        Polygon p = new Polygon();
        for (int i = 0; i < points.length; i++) {
            pointsT[i] = Matrix3x3.multiple(model, points[i]);
            p.addPoint(((int) pointsT[i].elementAt(0)), ((int) pointsT[i].elementAt(1)));
        }
        g.drawPolygon(p);

    }
}
