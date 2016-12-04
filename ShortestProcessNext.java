import java.util.ArrayList;


public class ShortestProcessNext extends Scheduler {

    //list for jobs and list for jobs that have been serviced and formatted
    private Job job;
    private ArrayList<Job> jobList;
    private ArrayList<Job> doneJobs = new ArrayList<Job>();
    private int timeElapse = 0;

    //constructor
    public ShortestProcessNext(ArrayList jobList) {
        this.jobList = jobList;
    }

    //run scheduling
    public void execute() {
        job = jobList.get(0);
        timeElapse += job.getDuration();
        job.run();
        jobList.remove(job);
        addToDoneJobs(job, doneJobs);

        while (!jobList.isEmpty()) {
            job = findNextShortestJob(timeElapse);
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

    //look through job list and find the job with the shortest service time
    private Job findNextShortestJob(int timeElapse) {
        Job shortestJob = jobList.get(0);
        for (Job i : jobList) {
            if (i.getArrivalTime() < timeElapse && i.getDuration() < shortestJob.getDuration()) {
                shortestJob = i;
            }
        }
        return shortestJob;
    }
}
