# Marble-Solitaire

This command-line program creates the classic game, Peg Solitaire, that was first played in the 1600s. In this project, there are two additional types to the board besides the traditional English board type, including European board and Triangular board.

## How to play the game

This game is very easy to play. The objective of the game is to remove as many pegs/marbles from the board as possible through valid moves. Hopefully you are able to empty the whole board! A valid move is to jump a peg orthogonally over an adjacent peg into a empty hole/position two positions away and then to remove the jumped peg. You can only jump over one adjacent peg.

In this program, the user to type in the "from" position of a marble and the "to" position of a marble to play the game. For example, typing 4 2 4 4 for the following

    0 0 0   
    0 0 0
0 0 0 0 0 0 0 

will result in...

    O O O
    O O O
O O O O O O O
O _ _ O O O O
O O O O O O O
    O O O
    O O O
Score: 31

The top row is 1 and the left most column is also 1. So 4 2 4 4, moved the piece from position 4(row), 2(column) to position 4(row), 4(column) which was the empty hole. Thus the peg from 4,2 is now at 4,4 and the position the peg left from as well as the peg it jumped over are empty. After each valid move, an updated board and score will be chose in the terminal.

The game will keep reading the user's input until 4 natural numbers, separated by spaces, are acquired. If there is an invalid input, the program will warn the user and ask for another set of numbers. Also, the program will let the user know if there are no more possible moves on the board. Hit "q" or "Q" to quit the game at any time.

## Run configuration 

To run the program, program needs to take some intital inputs as command-line arguments. First, user must pass in one of the following: english, european, or triangular. This argument will decide which board shape (and hence which model) you should use. Then the user may optionally pass the two arguments -size N, where N is a number, to specify the size of the board. If unspecified, the default size for the chosen board shape will be used. Lastly, the user may optionally pass the three arguments -hole R C, where R and C are numbers, to specify the row and column of the initial empty hole. If unspecified, the default hole position for the chosen board shape will be used. 

Examples:
"english -size 6" produces a plus-shaped english board with arm-width of 6, and initial hole in the center
"triangular" produces a triangle-shaped board with side-length 5, and initial hole at the top
"european -hole 1 4" produces an octagon-shaped board with side-length 3, and the initial hole in the middle of the top edge.

Run the command line arguments with the MarbleSolitaire.java file to play the game. 
