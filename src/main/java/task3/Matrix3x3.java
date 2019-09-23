package task3;

import java.util.Arrays;

public class Matrix3x3 {
    private static final int SIZE = 3;
    private double[][] data;

    public Matrix3x3() {
        this.data = new double[SIZE][SIZE];
    }

    public Matrix3x3(double m11, double m12, double m13,
                     double m21, double m22, double m23,
                     double m31, double m32, double m33) {
        this.data = new double[][]{{m11, m12, m13},
                {m21, m22, m23},
                {m31, m32, m33}};
    }

    public Matrix3x3(final Matrix3x3 m) {
        this.data = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            data[i] = Arrays.copyOf(m.data[i], SIZE);
    }

    public double elementAt(int row, int col){
        return data[row][col];
    }

    public void setElement(double value, int row, int col){
        data[row][col] = value;
    }

    public static Matrix3x3 translate(Matrix3x3 matrix, double x, double y) {
        Matrix3x3 res = new Matrix3x3(matrix);
        res.data[0][2] += x;
        res.data[1][2] += y;
        return res;
    }

    public static Matrix3x3 translate(double x, double y) {
        return new Matrix3x3(1, 0, x,
                0, 1, y,
                0, 0, 1);
    }

    public static Matrix3x3 scale(Matrix3x3 matrix, double x, double y) {
        Matrix3x3 res = new Matrix3x3(matrix);
        res.data[0][0] *= x;
        res.data[1][1] *= y;
        return res;
    }

    public static Matrix3x3 scale(double x, double y) {
        return new Matrix3x3(x, 0, 0,
                0, y, 0,
                0, 0, 1);
    }

    public static Matrix3x3 rotate(Matrix3x3 matrix3x3, double angle) {
        double cost = Math.cos(angle);
        double sint = Math.sin(angle);

        Matrix3x3 matrix = new Matrix3x3(matrix3x3);

        double m11 = matrix.data[0][0], m12 = matrix.data[0][1];
        double m21 = matrix.data[1][0], m22 = matrix.data[1][1];

        matrix.data[0][0] = cost * m11 - sint * m21;
        matrix.data[0][1] = cost * m12 - sint * m22;
        matrix.data[1][0] = sint * m11 + cost * m21;
        matrix.data[1][1] = sint * m12 + cost * m22;

        return matrix;
    }

    public static Matrix3x3 rotate(double angle) {
        double cost = Math.cos(angle);
        double sint = Math.sin(angle);

        return new Matrix3x3(cost, -sint, 0,
                sint, cost, 0,
                0, 0, 1);
    }

    public void transpose() {
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < row; col++) {
                double t = data[row][col];
                data[row][col] = data[col][row];
                data[col][row] = t;
            }
    }

    public Matrix3x3 transposed(){
        return new Matrix3x3(data[0][0], data[1][0], data[2][0],
                             data[0][1], data[1][1], data[2][1],
                             data[0][2], data[1][2], data[2][2]);
    }

    public void inverse(){
        double a11 = data[1][1] * data[2][2] - data[2][1] * data[1][2];
        double a12 = -(data[1][0] * data[2][2] - data[2][0] * data[1][2]);
        double a13 = data[1][0] * data[2][1] - data[2][0] * data[1][1];

        double det = data[0][0] * a11 + data[0][1] * a12 + data[0][2] * a13;
        if (det == 0.f)
            return;
        double a21 = -(data[0][1] * data[2][2] - data[2][1] * data[0][2]);
        double a22 = data[0][0] * data[2][2] - data[2][0] * data[0][2];
        double a23 = -(data[0][0] * data[2][1] - data[2][0] * data[0][1]);

        double a31 = data[0][1] * data[1][2] - data[1][1] * data[0][2];
        double a32 = -(data[0][0] * data[1][2] - data[1][0] * data[0][2]);
        double a33 = data[0][0] * data[1][1] - data[1][0] * data[0][1];

        data[0][0] = a11 / det;
        data[0][1] = a21 / det;
        data[0][2] = a31 / det;

        data[1][0] = a12 / det;
        data[1][1] = a22 / det;
        data[1][2] = a32 / det;

        data[2][0] = a13 / det;
        data[2][1] = a23 / det;
        data[2][2] = a33 / det;
    }

    public Matrix3x3 inversed(){
        double a11 = data[1][1] * data[2][2] - data[2][1] * data[1][2];
        double a12 = -(data[1][0] * data[2][2] - data[2][0] * data[1][2]);
        double a13 = data[1][0] * data[2][1] - data[2][0] * data[1][1];

        double det = data[0][0] * a11 + data[0][1] * a12 + data[0][2] * a13;
        if (det == 0.d)
            return new Matrix3x3();
        double a21 = -(data[0][1] * data[2][2] - data[2][1] * data[0][2]);
        double a22 = data[0][0] * data[2][2] - data[2][0] * data[0][2];
        double a23 = -(data[0][0] * data[2][1] - data[2][0] * data[0][1]);

        double a31 = data[0][1] * data[1][2] - data[1][1] * data[0][2];
        double a32 = -(data[0][0] * data[1][2] - data[1][0] * data[0][2]);
        double a33 = data[0][0] * data[1][1] - data[1][0] * data[0][1];

        return new Matrix3x3(a11 / det, a21 / det, a31 / det,
                             a12 / det, a22 / det, a32 / det,
                             a13 / det, a23 / det, a33 / det);
    }


    public static Matrix3x3 createSingular(){
        return new Matrix3x3(1, 0,  0,
                             0, 1,  0,
                             0, 0,  1);
    }

    public static Matrix3x3 multiple(Matrix3x3 left, Matrix3x3 right){
        Matrix3x3 res = new Matrix3x3();

        for (int k = 0; k < SIZE; k++) {
            for (int row = 0; row < SIZE; row++) {
                double sum = 0;
                for (int col = 0; col < SIZE; col++) {
                    sum += left.data[k][col] * right.data[col][row];
                }
                res.data[k][row] = sum;
            }
        }
        return res;
    }

    public static Vector3 multiple(Matrix3x3 m, Vector3 v){
        Vector3 res = new Vector3();

        for (int row = 0; row < SIZE; row++) {
            double sum = 0;
            for (int col = 0; col < SIZE; col++)
                sum += m.data[row][col] * v.elementAt(col);
            res.setElement(sum, row);
        }
        return res;
    }

}
