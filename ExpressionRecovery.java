import java.util.Scanner;

public class ExpressionRecovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос известных частей чисел и значения c
        System.out.print("Введите известную часть числа a (два символа, ? обозначает неизвестную цифру): ");
        String aStr = scanner.nextLine();
        while (!isValidNumber(aStr)) {
            System.out.print("Некорректный ввод. Повторите попытку: ");
            aStr = scanner.nextLine();
        }
        System.out.print("Введите известную часть числа b (два символа, ? обозначает неизвестную цифру): ");
        String bStr = scanner.nextLine();
        while (!isValidNumber(bStr)) {
            System.out.print("Некорректный ввод. Повторите попытку: ");
            bStr = scanner.nextLine();
        }
        System.out.print("Введите значение c: ");
        int c = scanner.nextInt();

        // Перебор всех возможных значений для неизвестных цифр в a и b
        boolean foundSolution = false;
        for (int i = 0; i <= 9; i++) {
            String a = aStr.replace("?", Integer.toString(i));
            if (!isValidNumber(a)) {
                continue;
            }
            for (int j = 0; j <= 9; j++) {
                String b = bStr.replace("?", Integer.toString(j));
                if (!isValidNumber(b)) {
                    continue;
                }
                if (Integer.parseInt(a) + Integer.parseInt(b) == c) {
                    // Вывод найденного решения
                    System.out.println("Решение найдено:");
                    System.out.println(a + " + " + b + " = " + c);
                    foundSolution = true;
                }
            }
        }

        if (!foundSolution) {
            System.out.println("Решения не найдено.");
        }
    }

    // Проверка, что строка является корректным двузначным числом с неизвестной цифрой
    private static boolean isValidNumber(String str) {
        if (str.length() != 2) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (c != '?' && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}