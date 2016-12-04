import java.util.ArrayList;


public class HighestResponseRatioNext extends Scheduler {

    //list for jobs and list for jobs that have been serviced and formatted
    private ArrayList<Job> jobList;
    private ArrayList<Job> doneJobs = new ArrayList<Job>();
    private Job job;
    private int timeElapse = 0;

    //constructor
    public HighestResponseRatioNext(ArrayList<Job> jobList) {
        this.jobList = jobList;
    }

    //run scheduling
    public void execute() {

        while (!jobList.isEmpty()) {

            job = findHighestRatio();
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

    //find the next job with the highest ration amongst the arrived jobs
    private Job findHighestRatio() {
        Job highestRatioJob = jobList.get(0);
        for (Job j : jobList) {
            if (j.getArrivalTime() <= timeElapse && calculateRatio(j) > calculateRatio(highestRatioJob)) {
                highestRatioJob = j;
            }
        }
        return highestRatioJob;
    }

    //calculate the ratio for a job
    private int calculateRatio(Job job) {
        return ((timeElapse - job.getArrivalTime()) + job.getDuration()) / job.getDuration();
    }


}
