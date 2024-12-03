package Task3;

import java.math.BigInteger;

public class Task3 {
    public static void main(String[] args) {
        new Task3().start(); // start Task 3
    }

    private void start() {
        BigInteger result = factorial(100); // Init a var with the result of the factorial function

        char[] chars = result.toString().toCharArray(); // convert the result to char array

        int total = 0; // total of the digits

        for (char c : chars) // iterate through the digits
            total += Integer.parseInt(String.valueOf(c)); // add the digit to total

        System.out.println(total); // output the total of the digits
    }

    // Find factorial of the number
    private BigInteger factorial(int n) {
        if (n > 1) // if n (number) is bigger than 1
            return BigInteger.valueOf(n).multiply(factorial(n - 1)); // return the result of the n * (n-1)!
        else // if n is equals or less than one
            return BigInteger.ONE; // return the digit one.
    }
}
