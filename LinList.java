import java.util.Iterator;
import java.util.LinkedList;

public class LinList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        list.add(17);
        list.add(18);
        list.add(19);
        list.add(20);

        System.out.println("Исходный список:");
        for (int element : list) {
            System.out.println(element);
        }

        LinkedList<Integer> reversedList = reverseLinkedList(list);

        System.out.println("Перевернутый список:");
        for (int element : reversedList) {
            System.out.println(element);
        }

        Queue<String> queue = new Queue<>();
        queue.enqueue("apple");
        queue.enqueue("banana");
        queue.enqueue("cherry");

        System.out.println("Метод first():");
        System.out.println(queue.first());

        System.out.println("Метод dequeue():");
        System.out.println(queue.dequeue());
        System.out.println("Очередь:");
        for (String element : queue) {
            System.out.println(element);
        }

        System.out.println("Метод dequeue():");
        System.out.println(queue.dequeue());
        System.out.println("Очередь:");
        for (String element : queue) {
            System.out.println(element);
        }

        System.out.println("Метод dequeue():");
        System.out.println(queue.dequeue());
        System.out.println("Очередь:");
        for (String element : queue) {
            System.out.println(element);
        }

        System.out.println("Метод isEmpty():");
        System.out.println(queue.isEmpty());
        System.out.println("Очередь:");
        for (String element : queue) {
            System.out.println(element);
        }
    }

    public static <T> LinkedList<T> reverseLinkedList(LinkedList<T> list) {
        LinkedList<T> reversedList = new LinkedList<>();
        for (T element : list) {
            reversedList.addFirst(element);
        }
        return reversedList;
    }
}

class Queue<T> implements Iterable<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void enqueue(T element) {
        list.addLast(element);
        System.out.println("Метод enqueue(" + element + "):");
        for (T e : list) {
            System.out.println(e);
        }
    }

    public T dequeue() {
        T element = list.removeFirst();
        System.out.println("Метод dequeue():");
        System.out.println(element);
        System.out.println("Очередь:");
        for (T e : list) {
            System.out.println(e);
        }
        return element;
    }

    public T first() {
        T element = list.getFirst();
        System.out.println("Метод first():");
        System.out.println(element);
        System.out.println("Очередь:");
        for (T e : list) {
            System.out.println(e);
        }
        return element;
    }

    public boolean isEmpty() {
        boolean result = list.isEmpty();
        System.out.println("Метод isEmpty():");
        System.out.println(result);
        System.out.println("Очередь:");
        for (T element : list) {
            System.out.println(element);
        }
        return result;
    }

    public int size() {
        return list.size();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}