import java.util.Hashtable;
import java.util.Random;

public final class AssociativeArrays {

    private AssociativeArrays() {

    }

    public static void main(final String... args) {
        // running for different value of n ie from 10^2 to 10^6
        for (final int n : new int[] { 100, 1000, 10000, 100000, 1000000 }) {
            // Creating a hash table of initial size 1000 with a load factor of 0.75 as
            // specified in the question
            final Hashtable<Integer, Integer> hashtable = new Hashtable<>(1000, 0.75f);
        }
    }

}
