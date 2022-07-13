import java.lang.reflect.Array;
import java.util.ArrayList;

public class Test {

    public static void main (String[] args) {
        boolean[][] isme = new boolean[3][3];
        boolean[][] fuck = isme;
        isme = new boolean[4][3];

        System.out.println(fuck.length);
        System.out.println(isme.length);


    }
}
