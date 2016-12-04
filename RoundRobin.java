/**
 * Created by gladysadjei on 11/24/16.
 */

import java.util.*;

public class RoundRobin extends Scheduler {

    //list for jobs and list for jobs that have been serviced and formated
    private ArrayList<Job> jobList;
    private static ArrayList<Job> doneJobs = new ArrayList<Job>();
    private Job job;
    private int quantum = 1;
    private int timeElapse = 0;

    //constructor
    public RoundRobin(ArrayList<Job> list) {
        jobList = list;
    }

    //run scheduling
    public void execute() {
        while (!jobList.isEmpty()) {
            job = jobList.get(0);
            if (job.getDuration() > quantum) {
                formatGraph(job, timeElapse);
                job.run(quantum);
                timeElapse += quantum;
            } else {
                formatGraph(job, timeElapse);
                timeElapse += job.getDuration();
                job.run(job.getDuration());
            }

            if (job.getDuration() == 0) {
                jobList.remove(job);
                addToDoneJobs(job, doneJobs);
            } else {
                jobList.remove(job);
                placeInList(job, timeElapse, jobList);

            }

        }

        for (Job j : doneJobs) {
            System.out.println(j.getLine());
        }

    }

}

