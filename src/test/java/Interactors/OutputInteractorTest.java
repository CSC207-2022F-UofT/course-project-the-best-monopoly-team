//package Interactors;
//
//import Persistence.LoadAccess;
//import Persistence.SaveAccess;
//import Persistence.LoadFile;
//import Persistence.SaveFile;
//import UseCases.UseCaseInteractor;
//
//import Entities.State;
//
//import org.junit.Test;
//
//import java.io.File;
//import java.util.ArrayList;
//
//public class OutputInteractorTest {
//
//    @Test
//    public void testUpdateMainTree() {
//        LoadAccess game = new LoadFile(new File("src/gameData/input_test.txt"));
//        SaveAccess save = new SaveFile(new File("src/gameData/input_test.txt"));
//        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
//        OutputInteractor outputTest = new OutputInteractor(useCaseTest);
//        InputInteractor inputTest = new InputInteractor(useCaseTest);
//
//        inputTest.getChoice(0);
//        inputTest.sendInput();
//
//        inputTest.getChoice(0);
//        inputTest.sendInput();
//
//        inputTest.getChoice(4);
//        inputTest.sendInput();
//
//        inputTest.getChoice(3);
//        inputTest.sendInput();
//
//        inputTest.getChoice(0);
//        inputTest.sendInput();
//
//        String testString = "Player 0 It's your turn! What do you want to do? You currently have 1500 dollars";
//        assert testString.equals(useCaseTest.getCurrentState().getPlayer().getName() +
//                " It's your turn! What do you want to do? You currently have " +
//                useCaseTest.getCurrentState().getPlayer().getMoney() + " dollars");
//    }
//
//    @Test
//    public void testAddOptionStrings() {
//        LoadAccess game = new LoadFile(new File("src/gameData/test.txt"));
//        SaveAccess save = new SaveFile(new File("src/gameData/test.txt"));
//        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
//        InputInteractor inputInteractor = new InputInteractor(useCaseTest);
//        OutputInteractor outputInteractor = new OutputInteractor(useCaseTest);
//
//        inputInteractor.getChoice(1);
//        inputInteractor.sendInput();
//
//        inputInteractor.getChoice(1);
//        inputInteractor.sendInput();
//
//        inputInteractor.getChoice(0);
//        inputInteractor.sendInput();
//
//        ArrayList<String> test = outputInteractor.getCurrentState().getOptions();
//
//    }
//
//    @Test
//    public void testGetOutput() {
//        LoadAccess game = new LoadFile(new File("src/gameData/input_test.txt"));
//        SaveAccess save = new SaveFile(new File("src/gameData/input_test.txt"));
//        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
//        InputInteractor inputTest = new InputInteractor(useCaseTest);
//        OutputInteractor outputTest= new OutputInteractor(useCaseTest);
//
//        inputTest.getChoice(0);
//        inputTest.sendInput();
//
//        inputTest.getChoice(0);
//        inputTest.sendInput();
//
//        inputTest.getChoice(4);
//        inputTest.sendInput();
//
//        inputTest.getChoice(3);
//        inputTest.sendInput();
//
//        inputTest.getChoice(0);
//        inputTest.sendInput();
//
//        String testString = "Player 0 It's your turn! What do you want to do? You currently have 1500 dollars";
//        outputTest.setFinalOutput();
//        assert testString.equals(outputTest.getOutput());
//    }
//
//    @Test
//    public void testUpdateState() {
//        LoadAccess game = new LoadFile(new File("src/gameData/input_test.txt"));
//        SaveAccess save = new SaveFile(new File("src/gameData/input_test.txt"));
//        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
//        InputInteractor inputTest = new InputInteractor(useCaseTest);
//        OutputInteractor outputTest= new OutputInteractor(useCaseTest);
//
//        inputTest.getChoice(0);
//        inputTest.sendInput();
//
//        inputTest.getChoice(0);
//        inputTest.sendInput();
//
//        inputTest.getChoice(2);
//        inputTest.sendInput();
//
//        State newState = inputTest.getCurrentState();
//        outputTest.updateState(newState);
//        ArrayList<String> newStateOptions = outputTest.getCurrentState().getOptions();
//
//        ArrayList<String> options = new ArrayList<>();
//        options.add("30 rounds");
//        options.add("60 rounds");
//        options.add("90 rounds");
//        options.add("no limit");
//
//        assert options.equals(newStateOptions);
//    }
//}
