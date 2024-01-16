import java.util.Scanner;

public class MinMaxFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        System.out.println("Введите элементы массива через пробел:");

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        Thread minThread = new Thread(() -> {
            int min = findMin(array);
            System.out.println("Минимум в массиве: " + min);
        });

        Thread maxThread = new Thread(() -> {
            int max = findMax(array);
            System.out.println("Максимум в массиве: " + max);
        });

        minThread.start();
        maxThread.start();

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int findMin(int[] array) {
        int min = array[0];
        for (int value : array) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    private static int findMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
