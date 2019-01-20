import java.util.*;

public class DrawGame {
  public static int[] userStatus = new int[5];
  public final static int fullSt = 100;
  public final static double dist = 10;              // dist btw status bar
  public final static double centerX = 7.25;         // start centerX
  public final static double centerY = 42.7;         // start centerY
  public final static double length = 6;             // length of status bar
  public final static double barHeight = 0.2;

  public static void main(String[] args) {

    setStart();

    showHomePage();

    int[] arr = {10, 30, 50, 100, 30};
    updateGUIStatus(arr);



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

    while (true) {
      StdDraw.setPenRadius(20);
      StdDraw.text(40, 15, "Type s to start");
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.show(100);

      if (StdDraw.hasNextKeyTyped()) {
        if (StdDraw.isKeyPressed(83))
          break;
      }

      StdDraw.text(40, 15, "Type s to start");
      StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
      StdDraw.show(100);

      if (StdDraw.hasNextKeyTyped()) {
        if (StdDraw.isKeyPressed(83))
          break;
      }
    }
    return;
  }

  // input: user status, next story to show
  public static void showHomePage() {
    StdDraw.clear();
    StdDraw.picture(40, 25, "homepage.png", 80, 53);

    StdDraw.setPenColor(StdDraw.WHITE);
    double x = centerX;
    for (int i = 0; i < 5; i++) {
      StdDraw.filledRectangle(centerX, centerY, length / 2, barHeight);
      x += dist;
    }

    StdDraw.show();
  }

  // input: new status differences
  public static void updateGUIStatus(int[] newStatusDiff) {
    double x = centerX;
    for (int i = 0; i < 5; i++) {
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledRectangle(x, centerY, length / 2, barHeight);

      if (newStatusDiff[i] < 0) {
        StdDraw.setPenColor(StdDraw.RED);
      } else if (newStatusDiff[i] > 0) {
        StdDraw.setPenColor(StdDraw.GREEN);
      }

      userStatus[i] += newStatusDiff[i];
      double barLength = length * (double)(userStatus[i]/(double)fullSt);

      if (barLength > length)
        barLength = length;

      double actualX = barLength/2 + (x - length*0.5);
      System.out.println(barLength);
      StdDraw.filledRectangle(actualX, centerY, barLength / 2, barHeight);
      StdDraw.show(100);
      StdDraw.setPenColor(StdDraw.DARK_GRAY);
      StdDraw.filledRectangle(actualX, centerY, barLength / 2, barHeight);
      StdDraw.show(100);

      x += dist;
    }
  }

  // input: the ongoing event
  // output: the decision as a String
  public static String makeChoice(Story st) {

  }

}
