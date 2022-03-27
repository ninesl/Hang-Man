# Hang Man

Java command-line game that will play hangman with you until you win or the hang-man DIES (very high stakes)

Reads a text file seperated by line. All words are case sensitive. Feel free to add or change the words you're guessing! The program will adjust based on how many words are in the file.

StartGame.java contains the main() method which starts the game and reads off "words.txt" as mentioned above.

## Interesting problems within the program

The trickiest part when designing the behavior for checking guessed words was when there was more than 1 letter in the same word.[^1]
[^1]: greedy, hello, balloon are examples of words with shared letters

A good solution for this was to concatenate a string of all guessed letters so far, and to check if the user had guessed that character or not already.

Code that is run while user has enough lives left
```
if(!isFinishedWord) {
    currentGuess = getGuess();
    if(!allGuesses.contains(String.valueOf(currentGuess))) { //So you can't double guess a letter
        allGuesses += currentGuess;
        if (!hasLetter(wordToGuess, currentGuess))
            lives--;
    }
}
```