//
public class Executer {
    int time;
    Storyline storyline;
    String file;
    int[] currProcessIndex; //store the indices to fixed, daily, random, randomtriggered


    public Executer(int time, String file) {
        this.time = time;
        this.file = file;
        storyline = new Storyline(file, time);
        currProcessIndex = new int[]{0,0,0,0};

    }

    public Story pickNextStory(int curTime){
        int allRandomEvent = random.size();
        Random r = new Random();
        double probability = r.nextDouble();
        int randomIndex = r.nextInt(allRandomEvent + 1);

        if (curTime == storyline.getFixed.get(currProcessIndex[0]).getRemainTime()) {
            currProcessIndex[0]++;
            return storyline.getFixed.get(currProcessIndex[0]-1).getStory());
        }
        else if (probability > 0.3) { //
            
        }
    }
}
