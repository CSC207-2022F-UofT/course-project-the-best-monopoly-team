package Interactors;

public class IOController {
    private String output;
    private int input;

    public IOController(){
        this.output = "Welcome to Monopoly";
        this.input = 0;
    }
    public String presentOutput(){
        return this.output;
    }
    public void setInput(int input){
        this.input = input;
    }
    public int getInput(){
        return this.input;
    }
    public void setOutput(String[] output){
        this.output = convertOutput(output);
    }
    public String convertOutput(String[] output){
        //Function needs to be implemented when connected to front end
        //In essence, take the resulting output from the UseCaseInteractor/Other Classes, it's not decided yet
        //but then concatenate that into a string that the user can understand and use to derive the input
        //for the next turns, so the input will be taken in Strings, take that Array of Strings and format

        //For a sample (very likely to change in the future)
        StringBuilder finalString = new StringBuilder("Player x, would you like to: ");
        int i = 0;
        for (String option : output){
            finalString.append(option).append("(").append(i).append("), ");
            i++;
        }
        String inString = finalString.toString();
        return inString.substring(inString.length() - 2);
    }
}
