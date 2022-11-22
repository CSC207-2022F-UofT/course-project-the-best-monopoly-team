package TreeHandlers;

import Entities.*;

import java.util.ArrayList;


/**
 * This tree handler handles the input during main game phase of the program.
 */
public class MainTreeHandler extends TreeHandler {

    //mainStates[0]: variable reserved for confirmation node
    //mainStates[1]: variable reserved for roll
    int[] mainStates = new int[2];
    String answer;
    GameLogicTree confirmationReturn;
    String diceRoll;
    public MainTreeHandler(){
    }

    /**
     * Constructor for the class when states are already known.
     * @param states
     */
    public MainTreeHandler(int[] states){
        mainStates = states;
    }

    /**
     * This method handles the input of the user in the main game part of the program.
     * <p>
     *
     * @param input the translated input of the user from the input interface
     */
    public State handleInput(int input){
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(currentTree.getName());

        switch (currentTree.getName()){
            case "Trade":
                currentState.setBackEnable(true);

                //provide a list of all possible players considering the current player is not an option
                ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
                playerCopy.remove(currentPlayer);
                for(int i = 0; i < playerCopy.size(); i++){
                    currentState.addOptions(playerCopy.get(i).getName());
                }
                break;
            case "PickPlayer":
                currentState.setBackEnable(true);
                //adds the chosen player index in selected options (who the current player wants to trade with)

                /*
                We asked them for an input in reference to a list with their player removed,
                thus we have to add 1 to their input in certain cases
                 */
                if (input >= getCurrentPlayerIndex()) {
                    input += 1;
                }
                //getting the player the user wants to trade with
                selectedOptions.put(currentTree.getName(), input);

                //provide item options from the inventory of the selected player
                Player selectedPlayer = board.getPlayers().get(input);
                ArrayList<Property> playerProperties = selectedPlayer.getProperties();
                if (playerProperties.isEmpty() || currentPlayer.getProperties().isEmpty()){
                    gameLogicInteractor.transverseCurrentTree(1);
                    currentState = handleInput(0);
                    break;
                }
                //using "i" starting from 0 to number of properties the player has - 1
                for (int i = 0; i < playerProperties.size(); i++){
                    currentState.addOptions(playerProperties.get(i).getName());
                }
                break;
            case "NothingToTrade":
            case "AlreadyRolled":
                currentState.addOptions("Ok");
                break;
            case "PickItemOp":
                currentState.setBackEnable(true);
                //the input corresponds to the index of the targeted opponent's property inventory
                selectedOptions.put(currentTree.getName(), input);

                //provide item options from the current player's inventory
                ArrayList<Property> currentPlayerInventory = currentPlayer.getProperties();
                //using "i" starting from 0 to number of properties the player has - 1
                for(int i = 0; i < currentPlayer.getProperties().size(); i++){
                    currentState.addOptions(currentPlayerInventory.get(i).getName());
                }
                break;
            case "PickItemSelf":
                currentState.setBackEnable(true);
                currentState.addOptions("Yes");
                currentState.addOptions("No");

                //the input corresponds to the index of the current player's targeted property;
                selectedOptions.put(currentTree.getName(), input);

                break;
            case "SendTrade":
                if (input == 0) {
                    Player tradingOpponent = board.getPlayers().get(selectedOptions.get("PickPlayer"));
                    currentState.setTradingOpponent(tradingOpponent);
                    currentState.setPlayer(currentPlayer);
                    currentState.setCurrentPlayerProperty(currentPlayer.getProperties().get(selectedOptions.get("PickItemSelf")));
                    currentState.setTradingPlayerProperty(tradingOpponent.getProperties().get(selectedOptions.get("PickItemOp")));
                    returnTree = currentTree;
                    returnPlayerIndex = getCurrentPlayerIndex();
                    //returnPlayerAddress will hold the original player index in this.board.getPlayers()
                    currentPlayer = tradingOpponent;
                    gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[1]);
                    addSwitchOptions(currentState);
                }
                else{
                    currentState = afterBottomNode();
                }
                break;
            case "ManageProperty":
                currentState.setBackEnable(true);
                ArrayList<Property> currentPlayerProperties = currentPlayer.getProperties();

                //if player has no properties, go to another node.
                if (currentPlayerProperties.isEmpty()){
                    gameLogicInteractor.transverseCurrentTree(1);
                    currentState = handleInput(0);
                }

                //provide options on the properties available
                for(int i = 0; i < currentPlayerProperties.size(); i++){
                    currentState.addOptions(currentPlayerProperties.get(i).getName());
                }
                break;
            case "NoProperties":
                currentState.setDescription("You have no properties :(");
                currentState.addOptions("Ok");
                break;

            case "SelectProperty":
                currentState.setBackEnable(true);

                //Case property selected (adds the property index)
                selectedOptions.put(currentTree.getName(), input);

                //the player chooses what to do to the property
                currentState.addOptions("Mortgage");
                currentState.addOptions("Unmortgage");
                currentState.addOptions("Build a house");
                break;
            case "Mortgage":
                if (mainStates[0] == 1) {
                    //the player chooses to mortgage the property
                    Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("SelectProperty"));
                    currentPlayer.mortgage(targetProperty);
                    mainStates[0] = 0;
                    currentState = afterBottomNode();
                }
                else{
                    //setup for confirmation node
                    currentState.addOptions("yes");
                    currentState.addOptions("no");
                    confirmationReturn = currentTree;
                }
                break;
            case "UnMortgage":
                //TODO: ADD FUNCTIONALITY
                break;
            case "BuildProperty":
                Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("SelectProperty"));

                //builds a house on the chosen property
                currentPlayer.buildHouse(targetProperty,1);
                currentState.setCurrentPlayerProperty(targetProperty);
                currentState.addOptions("Ok");
                break;
            case "Roll":
                if (mainStates[1] == 0) {
                    //roll the dice and update the position
                    diceRoll = currentPlayer.rollDice(0);
                    board.updatePlayerPosition(currentPlayer);

                    //get the space landed on
                    Cell landedOnCell = board.getCell(currentPlayer.getPosition());

                    /*
                    if the space is a property and has no owner, transverse to a branch, otherwise,
                    transverse to another
                     */
                    if (landedOnCell instanceof Property &&
                            ((Property) landedOnCell).getOwner() == null) {
                        gameLogicInteractor.transverseCurrentTree(0);
                    } else {
                        //perform the action on the space as well
                        answer = landedOnCell.performAction(currentPlayer,board);
                        gameLogicInteractor.transverseCurrentTree(1);
                    }
                    //perform the logic in the new node.
                    currentState = handleInput(0);
                    //player can no longer roll
                    mainStates[1] = 1;
                }
                else{
                    //go to a node where it tells the user that they cannot roll
                    gameLogicInteractor.transverseCurrentTree(2);
                    currentState = handleInput(0);
                }
                break;
            case "CallAction":
                //gets the response from rolling on a space
                currentState.setRoll(diceRoll);
                currentState.setDescription(answer);
                currentState.addOptions("Ok");
                break;
            case "EmptyPropertySpace":
                //gets the response and options from rolling on an empty property
                currentState.setRoll(diceRoll);
                targetProperty = (Property) board.getCell(currentPlayer.getPosition());
                currentState.setCurrentPlayerProperty(targetProperty);
                addSwitchOptions(currentState);
                break;
            case "Buy":
                //player buys the property that the player lands on
                targetProperty = (Property) board.getCell(currentPlayer.getPosition());

                //indicates that the player can afford it and sets the property owner as the current player and
                //deducts the player's money.
                currentPlayer.pay(targetProperty.getPrice());
                currentPlayer.getProperties().add(targetProperty);
                targetProperty.setOwner(currentPlayer);
                currentState = afterBottomNode();

                break;
            case "Auction":
                //returnPlayerAddress will hold the original player index in this.board.getPlayers()
                returnPlayerIndex = getCurrentPlayerIndex();

                //change trees and start the auction
                returnTree = currentTree;
                gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[2]);
                gameLogicInteractor.setupAuction();
                currentState = getCurrentState();
                break;
            case "Steal":
                currentState.setBackEnable(true);

                //provide options of which players we can steal from
                playerCopy = new ArrayList<Player>(board.getPlayers());
                playerCopy.remove(currentPlayer);
                for(int i = 0; i < playerCopy.size(); i++){
                    currentState.addOptions(playerCopy.get(i).getName());
                }
                break;
            case "ChoosePlayer":
                //Steal from the target player
                playerCopy = new ArrayList<Player>(board.getPlayers());
                playerCopy.remove(currentPlayer);
                String stealStatus = currentPlayer.steal(playerCopy.get(input));

                currentState.setDescription(stealStatus);
                currentState.addOptions("Ok");
                break;
            case "EndTurn":
                //end the turn if the person is not in debt
                if (currentPlayer.getMoney()  >= 0){
                    //changing the player and turning the state back to normal
                    changePlayers();
                    mainStates = new int[2];
                    currentState = afterBottomNode();
                }
                else{
                    //option when the user cannot end their turn
                    currentState.addOptions("Ok");
                }
                break;
            case "SettingsMenu":
                currentState.setBackEnable(true);

                //options for the settings menu
                addSwitchOptions(currentState);
                break;
            case "ExitGame":
                if (mainStates[0] == 1) {
                    mainStates[0]= 0;
                    currentState.setExitToMenu(true);
                }
                else{
                    //confirmation node setup
                    currentState.addOptions("Yes");
                    currentState.addOptions("No");
                    confirmationReturn = currentTree;
                }
                break;
            case "SaveGame":
                //options for saving the game
                currentState.addOptions("Ok");
                //TODO: make this option save the game in use case interactor
                currentState.setSaveGame(true);
                break;
            case "Bankruptcy":
                if (mainStates[0] == 1) {
                    mainStates[0] = 0;

                    //removing all player connection with the board
                    currentPlayerProperties = currentPlayer.getProperties();
                    for (Property targetedProperty : currentPlayerProperties) {
                        targetedProperty.setOwner(null);
                        targetedProperty.setHouses(0);
                        targetedProperty.setMortgageStatus(false);
                    }
                    Player tempPlayer = currentPlayer;
                    //switching the player before removing the original player
                    changePlayers();
                    board.removePlayer(tempPlayer);

                    //changing the player and turning the state back to normal
                    mainStates = new int[2];
                    currentState = afterBottomNode();
                }
                else {
                    //confirmation node setup
                    confirmationReturn = currentTree;
                    currentState.addOptions("Yes");
                    currentState.addOptions("No");
                }
                break;
            case "Confirmation":
                //Gives the user another chance to reconsider their actions
                if (input == 0) {
                    mainStates[0] = 1;
                    gameLogicInteractor.setCurrentTree(confirmationReturn);
                    currentState = handleInput(input);
                }
                else{
                    currentState = afterBottomNode();
                }
                break;
            case "Information":
                //allows for information to be shown to the user
                currentState = afterBottomNode();
                break;
        }
        //mutating the state to have memory of its state, useful for backwards transversal
        currentTree.setPreviousState(currentState);
        return currentState;
    }

    /**
     * Sets the tree back to its top position and returns the current state of the tree
     * @return state object
     */
    public State afterBottomNode(){
        gameLogicInteractor.setCurrentTreeToMaxParent();
        return getCurrentState();
    }

}
