# Game-of-Set
__________________________________

Set is a pattern-recognition card game where players identify groups of three cards that form a valid "set." Each card has four attributes, and for each attribute, the values must be either all the same or all different.


Main Screen:

<img width="748" height="579" alt="Screenshot 2026-01-06 at 10 26 22 PM" src="https://github.com/user-attachments/assets/30ad99df-cf9d-4dcf-ac8c-65baa4e0a02b" />



How it's made:
____________________________________

Tech used: Java, Java Swing

I started this project by creating the Card class, which defnes the four properties of a card (color, shape, fill, and number) and the method for determining a set of cards. Then I created the deck, board, boardsquare, and game class. The deck class assigns twelve cards to a deck, and creates the methods to shuffle the deck, pull the top card, check the number of cards left, and check if the deck is empty. The board class creates an array list of array lists for the board, and defines the methods used (replace card, add 3, get card, total cards, get board square, etc.). The board square class creates each board square and implements getters and setters for each variable (row, column, card, etc). Lastly, the game class creates the game function, and defines the methods of adding cards to the selected array, getting the number of selected cards, testing the selected cards to see if there is a set of three, removing cards from selected, and adding 3 cards to the board. The next step was to create the GUI, main panel, and card panel programs. The card panel file designs the panel for each card that is placed on the board, and has methods to select/deselect cards, and a mouse listener event so when each card is selected the program will check if there are 3 cards and if so test them for a set and repaint the board. The main panel program creates the main panel for the game, adds the buttons and words on the screen, and selects which cards go on the board at random. The GameGUI program has the necessary components to run the game and have the previous methods work together. 
