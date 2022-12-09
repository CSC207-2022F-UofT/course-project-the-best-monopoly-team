# MONOPOLY PLUS

## How to play the game
In the ```src/main/java/Main``` click the play button or run the file and a popup window 
with a monopoly board with ```New Game``` and ```Load Game``` buttons showing up. Note: If
your compile is not working, try using corretto 11.
## Playing the game
Playing the Game is straight forward, there is a context of text explaning what is happening
on the current state of the game and option buttons presenting choices of what the player can do.
* IMPORTANT NOTE: Load/Save is only functional if you have a ```src/gameSaves``` folder in your directory, otherwise the game will break
* IMPORTANT NOTE: This is still an initial creation of a monopoly game, there are likely bugs that need to be fixed/improvments that can be made

# Navigating the project files in the repository
This section outlines the brief overview of the structure of the project. The structure that we followed within this project was mainly packaging by feature. 
After clicking into source, we have 3 directories, main, save and test.

### Entities
This directory contains all the core business rules and is the lowest level
 
### Logic
The Backbone of this game; everything in this file controls the states as the game progresses
and different outcomes depending on various player inputs

### UseCases
The Logic pertaining to a user interaction with the screen as well different spaces
on the board

## Persistence
The Logic pertaining to storing game data in and getting game data from a file

## Test
This directory includes all the test files for each entity and their use case.
The coverage directory includes the generated coverage report from IntelliJ. 
Open the index.html file in a browser window to see the report.