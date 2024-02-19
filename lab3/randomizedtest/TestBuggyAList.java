package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing correct = new AListNoResizing<>();
        BuggyAList broken = new BuggyAList<>();

        correct.addLast(4);
        correct.addLast(5);
        correct.addLast(6);

        broken.addLast(4);
        broken.addLast(5);
        broken.addLast(6);

        assertEquals(correct.size(),broken.size());

        assertEquals(correct.removeLast(),broken.removeLast());
        assertEquals(correct.removeLast(),broken.removeLast());
        assertEquals(correct.removeLast(),broken.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> testList = new BuggyAList<>();

        int N = 5000; // Number of operations
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                // AddLast operation
                int randVal = StdRandom.uniform(0, 100);
                correctList.addLast(randVal);
                testList.addLast(randVal);
            } else if (operationNumber == 1) {
                // Size operation
                int correctSize = correctList.size();
                int testSize = testList.size();
                assertEquals("Size operation failed", correctSize, testSize);
            } else if (operationNumber == 2) {
                // GetLast operation
                if (correctList.size() > 0) { // Check to avoid exceptions
                    int correctVal = correctList.getLast();
                    int testVal = testList.getLast();
                    assertEquals("GetLast operation failed", correctVal, testVal);
                }
            } else if (operationNumber == 3) {
                // RemoveLast operation
                if (correctList.size() > 0) { // Check to avoid exceptions
                    int correctVal = correctList.removeLast();
                    int testVal = testList.removeLast();
                    assertEquals("RemoveLast operation failed", correctVal, testVal);
                }
            }
        }
    }
}
