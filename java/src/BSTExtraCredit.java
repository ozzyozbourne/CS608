import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

public final class BSTExtraCredit {

    // this class contains only static fuctions no need to create an object
    private BSTExtraCredit() {

    }

    public static void main(final String... args) {
        final List<Integer> nodeCountList = getNodeCountList(args);
        for (final int nodeCount : nodeCountList) {

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting search time of skewed and balancedBST of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

            final TreeMap<Integer, Integer> balancedBST = generateRightSkewedBST(nodeCount);
            final TreeMap<Integer, Integer> rightSkewedBST = generateRightSkewedBST(nodeCount);

            searchFirstValue(rightSkewedBST, balancedBST, 1);
            searchMiddleValue(rightSkewedBST, balancedBST, nodeCount / 2);
            searchLastValue(rightSkewedBST, balancedBST, nodeCount);
            searchValueNotInBST(rightSkewedBST, balancedBST, nodeCount + 1);

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("\nTesting completed for skewed and balancedBST of size -> " + nodeCount + "\n");
            System.out.println("------------------------------------------------------------------------------");

        }

    }

    private static void searchLastValue(final TreeMap<Integer, Integer> rightSkewedBST,
            final TreeMap<Integer, Integer> balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching last value in Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = rightSkewedBST.containsKey(key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching last value in balanced BST-> " + key);
        startTime = System.nanoTime();
        res = balancedBST.containsKey(key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchValueNotInBST(final TreeMap<Integer, Integer> rightSkewedBST,
            final TreeMap<Integer, Integer> balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching a value not present in the Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = rightSkewedBST.containsKey(key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching a value not present in the Balanced BST-> " + key);
        startTime = System.nanoTime();
        res = balancedBST.containsKey(key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchMiddleValue(final TreeMap<Integer, Integer> rightSkewedBST,
            final TreeMap<Integer, Integer> balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching middle value of input range in Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = rightSkewedBST.containsKey(key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching middle value of input range in the Balanced BST-> " + key);
        startTime = System.nanoTime();
        res = balancedBST.containsKey(key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    private static void searchFirstValue(final TreeMap<Integer, Integer> rightSkewedBST,
            final TreeMap<Integer, Integer> balancedBST,
            final int key) {
        System.out.println("****************************************************\n");
        System.out.println("Searching first value of input range in Right Skewed BST -> " + key);
        var startTime = System.nanoTime();
        var res = rightSkewedBST.containsKey(key);
        var endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

        System.out.println("Searching first value of input range in the Balanced BST-> " + key);
        startTime = System.nanoTime();
        res = balancedBST.containsKey(key);
        endTime = System.nanoTime();
        System.out.println("Time taken in nano seconds -> " + (endTime - startTime));
        System.out.println("Value found -> " + res);
        System.out.println("\n****************************************************\n");

    }

    /****
     *
     *
     * @param args
     * @return
     */
    private static List<Integer> getNodeCountList(final String... args) {
        if (args.length == 0)
            throw new RuntimeException("Please enter the range of x");
        final List<Integer> input = new ArrayList<>();
        for (final String arg : args)
            input.add(Integer.parseInt(arg));
        return input;
    }

    /****
     *
     *
     * @param nodeCount
     * @return
     */
    private static TreeMap<Integer, Integer> generateRightSkewedBST(final int nodeCount) {
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 1; i <= nodeCount; i++)
            treeMap.put(i, i);
        return treeMap;
    }

}
