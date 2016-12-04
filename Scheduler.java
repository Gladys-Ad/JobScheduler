
import java.util.*;

public abstract class Scheduler {

    //abstract execute method for respective scheduling
    protected abstract void execute();

    //method where formated jobs that have serviced are put into an arraylist that would be printed to console after all jobs are serviced
    public void addToDoneJobs(Job job, ArrayList<Job> doneJobs) {
        for (int i = 0; i < doneJobs.size(); i++) {
            if (job.getArrivalTime() < doneJobs.get(i).getArrivalTime()) {
                doneJobs.add(i, job);
                break;
            }
        }
        if (doneJobs.contains(job) == false) {
            doneJobs.add(job);
        }
    }

    //format "graph" of jobs, adds spaces where needed in order for the display to look like a graph
    public void formatGraph(Job job, int timeElapse) {
        int spaces = timeElapse - job.getLine().length();
        while (spaces > 0) {
            String line = job.getLine();
            line = line + " ";
            job.setLine(line);
            spaces--;
        }
    }

    //for preemptive scheduling where jobs are returned to the list if service time is greater than quantum
    public void placeInList(Job job, int timeElapse, ArrayList<Job> jobList) {
        for (int i = 0; i < jobList.size(); i++) {
            if (jobList.get(i).getArrivalTime() > timeElapse) {
                jobList.add(i, job);
                break;
            }
        }
        if (jobList.contains(job) == false) {
            jobList.add(job);
        }
    }

}
