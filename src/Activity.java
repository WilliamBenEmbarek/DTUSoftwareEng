/**
 * Created by Emil on 27/03/2017.
 */
public abstract class Activity {
    private String name;
    private int startWeek;
    private int  endWeek;

    public Activity(String name, int startWeek) {
        this.name = name;
        this.startWeek = startWeek;
    }

    public Activity(String name, int startWeek, int endWeek) {
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public String toString(){
        return this.name;
    }
}
