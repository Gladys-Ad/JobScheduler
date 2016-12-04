public class Job {

    private String name;
    private int arrivalTime;
    private int duration;
    private String line = "";

    //constructor
    public Job(String name, int arrivalTime, int duration) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    //necessary getter and setter
    public int getDuration() {
        return duration;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String l) {
        line = l;
    }

    //non-preemptive run method
    public void run() {
        while (duration > 0) {
            line = line + name;
            duration--;
        }
    }

    //preemptive run method
    public void run(int time) {
        duration -= time;
        while (time > 0) {
            line = line + name;
            time--;
        }
    }

}
