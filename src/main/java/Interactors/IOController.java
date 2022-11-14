package Interactors;

public class IOController {
    private String input;
    private String[] output;

    public IOController(){
        this.input = "";
        this.output = new String[]{};
    }
    public String[] packageData(String data){
        String[] temp = new String[this.output.length + 1];
        temp[this.output.length] = data;
        this.output = temp;
        return this.output;
    }
    public void setInput(String input){
        this.input = input;
    }
    public String getInput(){
        return this.input;
    }
}
