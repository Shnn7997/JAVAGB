import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArrayListExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        // заполнение списка случайными числами
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }

        // вывод списка на экран
        System.out.println("Исходный список: " + list);

        // нахождение минимального, максимального и среднего значений
        int min = Collections.min(list);
        int max = Collections.max(list);
        double sum = 0;
        for (int i : list) {
            sum += i;
        }
        double average = sum / list.size();

        System.out.println("Минимальное значение: " + min);
        System.out.println("Максимальное значение: " + max);
        System.out.println("Среднее значение: " + average);

        // удаление четных чисел
        list.removeIf(x -> x % 2 == 0);
        System.out.println("Список после удаления четных чисел: " + list);

        // сортировка слиянием
        mergeSort(list, 0, list.size() - 1);
        System.out.println("Список после сортировки слиянием: " + list);
    }

    private static void mergeSort(List<Integer> list, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(list, start, mid);
            mergeSort(list, mid + 1, end);
            merge(list, start, mid, end);
        }
    }

    private static void merge(List<Integer> list, int start, int mid, int end) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (int i = start; i <= mid; i++) {
            leftList.add(list.get(i));
        }
        for (int i = mid + 1; i <= end; i++) {
            rightList.add(list.get(i));
        }

        int i = 0, j = 0, k = start;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i) <= rightList.get(j)) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftList.size()) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightList.size()) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}
