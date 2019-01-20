import java.util.ArrayList;
import java.util.HashMap;

public class Decision {
    String prompt; //"Do you go to the gym or stay in lib?"
    String choice;
    HashMap<String, Integer[]> options; //value: scores on status
    HashMap<String, String> textAfterChoice;


    public Decision(String prompt, HashMap<String, Integer[]> options, HashMap<String, String> textAfterChoice) {
        this.prompt = prompt;
        this.options = options;;
        this.textAfterChoice = textAfterChoice;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public HashMap<String, Integer[]> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Integer[]> options) {
        this.options = options;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public HashMap<String, String> getTextAfterChoice() {
        return textAfterChoice;
    }

    public void setTextAfterChoice(HashMap<String, String> textAfterChoice) {
        this.textAfterChoice = textAfterChoice;
    }


    public void updateStatus(User user) {
        Integer[] changes = options.get(choice);
        int[] status = user.getStatus();
        int numStatus = user.getSTATUS_NUM();
        for (int i = 0; i < numStatus; i++) {
            status[i] = changes[i];
        }

    }



}
