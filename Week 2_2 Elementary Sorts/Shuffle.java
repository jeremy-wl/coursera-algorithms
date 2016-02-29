import java.util.Arrays;

/**
 * Created by Jeremy on 2/28/16.
 */
public class Shuffle {

    public static void shuffle(Comparable[] a) {

        for (int i = 0; i < a.length; i++) {
            int r = StdRandom.uniform(i+1); // Returns an integer uniformly between 0 (inclusive) and N (exclusive).
            Utils.exchange(a, r, i);
        }

    }

}
