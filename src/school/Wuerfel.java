package school;

import java.util.Random;

public class Wuerfel {
    private int a; //untere Grenze
    private int b; //obere Grenze
    private Random r;
    
    public Wuerfel() {
        a = 1;
        b = 6;
        r = new Random();
    }
    
    public Wuerfel(int von, int bis) {
        a = von;
        b = bis;
    }

    public int wuerfeln() {
        return r.nextInt(b) + a;
    }
}