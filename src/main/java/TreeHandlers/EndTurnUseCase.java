package TreeHandlers;

import Entities.State;

public class EndTurnUseCase extends TreeHandler implements NodeLogic{

    public State create_state(int input){
        State currentState = new State();
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
        return currentState;
    }

}
