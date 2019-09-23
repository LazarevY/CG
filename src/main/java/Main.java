import task1.PaintingPanel;
import common.TaskViewer;
import task2.Panel;
import task3.Matrix3x3;


public class Main {
    public static void main(String[] args) {
        Matrix3x3 m1 = Matrix3x3.translate(20, 30);
        Matrix3x3 m2 = Matrix3x3.scale(2,4);

        Matrix3x3 m3 = Matrix3x3.multiple(m2, m1);

        Matrix3x3 m4 = Matrix3x3.rotate(Math.toRadians(90));
        System.out.println();
    }
}
