import java.util.*;
import java.awt.Font;

public class DrawGame {
  public static int[] userStatus = new int[5];
  public final static int fullSt = 100;
  public final static double dist = 10;              // dist btw status bar
  public final static double centerX = 7.25;         // start centerX
  public final static double centerY = 42.7;         // start centerY
  public final static double length = 6;             // length of status bar
  public final static double barHeight = 0.2;
  public static String name = "";
  public static String gender = "";

  public static void main(String[] args) {

    setStart();

    showHomePage();

    infoAsker();

    /*
    Integer[] arr = {10, 30, 50, 100, 30};
    updateGUIStatus(arr);

    Integer[] arr1 = {10, -30, 50, -75, 30};
    String p = "wanna sleep?";
    HashMap<String, Integer[]> hm = new HashMap<>();
    hm.put("of course! I'm not kk", arr1);
    hm.put("certainly! I'm not kk", arr1);
    hm.put("without doubt! I'm not kk", arr1);

    Decision dc = new Decision(p, hm);
    String outChoice = makeChoice(dc);
    System.out.println("choimade is: " + outChoice);
    */




  }

  public static void setStart() {
    StdDraw.setCanvasSize(800, 500);
    StdDraw.setXscale(0, 80);
    StdDraw.setYscale(0, 50);

    startPage();
  }

  public static void startPage() {
    StdDraw.picture(40, 25, "start1.png", 80, 50);
    StdDraw.show(500);

    Font c = new Font("Courier", Font.BOLD, 30);
    StdDraw.setFont(c);
    while (true) {
      if (StdDraw.hasNextKeyTyped()) {
        if (StdDraw.isKeyPressed(83)) {
          StdDraw.nextKeyTyped();
          break;
        }
      }
      StdDraw.text(40, 15, "Type s to start");
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.show(100);

      if (StdDraw.hasNextKeyTyped()) {
        if (StdDraw.isKeyPressed(83)) {
          StdDraw.nextKeyTyped();
          break;
        }
      }

      StdDraw.text(40, 15, "Type s to start");
      StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
      StdDraw.show(100);

      if (StdDraw.hasNextKeyTyped()) {
        if (StdDraw.isKeyPressed(83)) {
          StdDraw.nextKeyTyped();
          break;
        }
      }
    }
    return;
  }

  // input: user status, next story to show
  public static void showHomePage() {
    StdDraw.clear();
    StdDraw.picture(40, 25, "homepage.png", 80, 53);

    Font f = new Font("Dialog", Font.PLAIN, 14);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setFont(f);

    if (name != "") {
      StdDraw.textLeft(67, 6, "name: " + name);
    }
    if (gender != "") {
      StdDraw.textLeft(67, 4, "gender: " + gender);
    }
    if (Driver.getTime() >= 0) {
      //System.out.println("time left > 0");
      StdDraw.textLeft(67, 2, "time left: " + Driver.getTime());
    }

    double x = centerX;
    for (int i = 0; i < 5; i++) {

      //StdDraw.setPenColor(StdDraw.DARK_GRAY);
      double barLength = length * (double)(userStatus[i]/(double)fullSt);
      pushNewStatus(barLength, x);
      x += dist;
    }

    StdDraw.show();
  }

  // input: new status differences
  // change the color of the status based on input
  public static void updateGUIStatus(Integer[] newStatusDiff) {
    double x = centerX;
    for (int i = 0; i < 5; i++) {
      /*
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledRectangle(x, centerY, length / 2, barHeight);
      */

      if (newStatusDiff[i] < 0) {
        StdDraw.setPenColor(StdDraw.RED);
      } else if (newStatusDiff[i] > 0) {
        StdDraw.setPenColor(StdDraw.GREEN);
      }

      userStatus[i] += newStatusDiff[i];
      double barLength = length * (double)(userStatus[i]/(double)fullSt);

      if (barLength > length)
        barLength = length;

      double actualX = barLength / 2 + (x - length * 0.5);
      StdDraw.filledRectangle(actualX, centerY, barLength / 2, barHeight);
      StdDraw.show(170);

      pushNewStatus(barLength, x);

      x += dist;
    }
  }

  // input: the current barlength of one STATUS, and x is the center of bar
  // make the status stay onto the current bar length
  public static void pushNewStatus(double barLength, double x) {
    double actualX = barLength / 2 + (x - length * 0.5);
    //System.out.println(barLength);
    /*
    StdDraw.filledRectangle(actualX, centerY, barLength / 2, barHeight);
    StdDraw.show(170);
    */

    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.filledRectangle(x, centerY, length / 2, barHeight);

    StdDraw.setPenColor(StdDraw.DARK_GRAY);
    StdDraw.filledRectangle(actualX, centerY, barLength / 2, barHeight);
    StdDraw.show();
  }


