# Hang Man

Java command-line game that will play hangman with you until you win or the hang-man DIES (very high stakes)

Reads a text file seperated by line. All words are case sensitive. Feel free to add or change the words you're guessing! The program will adjust based on how many words are in the file.

StartGame.java contains the main() method which starts the game and reads off "words.txt" as mentioned above.

## Interesting problems within the program

The game needed to have logic for not losing lives if the user had guessed the same letter twice.
I concatenated the currentGuess character to a string called allGuesses and to check if the currentGuess character already exists in the allGuesses string.

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

The trickiest part when designing the behavior for checking guessed words was when there was more than 1 letter in the same word.[^1]
[^1]: greedy, hello, balloon are examples of words with shared letters

I solved this issue by having a method that iterated through each character of the word to guess and to print a "blank" if the letter did not exist in the allGuesses string, and to print the letter if there was a match.

```
for (int i = 0; i < word.length(); i++) {
    for (int g = 0; g < allGuesses.length(); g++) {
	if (allGuesses.charAt(g) == word.charAt(i)) //makes sure to print duplicate letters
		letterOnScreen = Character.toUpperCase(word.charAt(i));
    }
    System.out.print(letterOnScreen + " ");
    letterOnScreen = '_';
}
```