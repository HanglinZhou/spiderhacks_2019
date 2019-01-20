import java.util.*;
import java.io.*;


public class Executer {
    int time;
    Storyline storyline;
    String file;
    int[] currProcessIndex; //store the indices to fixed, daily, randomtriggered


    public Executer(int time, String file) {
        this.time = time;
        this.file = file;
        try {
            Storyline storyline = new Storyline(file, time);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        currProcessIndex = new int[]{0,0,0};

    }

    public Story pickNextStory(int curTime){
        int allRandomEvent = storyline.getRandom().size();
        Random r = new Random();
        double probability = r.nextDouble();
        int randomIndex = r.nextInt(allRandomEvent + 1);

        if (curTime == storyline.getFixed().get(currProcessIndex[0]).getRemainTime()) {
            currProcessIndex[0]++;
            return storyline.getFixed().get(currProcessIndex[0]-1).getStory();
        }
        else if (probability > 0.3) { //
            Story story = storyline.getRandom().get(randomIndex);
            storyline.getRandom().remove(randomIndex);
            return story;
        }
        else if (storyline.getQueue().get(currProcessIndex[2]).getRemainTime() <= curTime) {
            currProcessIndex[2]++;
            return storyline.getQueue().get(currProcessIndex[2]-1).getStory();
        }
        else  {
            currProcessIndex[1]++;
            return storyline.getDaily().get(currProcessIndex[1]-1);
        }
    }
}
