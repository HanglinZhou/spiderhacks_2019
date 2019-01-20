import java.util.*;

public class Driver {
    private static int time = -10;

    public static void setTime(int tm) {
      time = tm;
    }

    public static int getTime() {
      return time;
    }

    public static String nameAsker() {
      return "What's your name, friend? (support upper/lowercase letters)";
    }

    public static String genderAsker() {
      return "How do you identify your gender?";
    }

    public static Decision timeAsker() {
      String prompt = "How many rounds would you like to play for a total of 4 yrs?";
      HashMap<String, Integer[]> hm = new HashMap<>();
      Integer[] arr = {0, 0, 0, 0, 0};
      hm.put("10 rounds per Yr", arr);
      hm.put("20 rounds per Yr", arr);
      hm.put("50 rounds per Yr", arr);
      hm.put("365 rounds per Yr", arr);
      Decision tmAsker = new Decision(prompt, hm);
      return tmAsker;
    }

    public static void main(String[] args) {
      DrawGame.setStart();
      DrawGame.showHomePage();

      String[] str = DrawGame.infoAsker();
      //0: name; 1: gender; 2: time

      //setup
      time = Integer.parseInt(str[2]) * 4;
      User user = new User(str[0], str[1]);
      DrawGame.infoAsker();
      Executor exe = new Executer("input.txt");

      for (int i = 0; i < time; i++) {
        Story st = exe.pickNextStory();
        DrawGame.makeChoice(st.getDecision());
      }



    }


}
