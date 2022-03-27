import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartGame {
    private static List<String> loadWords(File pathToLoad) {
        List<String> wordList = new ArrayList<>();

        try(Scanner wordScan = new Scanner(pathToLoad)) {
            while(wordScan.hasNextLine()) {
                String curLine = wordScan.nextLine();
                wordList.add(curLine);
            }
            if(wordList.size() == 0)
                throw new ArrayIndexOutOfBoundsException("Array is empty.");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading word file.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No words found in " + pathToLoad);
        }
        return wordList;
    }

    public static void main(String[] args) {
        List<String> wordList = loadWords(new File("words.txt"));
        int score = 0;
        for(String word : wordList) {
            System.out.println("Your current score is " + score + "/" + wordList.size());
            if(HangMan.hangMan(word))
                score++;
            ConsoleGraphics.clearScreen();
            System.out.println("The word was " + word.toUpperCase());
        }
    }
}
