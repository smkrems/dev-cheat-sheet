package lin.louis.collection;

import lombok.Data;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author llin
 * @created 14/05/14.
 */
public class ArrayListOrLinkedList {
    private static final int MAX_INTERATION = 1000000;

    private static final int NB_COMPARAISON = 100;

    private List<Foo> arrayList = new ArrayList<>(MAX_INTERATION);

    private List<Foo> linkedList = new LinkedList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < MAX_INTERATION; i++) {
            arrayList.add(new Foo());
            linkedList.add(new Foo());
        }
    }

    /**
     * ArrayList is the winner
     */
    @Test
    @Ignore
    public void testPerformanceForLoop() {
        int nbTestArraySlowerLinked = 0;
        int nbTestLinkedSlowerArray = 0;
        for (int i = 0; i < NB_COMPARAISON; i++) {
            long arrayListStartTime = System.currentTimeMillis();
            for (int j = 0; j < MAX_INTERATION; j++) {
                Integer id = arrayList.get(i).getId();
            }
            long arrayListEndTime = System.currentTimeMillis();
            long arrayListExecTime = arrayListEndTime - arrayListStartTime;

            long linkedListStartTime = System.currentTimeMillis();
            for (int j = 0; j < MAX_INTERATION; j++) {
                Integer id = linkedList.get(i).getId();
            }
            long linkedListEndTime = System.currentTimeMillis();
            long linkedListExecTime = linkedListEndTime - linkedListStartTime;

            if (linkedListExecTime - arrayListExecTime < 0) {
                nbTestArraySlowerLinked++;
            } else {
                nbTestLinkedSlowerArray++;
            }
        }

        displayResult(nbTestArraySlowerLinked, nbTestLinkedSlowerArray);
    }

    /**
     * ArrayList is the winner
     */
    @Test
    @Ignore
    public void testPerformanceForEachLoop() {
        int nbTestArraySlowerLinked = 0;
        int nbTestLinkedSlowerArray = 0;
        for (int i = 0; i < NB_COMPARAISON; i++) {
            long arrayListStartTime = System.currentTimeMillis();
            for (Foo foo : arrayList) {
                Integer id = foo.getId();
            }
            long arrayListEndTime = System.currentTimeMillis();
            long arrayListExecTime = arrayListEndTime - arrayListStartTime;

            long linkedListStartTime = System.currentTimeMillis();
            for (Foo foo : linkedList) {
                Integer id = foo.getId();
            }
            long linkedListEndTime = System.currentTimeMillis();
            long linkedListExecTime = linkedListEndTime - linkedListStartTime;

            if (linkedListExecTime - arrayListExecTime < 0) {
                nbTestArraySlowerLinked++;
            } else {
                nbTestLinkedSlowerArray++;
            }
        }

        displayResult(nbTestArraySlowerLinked, nbTestLinkedSlowerArray);
    }

    /**
     * ArrayList is the winner
     */
    @Test
    @Ignore
    public void testPerformanceIteratorLoop() {
        int nbTestArraySlowerLinked = 0;
        int nbTestLinkedSlowerArray = 0;
        for (int i = 0; i < NB_COMPARAISON; i++) {
            long arrayListStartTime = System.currentTimeMillis();
            Iterator<Foo> iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                Integer id = iterator.next().getId();
            }
            long arrayListEndTime = System.currentTimeMillis();
            long arrayListExecTime = arrayListEndTime - arrayListStartTime;

            long linkedListStartTime = System.currentTimeMillis();
            iterator = linkedList.iterator();
            while (iterator.hasNext()) {
                Integer id = iterator.next().getId();
            }
            long linkedListEndTime = System.currentTimeMillis();
            long linkedListExecTime = linkedListEndTime - linkedListStartTime;

            if (linkedListExecTime - arrayListExecTime < 0) {
                nbTestArraySlowerLinked++;
            } else {
                nbTestLinkedSlowerArray++;
            }
        }

        displayResult(nbTestArraySlowerLinked, nbTestLinkedSlowerArray);
    }


    private void displayResult(final int nbTestArraySlowerLinked, final int nbTestLinkedSlowerArray) {
        System.out.println("Nb test where ArrayList is slower than LinkedList: " + nbTestArraySlowerLinked);
        System.out.println("Nb test where LinkedList is slower than ArrayList: " + nbTestLinkedSlowerArray);
        String fasterList = nbTestLinkedSlowerArray - nbTestArraySlowerLinked > 0 ? "ArrayList" : "LinkedList";
        System.out.println("The fastest is: " + fasterList);
    }

    @Data
    public class Foo implements Serializable {
        private static final long serialVersionUID = -7103199952150233156L;
        private Integer id;
    }
}
