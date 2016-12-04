/**
 * Created by gladysadjei on 11/24/16.
 */

import java.util.*;

public class FirstComeFirstServed extends Scheduler {

    //list for jobs and list for jobs that have been serviced and formated
    private ArrayList<Job> jobList;
    private ArrayList<Job> doneJobs = new ArrayList<Job>();
    private Job job;
    private int timeElapse = 0;

    //constructor
    public FirstComeFirstServed(ArrayList<Job> list) {
        jobList = list;
    }

    public void execute() {
        //service jobs while there are jobs needing to be scheduled and print the formatted graph
        while (!jobList.isEmpty()) {

            job = jobList.get(0);
            formatGraph(job, timeElapse);
            timeElapse += job.getDuration();

            job.run();
            jobList.remove(job);
            addToDoneJobs(job, doneJobs);
        }

        for (Job j : doneJobs) {
            System.out.println(j.getLine());
        }

    }
}
