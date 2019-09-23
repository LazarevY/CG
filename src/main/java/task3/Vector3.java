package task3;


import java.lang.reflect.Array;
import java.util.Arrays;

public class Vector3 {
    private static final int SIZE = 3;
    private double[] data;

    public Vector3(){
        data = new double[SIZE];
    }

    public Vector3(double x, double y, double z){
        data = new double[]{x, y, z};
    }

    public Vector3(Vector3 v){
        data = Arrays.copyOf(v.data, SIZE);
    }

    public double getLength(){
        return Math.sqrt(data[0] * data[0] + data[1] * data[1] + data[2] * data[2]);
    }

    public Vector3 getNormalized(){
        double length = getLength();
        if(length == 0d)
            return new Vector3();
        return new Vector3(data[0]/length, data[1]/length, data[2]/length);
    }

    public final double[] getData(){
        return Arrays.copyOf(data, SIZE);
    }

    public double elementAt(int index){
        return data[index];
    }

    public void setElement(double value, int index){
        data[index] = value;
    }

    public void normalize(){
        double length = getLength();
        if(length == 0d)
            return;
        for (int i = 0; i < SIZE; i++)
            data[i] /= length;
    }
}
