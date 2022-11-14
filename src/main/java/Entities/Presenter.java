package Entities;

public class Presenter {
    private String[] currentOutput;

    public Presenter(){
        this.currentOutput = new String[]{};
    }
    public void presentData(){
        for (String str: this.currentOutput){
            System.out.println(str);
        }
    }
    public void updateOutput(String[] output){
        this.currentOutput = output;
    }
}
