import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;

public class Storyline {
    final int STATUS_NUM = 5;

    public class PipelinedEvent {
        Story story;
        int remainTime; //fixed: will be actual time

        public Story getStory() {
            return story;
        }

        public void setStory(Story story) {
            this.story = story;
        }

        public int getRemainTime() {
            return remainTime;
        }

        public void setRemainTime(int remainTime) {
            this.remainTime = remainTime;
        }



        public PipelinedEvent(Story story, int remainTime) {
            this.story = story;
            this.remainTime = remainTime;
        }
    }

    ArrayList<PipelinedEvent> fixed;
    ArrayList<Story> daily;
    ArrayList<Story> random;
    HashMap<String, Story> randomTriggered; //key as the outcome of some decision, value is the triggered event
    Queue<PipelinedEvent> queue;
    int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }



    public Storyline(String fileName, int time) throws IOException {
        this.time = time;



        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));


        String st;
        Type curType = Type.DEFAULT;
        while ((st = br.readLine()) != null) {
            if (st.equals("fixed")) {
                curType = Type.FIXED;
                break;
            }
            else if (st.equals("daily")) {
                curType = Type.DAILY;
                break;
            }
            else if (st.equals("random")) {
                curType = Type.RANDOM;
                break;
            }
//            else if (st.equals("randomTriggered")) {
//                curType = Type.RANDOMTRIGGERED;
//                break;
//            }
            else if (st.equals("dailyTriggered")) {
                curType = Type.DAILYTRIGGERED;
                break;
            }

            storeStory(br, curType, st);

        }


    }


    public ArrayList<PipelinedEvent> getFixed() {
        return fixed;
    }

    public void setFixed(ArrayList<PipelinedEvent> fixed) {
        this.fixed = fixed;
    }

    public ArrayList<Story> getDaily() {
        return daily;
    }

    public void setDaily(ArrayList<Story> daily) {
        this.daily = daily;
    }

    public ArrayList<Story> getRandom() {
        return random;
    }

    public void setRandom(ArrayList<Story> random) {
        this.random = random;
    }

    public HashMap<String, Story> getRandomTriggered() {
        return randomTriggered;
    }

    public void setRandomTriggered(HashMap<String, Story> randomTriggered) {
        this.randomTriggered = randomTriggered;
    }

    public Queue<PipelinedEvent> getQueue() {
        return queue;
    }

    public void setQueue(Queue<PipelinedEvent> queue) {
        this.queue = queue;
    }

    private void storeStory(BufferedReader br, Type type, String st) throws IOException{
        while ((st = br.readLine()) != null) {
            HashMap<String, Integer[]> options = new HashMap<>();
            HashMap<String, String> textAfterChoices = new HashMap<>();
            String prompt = "";
            double timeRatio = 0;
            String cause = "";
            String textAfterChoice = "";
            if (st.charAt(0) != '-') { //prompt
                prompt = st;
                while ((st = br.readLine()) != null) {
                    String option = "";
                    Integer[] scores = new Integer[STATUS_NUM];
                    if (st.charAt(0) == '_') { //option
                        option = st;
                    }
                    else if (st.chatAt(0) == '>')
                        timeRatio = Double.parseDouble(st.substring(1));
                    else if (st.charAt(0) == '+')
                        cause = st.substring(1);
                    else if (st.charAt(':')) {
                        textAfterChoice = st.substring(1);
                        textAfterChouces.put(option, textAfterChoice);
                    }
                    else {
                        String[] scoresString = st.split(" ");
                        for (int i = 0; i < scores.length; i++) {
                            scores[i] = Integer.parseInt(scoresString[i]);
                        }
                    }
                    options.put(option, scores);
                }

                Decision decision = new Decision(prompt, options, textAfterChoices);
                Story story = new Story(decision, type);


                if (type == Type.FIXED) { //todo
                    int remainTime = timeRatio * time;
                    fixed.add(new PipelinedEvent(story, remainTime));
                }

                else if (type == Type.DAILY) {
                    daily.add(story);
                }
                else if (type == Type.RANDOM) {
                    random.add(story);
                }
                else { //randomtriggered
                    randomTriggered.put(cause, story);
                    int remainTime = timeRatio * time+time;
                    queue.add(new PipelinedEvent(story, remainTime));
                }
            }

        }

    }




}
