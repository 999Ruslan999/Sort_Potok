import java.util.Arrays;

public class ParallelSorter extends Thread implements Sorter {


    public static final int THREAD_COUNT = 2;

    private int[] array = new int[]{0, -1, 234, 1, 2};

    @Override
    public void sort(int[] array) {
        sortArray(new int[]{0, -1, 234, 1, 2});
    }


    public int[] sortArray(int[] arrayA) {
        if (arrayA == null) {
            return null;
        }

        if (arrayA.length < 2) {
            return arrayA;
        }

        int[] arrayB = new int[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);


        int[] arrayC = new int[arrayA.length - arrayA.length / 2];
        System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);


        arrayB = sortArray(arrayB);
        arrayC = sortArray(arrayC);


        return mergeArray(arrayB, arrayC);
    }


    public int[] mergeArray(int[] arrayA, int[] arrayB) {

        int[] arrayC = new int[]{arrayA.length + arrayB.length};
        int positionA = 0, positionB = 0;

        for (int i = 0; i < arrayC.length; i++) {
            if (positionA == arrayA.length) {
                arrayC[i] = arrayB[i - positionB];
                positionB++;
            } else if (positionB == arrayB.length) {
                arrayC[i] = arrayA[i - positionA];
                positionA++;
            } else if (arrayA[i - positionA] < arrayB[i - positionB]) {
                arrayC[i] = arrayA[i - positionA];
                positionB++;
            } else {
                arrayC[i] = arrayB[i - positionB];
                positionA++;
            }
        }
        return arrayC;

    }

    @Override
    public String toString() {
        return "ParallelSorter{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}