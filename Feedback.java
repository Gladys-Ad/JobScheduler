import java.util.*;

public class Feedback extends Scheduler {

    //the three lists represent the three queues
    private Job job;
    private ArrayList<Job> jobList;
    private ArrayList<Job> lowerJobList = new ArrayList();
    private ArrayList<Job> lowestJobList = new ArrayList();
    private ArrayList<Job> doneJobs = new ArrayList<Job>();
    ;
    private int quantum = 1;
    private int timeElapse = 0;

    //constructor
    public Feedback(ArrayList jobList) {
        this.jobList = jobList;
    }

    //run scheduling
    public void execute() {

        //keep scheduling until there are no jobs in all the queues
        while (!(jobList.isEmpty() && lowerJobList.isEmpty() && lowestJobList.isEmpty())) {

            job = getJob();

            if (job.getDuration() < quantum) {
                formatGraph(job, timeElapse);
                job.run(job.getDuration());
                timeElapse += quantum;
            } else {
                formatGraph(job, timeElapse);
                job.run(quantum);
                timeElapse += quantum;
            }

            //if job has been completely serviced, remove from joblist and move to the donelist. if not put back into list
            if (job.getDuration() == 0) {
                removeJob(job);
                addToDoneJobs(job, doneJobs);
            } else {
                placeInList(job);
            }
        }

        //print formated graph
        for (Job j : doneJobs) {
            System.out.println(j.getLine());

        }
    }

    //gets the next job that needs to be serviced
    private Job getJob() {

        //service the first job if this is the start of the scheduler
        if (timeElapse == 0) {
            return jobList.get(0);
        } else {
            //service the next job if there are still jobs in jobList and that next job has already arrived or is arriving at the time
            if (!jobList.isEmpty() && jobList.get(0).getArrivalTime() <= timeElapse) {
                return jobList.get(0);
            }

            //check if there are jobs waiting in the second queue and service jobs there
            else if (lowerJobList.size() != 0) {
                return lowerJobList.get(0);
            }

            //service jobs in the last queue
            else
                return lowestJobList.get(0);
        }
    }

    //remove job from the respective queue. this is done when a job has been completely serviced
    private void removeJob(Job job) {
        if (jobList.contains(job)) {
            jobList.remove(job);
        } else if (lowerJobList.contains(job)) {
            lowerJobList.remove(job);
        } else
            lowestJobList.remove(job);
    }

    //place job back in the queue its suppose to. this is done when a job has a service time greater than the quantum
    private void placeInList(Job job) {
        if (jobList.contains(job)) {
            jobList.remove(job);

            //if that was the last job in the queue, move job to the next queue
            if (jobList.isEmpty()) {
                lowerJobList.add(job);
            }

            //if not, then if there aren't any jobs in the other queue, put the job back into this queue (meaning it will run again)
            else if (jobList.get(0).getArrivalTime() > timeElapse && lowerJobList.isEmpty() && lowestJobList.isEmpty()) {
                jobList.add(0, job);
            } else
                lowerJobList.add(job);
        }

        //move to the next relative queue
        else if (lowerJobList.contains(job)) {
            lowerJobList.remove(job);
            lowestJobList.add(job);
        }

        //if job was originally from the last queue, then enque again in the last queue to become a FIFO for the last queue
        else {
            lowestJobList.remove(job);
            lowestJobList.add(job);
        }
    }


}
