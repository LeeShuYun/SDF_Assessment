package calculator;

import java.io.Console;

//java -cp classes calculator.App
public class App {
    public static void main(String[] args) {
        // System.out.println("<number> <operator> <number>");
        
        // reading the user input
        Console cons = System.console();
        String userInput = "";
        System.out.println("Welcome.");

        // prep work for calculations
        Float result;
        String lastVal = "0"; // any last calculated values are here

        // loop to take inputs infinitely until exit is entered
        while (true) {
            // reading inputs
            userInput = cons.readLine("> ");
            userInput = userInput.trim();

            // check if it's an exit to get out of the calculator
            if ("exit".equals(userInput)) {
                System.out.println("Bye bye");
                break;
            }

            // checking for $last and replacing if any     
            if (userInput.contains("last")) {
                userInput = userInput.replace("$last", lastVal);
            }

            // break the input into the commands
            String[] commands = userInput.split(" ");
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
