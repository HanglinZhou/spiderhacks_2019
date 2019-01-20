import java.util.ArrayList;
//import com.sun.org.apache.xpath.internal.operations.Bool;


public class User {

    final int STATUS_NUM = 6;
    String name;
    String gender;
    int age;

    //increasing index: academic, social, health, money, (confidence), love relationship
    int[] status;
    ArrayList<Decision> decisionHistory;
    String major;
    String majorCategory;
    int year;
    double units;

    public User(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public int getSTATUS_NUM() {
        return STATUS_NUM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int[] getStatus() {
        return status;
    }

    public void setStatus(int[] status) {
        this.status = status;
    }

    public ArrayList<Decision> getDecisionHistory() {
        return decisionHistory;
    }

    public void setDecisionHistory(ArrayList<Decision> decisionHistory) {
        this.decisionHistory = decisionHistory;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajorCategory() {
        return majorCategory;
    }

    public void setMajorCategory(String majorCategory) {
        this.majorCategory = majorCategory;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }


}
