import java.util.Scanner;
import java.util.Stack;

public class PalindromeChecker {

    // Method to check palindrome using Stack
    public static boolean isPalindrome(String input) {
        Stack<Character> stack = new Stack<>();

        // Convert to lowercase and remove spaces
        input = input.toLowerCase().replaceAll("\\s+", "");

        // Push characters to stack
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        // Pop characters and build reversed string
        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        // Compare original and reversed
        return input.equals(reversed);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Palindrome Checker App =====");
        System.out.print("Enter a string: ");
        String text = scanner.nextLine();

        if (isPalindrome(text)) {
            System.out.println("Result: The string is a PALINDROME.");
        } else {
            System.out.println("Result: The string is NOT a palindrome.");
        }

        scanner.close();
    }
}