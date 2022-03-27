import java.util.Scanner;

public class InputParser {
    //take in as a String, returns true if can be parsed, false if can't
    //stupid and convoluted? or genius
    //TODO add more wrappers as needed
    private static boolean validInput(String input, Object obj) {
        boolean isValidVar = false;
        try {//checks if obj is diff types of wrapper class
            if(obj instanceof Integer) {
                Integer.parseInt(input);
            }
            if(obj instanceof Double) {

                Double.parseDouble(input);
            }
            if(obj instanceof Character) {
                return input.length() == 1;
            }
            isValidVar = true; //Should only be able to get here if no exceptions thrown
        } catch(Exception e) {
            //this catches any Format Exception and just returns false
            // which will redo the do-while loop in getValidInput();
            //lol if only === existed
        }
        return isValidVar;
    }

    /**
     * Will keep prompting user until input can be parsed to dataType (int, double, etc.)
     * Does not parse the input, only will return is input COULD be parsed
     * Can be used by InputParser.getValidInput
     */
    public static String getValidInput(String promptMessage, Object dataType) {
        return getValidInput(promptMessage, "That is an invalid input.", dataType);
    }
    public static String getValidInput(String promptMessage, String failMessage, Object dataType) {
        Scanner myScanner = new Scanner(System.in);
        String input;
        boolean isValid;

        do {
            System.out.print(promptMessage);
            input = myScanner.nextLine();
            isValid = validInput(input, dataType); //checks if input is the correct data type
            if(!isValid)
                System.out.println(failMessage);
        } while(!isValid);

        //myScanner.close();
        return input; //validated and ready to be parsed later
    }

}
