import java.util.*;

public class PhoneSort {
    public static void main(String[] args) {
        Map<String, List<String>> phoneBook = new HashMap<>();

        // Заполнение телефонной книги
        phoneBook.put("Иван Иванов", Arrays.asList("111-111-111", "222-222-222"));
        phoneBook.put("Светлана Петрова", Collections.singletonList("333-333-333"));
        phoneBook.put("Кристина Белова", Collections.singletonList("444-444-444"));
        phoneBook.put("Анна Мусина", Arrays.asList("555-555-555", "666-666-666"));
        phoneBook.put("Анна Крутова", Arrays.asList("777-777-777", "888-888-888"));
        phoneBook.put("Иван Юрин", Collections.singletonList("999-999-999"));
        phoneBook.put("Петр Лыков", Collections.singletonList("000-000-000"));
        phoneBook.put("Павел Чернов", Arrays.asList("111-222-333", "444-555-666"));
        phoneBook.put("Петр Чернышов", Collections.singletonList("777-888-999"));
        phoneBook.put("Мария Федорова", Collections.singletonList("000-111-222"));
        phoneBook.put("Марина Светлова", Arrays.asList("333-444-555", "666-777-888"));
        phoneBook.put("Мария Савина", Collections.singletonList("999-000-111"));
        phoneBook.put("Мария Рыкова", Collections.singletonList("222-333-444"));
        phoneBook.put("Марина Лугова", Collections.singletonList("555-666-777"));
        phoneBook.put("Анна Владимирова", Collections.singletonList("888-999-000"));
        phoneBook.put("Иван Мечников", Collections.singletonList("111-222-333"));
        phoneBook.put("Петр Петин", Collections.singletonList("444-555-666"));
        phoneBook.put("Иван Ежов", Collections.singletonList("777-888-999"));

        // Вывод телефонной книги
        System.out.println("Телефонная книга:");
        for (String name : phoneBook.keySet()) {
            System.out.print(name + ": ");
            List<String> phones = phoneBook.get(name);
            for (int i = 0; i < phones.size(); i++) {
                System.out.print(phones.get(i));
                if (i < phones.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        // Поиск повторяющихся имен и количества повторений
        Map<String, Integer> nameCount = new HashMap<>();
        for (String name : phoneBook.keySet()) {
            nameCount.put(name, phoneBook.get(name).size());
        }

        // Сортировка по убыванию популярности
        List<Map.Entry<String, Integer>> sortedNames = new ArrayList<>(nameCount.entrySet());
        sortedNames.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // Вывод результатов
        System.out.println("\nПовторяющиеся имена:");
        for (Map.Entry<String, Integer> entry : sortedNames) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        // Сортировка методом пирамидальной сортировки
        heapSort(sortedNames);

        // Вывод результатов
        System.out.println("\nСортировка методом пирамидальной сортировки:");
        for (Map.Entry<String, Integer> entry : sortedNames) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void heapSort(List<Map.Entry<String, Integer>> list) {
        int n = list.size();
        // Строим кучу
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        // Один за другим извлекаем элементы из кучи
        for (int i = n - 1; i > 0; i--) {
            // Перемещаем текущий корень в конец
            Map.Entry<String, Integer> temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);

            // Вызываем max heapify на уменьшенной куче
            heapify(list, i, 0);
        }
    }

    private static void heapify(List<Map.Entry<String, Integer>> list, int n, int i) {
        int largest = i; // Инициализируем наибольший элемент как корень
        int l = 2 * i + 1; // Левый потомок
        int r = 2 * i + 2; // Правый потомок

        // Если левый потомок больше корня
        if (l < n && list.get(l).getValue() > list.get(largest).getValue()) {
            largest = l;
        }

        // Если правый потомок больше, чем самый большой элемент на данный момент
        if (r < n && list.get(r).getValue() > list.get(largest).getValue()) {
            largest = r;
        }

        // Если наибольший элемент не корень
        if (largest != i) {
            Map.Entry<String, Integer> temp = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, temp);

            // Рекурсивно heapify поддерево
            heapify(list, n, largest);
        }
    }
}