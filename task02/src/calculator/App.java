package calculator;

import java.io.Console;
import java.util.Scanner;

//java -cp classes calculator.App
public class App {
    public static void main(String[] args) {
        // System.out.println("<number> <operator> <number>");

        // reading the user input
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        System.out.println("Welcome.");

        // prep work for calculations
        Float result;
        String lastVal = ""; // any last calculated values are here

        // loop to take inputs infinitely until exit is entered
        while (true) {
            // reading inputs
            userInput = sc.nextLine();
            userInput = userInput.trim();
            System.out.println("> " + userInput);
            if ("exit".equals(userInput)) {// check if it's an exit to get out of the calculator
                break;
            }

            // checking for $last
            String cleanInput = userInput;
            if (userInput.contains("last") && lastVal.equals("")) {
                System.out.println("Sorry, no last value. Do a calculation first.");
            } else {  //we have a $last to replace or no $last at all
                if (userInput.contains("last")) {
                    cleanInput = userInput.replace("$last", lastVal);
                } else { 
                    // there is no last, don't do anything
                }
                // break the input into the commands
                String[] commands = cleanInput.split(" ");
                // I thought of using Double because it works faster on chip but we don't need
                // the speed here.
                // Maybe with later iterations of calculators
                Float firstDigit = Float.valueOf(commands[0]);
                Float secondDigit = Float.valueOf(commands[2]);
                // evaluate expression
                switch (commands[1]) {
                    case "+":
                        result = firstDigit + secondDigit;
                        System.out.println(result);
                        lastVal = Float.toString(result);
                        break;
                    case "-":
                        result = firstDigit - secondDigit;
                        System.out.println(result);
                        lastVal = Float.toString(result);
                        break;
                    case "/":
                        result = firstDigit / secondDigit;
                        System.out.println(result);
                        lastVal = Float.toString(result);
                        break;
                    case "*":
                        result = firstDigit * secondDigit;
                        System.out.println(result);
                        lastVal = Float.toString(result);
                        break;

                }

            }

        }
    }
}
