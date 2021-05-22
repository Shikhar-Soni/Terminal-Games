The following is a Hangman game written in perl.

To run the program -
1. Open CMD and type 'perl Hangman.pl'

How to play the game -
1. Enter a single letter at each step to guess the letters in the word.
2. Incase a wrong guess is made, the hangman stick figure emerges, each part at a time.
3. You have a total of 6 wrong guesses available.
4. You will continue to make guesses until the word is guessed or you have made 6 incorrect guesses.
5. You can play again by entering 'Y' or exit the program by pressing any other letter.

Assumptions made:
1. A player can only enter one letter at a time.
2. If a letter that's correct and has already been guessed is entered again then it's not counted
as a wrong guess.
3. If a player repeatedly enters a wrong letter again and again then only it's first occurence is
counted as incorrect.
4. Input is not case sensetive, so 'A' and 'a' are both equivalent to 'a'.
5. The exit input is case sensitive and one has to input 'Y' in upper case to play again but can 
exit the program by entering any character.
6. All characters other than those present in the word are regarded as incorrect choices.
7. The hangman stick figure is printed only when a wrong choice is made.
8. An empty space or multiple character is considered as incorrect input and not treated as a wrong 
attempt.