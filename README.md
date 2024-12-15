# Mastermind Game

## Description

The Mastermind Game is a classic code-breaking game where the player attempts to guess a sequence of randomly selected numbers (0-7). The game provides feedback after each guess to help the player deduce the correct sequence.

## Features

- User can pick difficulty levels from 4-digit to 8-digit numbers.
- Randomly generated sequence of numbers from 0 to 7
- Multiplayer mode available
- Able to edit player name
- Each user will have 10 attempts to solve the game
- Number of attempts remaining shown before each attempt
- Feedback provided after each guess
- User has the ability to view the history of guesses and their feedback
- User has the ability to view the hint for each-digit once
- User can view the current user's playing time
- Score is updated once a user solves
- Shows total game play time
- Lists only the players that solved the code and their score, shows the player with the highest score
- Read and Writes a file which keeps top 5 scores of all time

## In-Game Useful Commands

While guessing for the randomly generated number, player can also take advantage of below commands:

- `history` - view the history of guesses and their feedback
- `hint` - view the hint for each-digit once, and player's available hints
- `time` - view the current player's playing time
- `getCode` - case-sensitive. View the code to solve

## Making the Application

Completing a Web Dev Bootcamp, I have only used JavaScript using VS code, 
but to challenge myself, I've decided to code in Java, using IntelliJ, 
where I only have little experience with. Also, because I believe it 
aligns more as a back-end programming language. I first approached my  
project using procedural programming design. Java uses heavily relies 
on object-oriented programming principles and features. I've tried my 
best to implement OOP designs in the project.

> I wanted to first separate few sections into different sub-packages 
> for better organization.
> 1. Api - for getting the random generated code
> 2. Game - to have any logic related to game and during the game play
> 3. UI - the user interface (front-end, any System.out.print(s)) to 
handle game messages, different responses to the user's inputs, time 
play, etc.

I wanted to keep the `Main.java` file short and concise for readability 
and to separate the codes that will run game logic and functionalities. 
I've used the instance of `Game` and `Scanner` as a static field for 
global access. After running the program and a welcoming message, 
program will ask for an inputs for game level. Which will check if what 
the user entered is a number and within the range of 4 to 8. User's 
input for difficulty level will dynamically set the URL API 
endpoint. Based on the level input, make HTTP request for a `level`-digit 
code. Then asks for the number of players. The information is then 
saved in the fields of `Game` instance.
Once all inputs are validated, it will create an instance of `Game` and
the game will start which will also trigger the timer for game,
utilized built-in `System.currentTimeMillis()` method. Game will ask 
for input for player names. Names are used to create a `Player` 
instance and used as one of the fields. Players are also added to a 
field in `game`'s field as ArrayList. Once all the player names are 
confirmed, `playGame` method is called.

> **User Input:** Whenever a user input is required, `UserInput` class, 
> the input is validated with the `Validator` class. If a user doesn't 
> provide a valid input, program will repeat the question.

> **Adding Players:** I've used while loop for getting the player's 
> information to implement multi-player feature. The program will keep 
> asking for name based on the player number input. Once all names are 
> finished, the program asks if user want to edit a player's name and user 
> can do so by providing the player number. The player number and name 
> will be displayed. 

`playGame` has 4 fields:
1. `getTurn` - acts as a way to track the player's turn
2. `userGuess` - to store the user input
3. `numOfFinishedPlayers` - to keep how many players are finished game
4. `solvedPlayers` - to keep a list of players that solved and their score

The `playGame` method has a while loop that will continuously run as long as:

1. The boolean value of `gameOver` in `game` instance is false.
2. If the `numOfFinishedPlayers` is not equal to the number of players
   (If they are equal it will set the value of `gameOver` to true).

Inside this while loop, I used the `getTurn` to toggle between players. 
Game will increment at end of each turn and by using modulus with 
number of players, I will get the index number of player to use to get 
from the ArrayList. Also, in each turn, in the beginning it will start 
time and at the end it will subtract current millisecond with start 
time and the result of it will be accumulated in the current player's 
field to keep track of each player's play time. In each turn, game will 
ask for the user guess for the code to solve and run the `validateCode` 
of the `Validator` class. Each player instance has a field to track the 
number of attempts left, the field decreases at the end of each 
player's turn and when the attempt runs out, another field in the 
instance `isPlaying` changes its value to false. This allows the 
program to skip the player that is not playing.

> **Playing the Game:** Once all the player(s) are added, playGame 
> method is called. It will show the in-game commands: command to show 
> the history, time, hint, and retrieve the answer. Also, will show the 
> player name, how many hints the player has left (inside the in-game 
> command), and how many attempts a player has left. Main functionality 
> is inside the `PlayGame` class.

> **Validating Code:** I needed a way to keep track of: correct 
> locations, correct numbers, and account for the duplicated numbers in 
> the code. I've used 2 variable to keep track of correct numbers and 
> locations. Used HashMap to keep track the answer, each number as a key 
> and increment the value if the HashMap contains the key. Once the 
> HashMap is assigned, the user's guess will iterate each of the user's 
> number and check if it contains the number in the HashMap, if it does,
> increment correct number and decrement the value.

Once the game is over by either out of attempts for all players and/or 
players solved the code, it will return the `ArrayList` of the solved 
players in a `String.` Then it will display all the players with their 
scores and shows the highest score for the game play.
Lastly, `readWriteTopPlayers` method of `TopPlayersHandler` class is 
called to add each of the current player's name and score into an 
instance of `PlayerScore.` These instances then will be added to a 
`List.` Furthermore, the text file `top5.txt` is read to get the all 
the previous top 5 scored players, create instance for each, then added 
to the `List.` Once all the players are added, the list will sort in 
descending order (to list from the highest score to least), and write 
to the `top5.txt` file but only will write 5 players only. Will also 
output the top 5 players and the game ending message.

- Programming Language: **Java**

## Try it yourself
I've used the IntelliJ IDE, if you don't have it, please [download]
(https://www.jetbrains.com/idea/download) it.

Go to this [link](https://github.com/YoonHCho/mastermind_Java) and clone the repo. Please refer to this [link](https://www.jetbrains.com/help/idea/set-up-a-git-repository.html) if you're not familiar with cloning a repo.

When the project opens, you should be automatically in the project 
directory. Find the `Main.java` inside the `src` directory of the 
project and `Run` the file to run the application.