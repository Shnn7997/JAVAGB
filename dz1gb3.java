import java.util.Scanner;
import java.util.Stack;

public class dz1gb3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();

        double result = evaluateExpression(expression);
        System.out.println("Результат: " + result);
    }

    private static double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", ""); // удаляем пробелы

        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double operand2 = operands.pop();
                    double operand1 = operands.pop();
                    char operator = operators.pop();
                    double result = applyOperator(operator, operand1, operand2);
                    operands.push(result);
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Некорректное выражение");
                }
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && operators.peek() != '(' && hasHigherPrecedence(operators.peek(), ch)) {
                    double operand2 = operands.pop();
                    double operand1 = operands.pop();
                    char operator = operators.pop();
                    double result = applyOperator(operator, operand1, operand2);
                    operands.push(result);
                }
                operators.push(ch);
            } else if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                while (i + 1 < expression.length() && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    sb.append(expression.charAt(i + 1));
                    i++;
                }
                double operand = Double.parseDouble(sb.toString());
                operands.push(operand);
            } else {
                throw new IllegalArgumentException("Некорректный символ: " + ch);
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = operands.pop();
            double operand1 = operands.pop();
            char operator = operators.pop();
            double result = applyOperator(operator, operand1, operand2);
            operands.push(result);
        }

        if (operands.size() != 1 || !operators.isEmpty()) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        return operands.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return true;
        } else {
            return false;
        }
    }

    private static double applyOperator(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }
}
