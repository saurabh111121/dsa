package DataStructures.Stacks;

import java.util.Stack;
import java.util.HashMap;

/*
===========================================================
Stack Applications & Classic Problems
===========================================================

1) Balanced Parentheses Check
2) Reverse a String using Stack
3) Evaluate Postfix Expression
4) Infix to Postfix Conversion
5) Next Greater Element
6) Previous Greater Element
7) Stock Span Problem
8) Min Stack (O(1) getMin)
9) Sort a Stack (using recursion)
10) Decode String (LeetCode 394)

===========================================================
*/

public class StackApplications {

    // =====================================================
    // 1️⃣ BALANCED PARENTHESES
    // =====================================================
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) return false;
            }
        }

        return stack.isEmpty();
    }

    // =====================================================
    // 2️⃣ REVERSE STRING USING STACK
    // =====================================================
    public static String reverseString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) stack.push(c);

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.toString();
    }

    // =====================================================
    // 3️⃣ EVALUATE POSTFIX EXPRESSION
    // =====================================================
    public static int evaluatePostfix(String expr) {
        Stack<Integer> stack = new Stack<>();

        for (String token : expr.split(" ")) {
            switch (token) {
                case "+": stack.push(stack.pop() + stack.pop()); break;
                case "-": { int b = stack.pop(); int a = stack.pop(); stack.push(a - b); } break;
                case "*": stack.push(stack.pop() * stack.pop()); break;
                case "/": { int b = stack.pop(); int a = stack.pop(); stack.push(a / b); } break;
                default:  stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    // =====================================================
    // 4️⃣ INFIX TO POSTFIX
    // =====================================================
    public static String infixToPostfix(String expr) {
        Stack<Character> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (char c : expr.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                output.append(c).append(' ');
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(' ');
                }
                if (!stack.isEmpty()) stack.pop(); // remove '('
            } else {
                // Operator
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    output.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) output.append(stack.pop()).append(' ');
        return output.toString().trim();
    }

    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        if (op == '^') return 3;
        return 0;
    }

    // =====================================================
    // 5️⃣ NEXT GREATER ELEMENT
    // =====================================================
    public static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : arr[stack.peek()];
            stack.push(i);
        }

        return result;
    }

    // =====================================================
    // 6️⃣ PREVIOUS GREATER ELEMENT
    // =====================================================
    public static int[] prevGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : arr[stack.peek()];
            stack.push(i);
        }

        return result;
    }

    // =====================================================
    // 7️⃣ STOCK SPAN PROBLEM
    // =====================================================
    public static int[] stockSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            span[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }

        return span;
    }

    // =====================================================
    // 8️⃣ MIN STACK - O(1) getMin
    // =====================================================
    static class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public int pop() {
            int val = stack.pop();
            if (val == minStack.peek()) minStack.pop();
            return val;
        }

        public int peek() { return stack.peek(); }

        public int getMin() { return minStack.peek(); }

        public boolean isEmpty() { return stack.isEmpty(); }
    }

    // =====================================================
    // 9️⃣ SORT A STACK (using recursion)
    // =====================================================
    public static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;

        int top = stack.pop();
        sortStack(stack);
        insertSorted(stack, top);
    }

    private static void insertSorted(Stack<Integer> stack, int val) {
        if (stack.isEmpty() || stack.peek() <= val) {
            stack.push(val);
            return;
        }
        int top = stack.pop();
        insertSorted(stack, val);
        stack.push(top);
    }

    // =====================================================
    // 🔟 DECODE STRING (LeetCode 394)
    // e.g. "3[a2[bc]]" -> "abcbcabcbcabcbc"
    // =====================================================
    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder current = new StringBuilder();
        int k = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(k);
                strStack.push(current);
                current = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                int count = countStack.pop();
                StringBuilder prev = strStack.pop();
                for (int i = 0; i < count; i++) prev.append(current);
                current = prev;
            } else {
                current.append(c);
            }
        }

        return current.toString();
    }

    // =====================================================
    // UTILITY
    // =====================================================
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        // Balanced Parentheses
        System.out.println("Balanced '({[]})': " + isBalanced("({[]})"));
        System.out.println("Balanced '([)]':   " + isBalanced("([)]"));
        System.out.println("Balanced '{[]}':   " + isBalanced("{[]}"));

        // Reverse String
        System.out.println("Reverse 'hello': " + reverseString("hello"));

        // Postfix Evaluation
        System.out.println("Postfix '5 1 2 + 4 * + 3 -': " + evaluatePostfix("5 1 2 + 4 * + 3 -"));

        // Infix to Postfix
        System.out.println("Infix 'a+b*(c^d-e)': " + infixToPostfix("a+b*(c^d-e)"));

        // Next Greater Element
        int[] arr = {4, 5, 2, 10, 8};
        System.out.print("Next Greater [4,5,2,10,8]: ");
        printArray(nextGreater(arr));

        // Previous Greater Element
        System.out.print("Prev Greater [4,5,2,10,8]: ");
        printArray(prevGreater(arr));

        // Stock Span
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        System.out.print("Stock Span: ");
        printArray(stockSpan(prices));

        // Min Stack
        MinStack minStack = new MinStack();
        minStack.push(5);
        minStack.push(3);
        minStack.push(7);
        minStack.push(2);
        System.out.println("MinStack getMin: " + minStack.getMin());
        minStack.pop();
        System.out.println("MinStack getMin after pop: " + minStack.getMin());

        // Sort Stack
        Stack<Integer> unsorted = new Stack<>();
        unsorted.push(3);
        unsorted.push(1);
        unsorted.push(4);
        unsorted.push(2);
        sortStack(unsorted);
        System.out.println("Sorted Stack (top is max): " + unsorted);

        // Decode String
        System.out.println("Decode '3[a2[bc]]': " + decodeString("3[a2[bc]]"));
        System.out.println("Decode '2[abc]3[cd]ef': " + decodeString("2[abc]3[cd]ef"));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Balanced Parentheses:  Time O(n),      Space O(n)
2) Reverse String:        Time O(n),      Space O(n)
3) Evaluate Postfix:      Time O(n),      Space O(n)
4) Infix to Postfix:      Time O(n),      Space O(n)
5) Next Greater Element:  Time O(n),      Space O(n)
6) Prev Greater Element:  Time O(n),      Space O(n)
7) Stock Span:            Time O(n),      Space O(n)
8) Min Stack push/pop:    Time O(1),      Space O(n)
   Min Stack getMin:      Time O(1),      Space O(1)
9) Sort Stack:            Time O(n²),     Space O(n)
10) Decode String:        Time O(n * k),  Space O(n)

===========================================================
*/