  // input: the ongoing event
  // output: the decision as a String
  public static String makeChoice(Decision dec) {
    double textX = 20;
    Font promptFont = new Font("Dialog", Font.BOLD, 18);
    StdDraw.setFont(promptFont);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.textLeft(textX, 37, dec.getPrompt());
    StdDraw.show(1000);


    Set<String> choiceBuffer = dec.getOptions().keySet();  // all choices
    int numChoices = choiceBuffer.size();
    String[] choices = new String[numChoices];
    int i = 0;
    //String showChoices = "";
    double textY = 32;
    double distY = 2.5;
    StdDraw.setPenColor(StdDraw.DARK_GRAY);
    for (String c : choiceBuffer) {
      choices[i] = c;
      c = "    " + c;

      Font instFont = new Font("Dialog", Font.PLAIN, 14);
      StdDraw.setFont(instFont);

      StdDraw.textLeft(textX, textY, "Type " + i + " on keyboard: ");
      textY -= distY;

      int j = 0;
      int numOneLine = 55;
      Font choiceFont = new Font("Dialog", Font.BOLD, 18);
      StdDraw.setFont(choiceFont);
      while (j < c.length()) {
        int end = j + numOneLine;
        if (c.length() < j + numOneLine) {
          end = c.length();
        }
        StdDraw.textLeft(textX, textY, c.substring(j, end));
        textY -= distY;
        j += numOneLine;
      }
      textY -= distY;
      StdDraw.show(100);
      i++;
    }


    //StdDraw.textLeft(textX, 25, showChoices);
    StdDraw.show();

    // now user make the choice
    while (true) {
      if (StdDraw.hasNextKeyTyped()) {
        char charOfChoice = StdDraw.nextKeyTyped();
        if (charOfChoice > 47 && charOfChoice < 48 + numChoices) {
            // if legitmate choices made, return that choice
            //showHomePage();
            updateGUIStatus(dec.getOptions().get(choices[charOfChoice - 48]));
            showHomePage();
            dec.setChoice(choices[charOfChoice - 48]);
            return dec.getChoice();
          }
      }
    }
  }

  public static String[] infoAsker() {
    int numInfo = 3;
    String[] str = {"", "", ""};
    int ntStartY = -25;
    int ntEndY = 23;
    int x = 47;
    for (int i = ntStartY; i < ntEndY; i+=3) {
      StdDraw.clear();
      showHomePage();
      StdDraw.picture(x, i, "infoNote.png", 60, 52);
      StdDraw.show(200);
    }

    Font f = new Font("Courier", Font.PLAIN, 15);
    StdDraw.setFont(f);
    StdDraw.setPenColor(StdDraw.BLACK);

    int y = ntEndY + 8;
    StdDraw.text(x, y, "some basic info before we start...");
    y -= 3.5;

    StdDraw.text(x, y, Driver.nameAsker());
    y -= 2;
    StdDraw.text(x, y, "_______________(hit enter after finish)");
    StdDraw.show(500);
    int infoX = x - 15;
    while (true) {
      if (StdDraw.hasNextKeyTyped()) {
        char letter = StdDraw.nextKeyTyped();
        if (letter == '\n') {
          break;
        }//enter
        if ((letter >= 'A' && letter <= 'Z') ||
            (letter >= 'a' && letter <= 'z') ||
          letter == ' ') {
          StdDraw.text(infoX, y+0.25, "" + letter);
          str[0] += letter;
          infoX += 1;
          StdDraw.show(200);
        }
      }
    }
    name = str[0];

    y-= 3.5;
    StdDraw.text(x, y, Driver.genderAsker());
    y -= 2;
    StdDraw.text(x, y, "_______________(hit enter after finish)");
    StdDraw.show(500);
    infoX = x - 15;
    while (true) {
      if (StdDraw.hasNextKeyTyped()) {
        char letter = StdDraw.nextKeyTyped();
        if (letter == '\n') //enter
          break;
        if ((letter >= 'A' && letter <= 'Z') ||
            (letter >= 'a' && letter <= 'z') ||
          letter == ' ') {
          StdDraw.text(infoX, y+0.25, "" + letter);
          str[1] += letter;
          infoX += 1;
          StdDraw.show(200);
        }
      }
    }
    gender = str[1];
    showHomePage();

    Decision tm = Driver.timeAsker();
    makeChoice(tm);
    str[2] = tm.getChoice().substring(0, 3);
    if (tm.getChoice().charAt(2) == ' ') {
      str[2] = tm.getChoice().substring(0, 2);
    }

    Driver.setTime(Integer.parseInt(str[2]) * 4);
    showHomePage();
    return str;


  }

}
