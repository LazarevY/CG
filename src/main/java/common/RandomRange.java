package common;

import java.util.Random;

public class RandomRange extends Random {
    public RandomRange(){
        super();
    }

    public int getOfRange(int from, int to){
        return nextInt() % (to - from) + from;
    }
    public int negativeFactor(){
        return nextBoolean()? -1 : 1;
    }
}
