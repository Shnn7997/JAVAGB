import java.util.Scanner;
import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class dz3gb3 {
    private static final String LOGFILENAME = "calc.log";

    private static PrintWriter logWriter;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            File logFile = new File(LOGFILENAME);
            logWriter = new PrintWriter(new FileWriter(logFile, true));
        } catch (IOException e) {
            System.out.println("Ошибка при открытии файла лога: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.print("Введите выражение (для выхода введите 'exit'): ");
            String expression = scanner.nextLine();

            if (expression.equals("exit")) {
                break;
            }

            try {
                double result = evaluateExpression(expression);
                System.out.println("Результат: " + result);
                logOperation(expression, result);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            System.out.println("Выберите действие:");
            System.out.println("1. Ввести новое выражение");
            System.out.println("2. Отменить вычисление предыдущего выражения");
            System.out.println("3. Завершить работу");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println("Отменяю вычисление предыдущего выражения...");
                    break;
                case 3:
                    System.out.println("Завершаю работу...");
                    logWriter.close();
                    return;
                default:
                    System.out.println("Некорректный выбор, повторите попытку");
            }
        }

        logWriter.close();
    }

    private static double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", ""); // удаляем пробелы

        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                int start = i;
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    i++;
                }
                double operand = Double.parseDouble(expression.substring(start, i));
                operands.push(operand);
                i--;
            } else if (isOperator(c)) {
                while (!operators.empty() && hasPrecedence(c, operators.peek())) {
                    double op2 = operands.pop();
                    double op1 = operands.pop();
                    char op = operators.pop();
                    double result = applyOperator(op, op1, op2);
                    operands.push(result);
                }
                operators.push(c);
            } else {
                throw new IllegalArgumentException("Некорректный символ в выражении: " + c);
            }
        }

        while (!operators.empty()) {
            double op2 = operands.pop();
            double op1 = operands.pop();
            char op = operators.pop();
            double result = applyOperator(op, op1, op2);
            operands.push(result);
        }

        return operands.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return true;
        } else {
            return false;
        }
    }

    private static double applyOperator(char op, double op1, double op2) {
        switch (op) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
            case '/':
                if (op2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                return op1 / op2;
            default:
                throw new IllegalArgumentException("Некорректный оператор: " + op);
        }
    }

    private static void logOperation(String expression, double result) {
        logWriter.println(expression + " = " + result);
    }
}