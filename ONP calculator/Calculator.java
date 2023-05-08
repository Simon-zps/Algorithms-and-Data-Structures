public class Calculator {
    public static void main(String[] args) {
        String input = "5 * ( ( 3 - 7 ) * 2 - 3 * ( 5 + 1 ) ) - 3";
        System.out.println("Wartość wyrażenia: " + evaluateExpression(input));

        String input2 = "50 * 2 / 5 + 4";
        System.out.println("Wartość wyrażenia: " + evaluateExpression(input2));


        System.out.println("p\tq\tr\ts\t((p ∧ q) ∨ (r ⇒ s))");
        for (int p = 0; p <= 1; p++) {
            for (int q = 0; q <= 1; q++) {
                for (int r = 0; r <= 1; r++) {
                    for (int s = 0; s <= 1; s++) {
                        String input3 = String.format("%d ∧ %d ∨ ( %d ⇒ %d )", p, q, r, s);
                        int result = evaluateExpression(input3);
                        System.out.printf("%d\t%d\t%d\t%d\t%d\n", p, q, r, s, result);
                    }
                }
            }
        }
    }

    public static int evaluateExpression(String expression) {
        String postfixExpression = infixToPostfix(expression);
        System.out.println("Postfix expression: " +postfixExpression);
        return evaluatePostfix(postfixExpression);
    }

    public static String infixToPostfix(String expression) {
        StringBuilder postfix = new StringBuilder();
        Stos<Character> stack = new Stos<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    postfix.append(expression.charAt(i++));
                }
                postfix.append(' ');  // Dodaj spację jako separator liczb.
                i--;  // Skoryguj indeks po zakończeniu pętli while zeby nie przeskoczyc cyfry bo petla for i tak zwiekszy indeks o 1
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static int evaluatePostfix(String expression) {
        Stos<Integer> stack = new Stos<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i++) - '0');
                }
                stack.push(num);
                i--;  // Skoryguj indeks po zakończeniu pętli while.
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Nieprawidłowe wyrażenie");
                }

                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                    case '∧':
                        stack.push((val2 != 0 && val1 != 0) ? 1 : 0);
                        break;
                    case '∨':
                        stack.push((val2 != 0 || val1 != 0) ? 1 : 0);
                        break;
                    case '⇒':
                        stack.push((val2 == 0 || val1 != 0) ? 1 : 0);
                        break;
                    case '⇔':
                        stack.push((val2 == val1) ? 1 : 0);
                        break;
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Nieprawidłowe wyrażenie");
        }

        return stack.pop();
    }

// operator / operand

    public static int precedence(char operator) { // It's a method that returns the precedence of an
        // operator which is used to decide which operator to perform first if there are multiple operators in an expression.
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '∧':
            case '∨':
                return 4;
            case '⇒':
            case '⇔':
                return 5;
        }
        return -1;
    }
}

