package Task1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Task1 task1 = new Task1(); // init class Task1.Task1 to start Task-1
        System.out.println("The first task of Implemica");

        // init the variable N (count of opening and closing brackets) with
        // the result of the enter count of brackets function
        int N = task1.enterCountOfBrackets();

        task1.generateExamples(N); // init the generate examples function
    }

    // Enter the value of the count of brackets
    private int enterCountOfBrackets() {
        while(true) {
            System.out.println("Please, enter a count of pairs of brackets (N):");
            System.out.print("N = ");

            try { // if something goes incorrect, catch the exception
                int N = new Scanner(System.in).nextInt(); // init the N variable with the result of user's input

                if (N < 0) // if user entered the negative value, throw exception
                    throw new Exception();

                System.out.println(); // do free space between lines

                return N; // return the user's input value
            } catch (Exception e) {
                System.out.println("Incorrect data! Try again.\n");
            }
        }
    }

    // Generate visualisation of examples
    private void generateExamples(int N) {
        // N * 2 - total brackets
        char[] brackets = "(".repeat(N * 2).toCharArray(); // init the array of brackets with brackets equals the N variable

        int countOfCorrectExamples = 0; // the number of correct examples out of all examples
        int i = 0; // the number of selected bracket

        while(true) {
            if (i >= N * 2) { // if the number of selected bracket is max
                countOfCorrectExamples += handleResult(brackets); // increase the variable

                break; // stop looping
            }
            else if (brackets[i] == '(') { // if the char have an opening bracket change to a closing bracket
                countOfCorrectExamples += handleResult(brackets); // increase the variable if the brackets are correct

                brackets[i] = ')'; // change the char to a closing bracket

                if (i > 0) { // if the current number out of brackets is bigger than zero
                    for (int j = 0; j < i; j++) { // change all previous brackets to opening brackets
                        brackets[j] = '('; // set the char an opening column
                    }
                }

                i = 0; // Reset the number out of brackets to the first number out of brackets
            } else // if the char does not equal an opening bracket
                i += 1; // increase the number of the current bracket
        }

        System.out.println("\nCount of correct examples: " + countOfCorrectExamples);
    }

    // Check the example for correctness
    private boolean isCorrectExample(char[] brackets) {
        int countOfOpeningBrackets = 0; // The counter of opening brackets

        if (brackets[0] == ')') { // if the first bracket equals a closing bracket then the example is incorrect
            return false;
        } else {
            for (char bracket : brackets) { // check all brackets
                // if the counter is negative it means that closing brackets is bigger
                // in the beginning than opening brackets so the example is wrong
                if (countOfOpeningBrackets < 0)
                    return false;

                if (bracket == '(') // if the bracket equals an opening bracket then increase the counter
                    countOfOpeningBrackets += 1;
                else // else decrease the counter
                    countOfOpeningBrackets -= 1;
            }
        }

        return countOfOpeningBrackets == 0; // if counter equals zero then the example is correct
    }

    private int handleResult(char[] columns) {
        boolean isCorrect = isCorrectExample(columns); // check the example for correctness

        System.out.print(columns); // print out the example
        System.out.println(" - " + (isCorrect ? "correct" : "incorrect"));

        return isCorrect ? 1 : 0;
    }
}