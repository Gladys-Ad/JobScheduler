import java.util.ArrayList;

public class ShortestRemainingTime extends Scheduler {

    //list for jobs and list for jobs that have been serviced and formatted
    private ArrayList<Job> jobList;
    private ArrayList<Job> doneJobs = new ArrayList<Job>();
    private Job job;
    private int timeElapse = 0;

    //constructor
    public ShortestRemainingTime(ArrayList jobList) {
        this.jobList = jobList;
    }

    //run scheduling
    public void execute() {

        while (!jobList.isEmpty()) {

            job = getShortestRemainingJob(timeElapse);
            formatGraph(job, timeElapse);
            job.run(1);
            timeElapse++;

            //job has been completely serviced, remove job from the joblist and place into donelist
            if (job.getDuration() == 0) {
                jobList.remove(job);
                addToDoneJobs(job, doneJobs);

                if (jobList.size() > 0) {
                    job = jobList.get(0);
                }
            } else {
                jobList.remove(job);
                placeInList(job, timeElapse, jobList);
            }
        }

        for (Job j : doneJobs) {
            System.out.println(j.getLine());
        }

    }

    //find the next job with the shortest remaining service time
    public Job getShortestRemainingJob(int timeElapse) {
        Job shortestRemainingJob = job;
        for (Job j : jobList) {
            if (timeElapse == 0) {
                shortestRemainingJob = jobList.get(0);
                break;
            } else if (!(j.equals(job)) && j.getArrivalTime() <= timeElapse && j.getDuration() < job.getDuration()) {
                shortestRemainingJob = j;

            }
        }
        return shortestRemainingJob;
    }


}
