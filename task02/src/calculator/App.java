package calculator;

import java.io.Console;
import java.util.Scanner;

//java -cp classes calculator.App
public class App {
    public static void main(String[] args) {
        System.out.println("<number> <operator> <number>");

        Scanner sc =  new Scanner(System.in);
        String userInput = "";
        System.out.println("Welcome.");
        while (true) {
            userInput = sc.nextLine();    // reads a line of input
            userInput = userInput.trim(); // clean up the whitespaces
            System.out.println("> " + userInput);
            if ("exit".equals(userInput)) // check if it's an exit to get out of the calculator
                break;
            }
            System.out.println(userInput);
            //init the last var and replace value first
            // String lastVal = "";
            // if (userInput.contains("$last")){
            //     userInput.replace("$last", lastVal);
            // }

            // if (lastVal.equals("")){
            //     System.out.println("Sorry, no last value. Do a calculation first.");
            // }
            //break the input into the different commands
            String[] commands = userInput.split(" ");
            System.out.println(commands[0]);
            Double firstDigit = Double.valueOf(commands[0]);
            System.out.println(firstDigit);
            Double secondDigit = Double.valueOf(commands[2]);
            System.out.println(secondDigit);

            Integer result;

            //evaluate expression
            switch(commands[1]){
                case "+":
                    result = (int)(firstDigit + secondDigit);
                    System.out.println(result);
                    // lastVal = Integer.toString(result);
                    break;
                case "-":
                 result = (int)(firstDigit + secondDigit);
                System.out.println(result);
                // lastVal = Integer.toString(result);
                    break;
                case "/":
                 result = (int)(firstDigit + secondDigit);
                System.out.println(result);
                // lastVal = Integer.toString(result);
                    break;
                case "*":
                 result = (int)(firstDigit + secondDigit);
                System.out.println(result);
                // lastVal = Integer.toString(result);
                    break;
            }

    }
}
