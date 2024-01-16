import java.util.Arrays;
import java.util.Random;

public class MultiThreadSort {

    public static void main(String[] args) {
        int[] array = generateRandomArray();

        Thread insertionSortThread = new Thread(() -> {
            int[] sortedArray = insertionSort(array.clone());
            System.out.println("Сортировка вставками: " + Arrays.toString(sortedArray));
        });

        Thread selectionSortThread = new Thread(() -> {
            int[] sortedArray = selectionSort(array.clone());
            System.out.println("Сортировка выбором: " + Arrays.toString(sortedArray));
        });

        Thread bubbleSortThread = new Thread(() -> {
            int[] sortedArray = bubbleSort(array.clone());
            System.out.println("Сортировка пузырьком: " + Arrays.toString(sortedArray));
        });

        insertionSortThread.start();
        selectionSortThread.start();
        bubbleSortThread.start();
    }

    private static int[] generateRandomArray() {
        Random random = new Random();
        int size = random.nextInt(10) + 5;
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }

        return array;
    }

    private static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    private static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    private static int[] bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
        return array;
    }
}
