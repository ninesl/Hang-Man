public class HangMan {
    //Graphics, hangman updating based of guesses
    //Coming up with word, based off text file within game

    //no idea how to make this not as annoying
    //lives are equal to # of elements within FullMan
    private final static String []fullMan = {
            //_____ Each line is 5 long
            " ͟͟͟ \n" +
                    "|* *|\n" +
                    " ͞͞͞ ",  //0 head
            "\n  |  ",    //1 torso
            "\r<-|  ",  //2 left arm
            "\r<-|->",//3 right arm
            "\n ͞͞͞ ",  //4 waist
            "\n|    ",  //5 left leg
            "\r|   |" //6 right leg
    };

    private static void printCurrentMan(int lives) {
        for (int i = 0; i < lives; i++) {
            System.out.print(fullMan[i]);
        }
    }

    private static boolean hasLetter(String word, char c) {
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == c)
                return true;
        }
        return false;
    }

    private static char getGuess() {
        String failMessage = "Please enter a single letter.";
        String input = "";
        boolean isSingleLetter = false;
        while (!isSingleLetter) {
            input = InputParser.getValidInput("Please enter your guess : ", failMessage, 'c');
            if (!Character.isLetter(input.charAt(0)))
                System.out.println(failMessage);
            else
                isSingleLetter = true;
        }
        return Character.toUpperCase(input.charAt(0));
    }

    private static void printGuesses(String allGuesses, String word) {
        char letterOnScreen = '_';
        //boolean correctGuesses = false;
        //Prints word with guesses shown
        System.out.println();//spacing
        for (int i = 0; i < word.length(); i++) {
            for (int g = 0; g < allGuesses.length(); g++) {
                if (allGuesses.charAt(g) == word.charAt(i)) {//makes sure to print duplicate letters
                    letterOnScreen = Character.toUpperCase(word.charAt(i));
                }
            }
            System.out.print(letterOnScreen + " ");
            letterOnScreen = '_';
        }
        System.out.println();//spacing
        //Prints all guesses so far
        //to not print duplicate guesses I could make another string,
        //and only append the letter if it did not exist previously
        for (int g = 0; g < allGuesses.length(); g++) {
            System.out.print(allGuesses.charAt(g) + " ");
        }
        System.out.println();//spacing
    }
    public static boolean checkWord(String allGuesses, String wordToCheck) {
        boolean isWordSoFar = true;
        int counter = 0;
        while(isWordSoFar && counter < wordToCheck.length()) {
            if (!(allGuesses.indexOf(wordToCheck.charAt(counter)) > -1)) {
                //false if allGuesses does not have all letters that wordToCheck contains
                isWordSoFar = false;
            }
            counter++;
        }
        return isWordSoFar;
    }
    //returns true if won, false if lost
    public static boolean hangMan(String wordToGuess) {
        //THE GAME
        //init vars
        wordToGuess = wordToGuess.toUpperCase();
        String[] manInCurrentGame = new String[fullMan.length];
        int lives = manInCurrentGame.length;
        char currentGuess;
        String allGuesses = "";//all guesses get added onto here
        boolean isFinishedWord = false;
        //get guesses until you win or die... very high stakes
        ConsoleGraphics.clearScreen();
        while (lives > 0 && !isFinishedWord) {
            printCurrentMan(lives); //print ASCII art based off # of lives left
            printGuesses(allGuesses, wordToGuess); //print guesses below art
            isFinishedWord = checkWord(allGuesses, wordToGuess);
            if(!isFinishedWord) {
                currentGuess = getGuess();
                if (!hasLetter(wordToGuess, currentGuess))
                    lives--;
                allGuesses += currentGuess;
            }
            ConsoleGraphics.clearScreen();
        }
        String endScreen = "The word was " + wordToGuess +"\n";
        if(isFinishedWord)
            endScreen += "✔️You won!✔️";
        else
            endScreen += "\uD83D\uDC80Sorry, try again next time\uD83D\uDC80";
        System.out.print(endScreen);
        return isFinishedWord;
    }
}
