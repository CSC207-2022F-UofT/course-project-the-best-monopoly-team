# MONOPOLY PLUS

To run the game, run the main method found in the main folder. Then proceed to run the Main.main() function within the file.
Sidenote: We are using Java 11 corretto as our JDK.

## Playing the game
This is currently a text based version of monopoly. So then the I/O that we are using is the command line. 
When prompted for an action, we will have a corresponding number for the input (0,1,2,3). Type in the desired action number then press enter.

# Navigating the project files in the repository
This section outlines the brief overview of the structure of the project. The structure that we followed within this project was mainly packaging by feature. 
After clicking into source, we have 3 directories, main, save and test.

## Main
This directory contains most of the source code pertaining to entities, use cases, interactors, tree handler and more.

### Entities
This directory contains all the core business rules and is the lowest level. The list below contains all the entities and a brief description of what they do.
- ActionSpace
    - Action spaces are spaces where the player has to draw a card from community chest, chance or jail. This class manages the cards that the players are going to draw and their related actions.
- Board
    - The board class keeps track of players and which position they are at, the different cells. These cells can either be of class property or action space
- Cell
    - Just an abstract class that both property and action space needs to inherit from
- CornerTiles
    - This governs the 4 corners of the board, jail, free parking, goToJail, and Go
- GameLogicTree
    - This governs the tree that builds the game logic
- MenuTree
    - This governs the tree for the different menu actions
- Player
    - This class keeps track of everything pertaining to the player, such as money, the properties they have and more.
- Property
    - This class keep track of the different properties, their name, color, cost, rent pricing, the player that is owned by and more.
- State
    - This class keeps track of the state of the game and the options pertaining to saving the game and player information

### Interactors
- DataAccess
    - loadGame method and saveGame method
- GameCreation
    - Setting the board up if they were creating the game or loading in a previous saved game
- GameLogic
    - Sets up the tree entities and how they interact
- IOController
    - Controls the text based output and input of the program
- TextFileTranslator
    - loading the game in from the text files and also saving the game to text files.

### Main
- Main
    - Where the game is ran

### TreeHandlers
Some aspects of the game also needs trees to decide and keep track of the options.
- AuctionTreeHandler
- MainTreeHandler
- TradingTreeHandler
- TreeHandler

### UseCases
When the user interacts with a part of the game that is are use cases, the logic begin each use cases should be here.

## Save
This directory contains the save data and the game data that needs to be loaded in when the game is first played or when a previous game needs to be loaded in.
- chestcomjail.txt
    - Cards for community chest, chance and jail.
- properties.txt
    - The list of properties and their relevant characteristics.

## Test
This directory includes all the test files for each entity and their use case.
- Entities
- Interactors
- UseCases
