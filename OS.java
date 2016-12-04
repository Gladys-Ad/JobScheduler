import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class OS {

    public static void main(String args[]) {
        //Read jobs from file and put job name, arrival time, and duration into arraylist
        BufferedReader jobReader = null;
        String fileLine;
        String[] jobInfo;
        ArrayList<Job> jobList = new ArrayList<Job>();

        int schedulerNumber = 0;
        //run scheduler that "user" wants to use, repeat until program is ended
        while (schedulerNumber != 7) {
            try {
                //Get text file to read from user input
                jobReader = new BufferedReader(new FileReader(new File(args[0])));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                while ((fileLine = jobReader.readLine()) != null) {
                    //create job objects and add it to array list of jobs
                    jobInfo = fileLine.split("\\s+");
                    Job job = new Job(jobInfo[0], Integer.parseInt(jobInfo[1]), Integer.parseInt(jobInfo[2]));
                    jobList.add(job);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            System.out.println();

            //Request for scheduling number
            System.out.println("Please enter the number of the scheduler you wish to handle this job:");
            System.out.println("1 First Come First Served\n2 Round Robin\n3 Shortest Process Next\n4 Shortest Remaining Time\n5 Highest Response Ration Next\n6 Feedback\n7 Quit Scheduler");

            //Scan for user's input for choice of schedule
            Scanner scan = new Scanner(System.in);
            schedulerNumber = scan.nextInt();
            System.out.println();

            //create respective scheduler
            switch (schedulerNumber) {
                //FCFS
                case 1:
                    FirstComeFirstServed fcfs = new FirstComeFirstServed(jobList);
                    fcfs.execute();
                    break;

                //RR
                case 2:
                    RoundRobin rr = new RoundRobin(jobList);
                    rr.execute();
                    break;

                //SPN
                case 3:
                    ShortestProcessNext spn = new ShortestProcessNext(jobList);
                    spn.execute();
                    break;

                //SRT
                case 4:
                    ShortestRemainingTime srt = new ShortestRemainingTime(jobList);
                    srt.execute();
                    break;

                //HRRN
                case 5:
                    HighestResponseRatioNext hrrn = new HighestResponseRatioNext(jobList);
                    hrrn.execute();
                    break;

                //feedback
                case 6:
                    Feedback fb = new Feedback(jobList);
                    fb.execute();
                    break;

                //close bufferReader and exit program
                case 7:
                    try {
                        jobReader.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    System.exit(0);
                    break;

                //error
                default:
                    System.out.println("Please select a valid scheduler!");
                    break;
            }
        }

    }
}
