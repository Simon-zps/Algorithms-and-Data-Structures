import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computing {

    private final int numberOfProcesses = 10;
    private final int maxProcessTime = 1500;
    private ArrayList<Process> processes = new ArrayList<>();
    private CPU FCFS = new CPU(SchedulingType.FCFS);
    private CPU SJF = new CPU(SchedulingType.SJF);
    private CPU SRTF = new CPU(SchedulingType.SRTF);
    private CPU RR = new CPU(SchedulingType.RR);

    private int timer = 0;
    private int quantTime = 50;

    private SchedulingType type;

    public Computing(SchedulingType type) {
        this.type = type;
    }

    public void testCPU(){

        Random random = new Random();

        for (int i = 0; i < numberOfProcesses; i++) {
            processes.add(new Process(random.nextInt(maxProcessTime)));
        }

        CPU test = null;
        switch(type){
            case FCFS:{
                test = FCFS;
                System.out.println("For FCFS:");
                break;
            }
            case SJF:{
                test = SJF;
                System.out.println("For SJF:");
                break;
            }
            case SRTF:{
                test = SRTF;
                System.out.println("For SRTF:");
                break;
            }
            case RR:{
                test = RR;
                System.out.println("For RR:");
                break;
            }
        }

        boolean doContinue = true;
        while (doContinue) {
            int processIndex = timer / 100;
            if (timer % 100 == 0 && processIndex < processes.size()) {
                assert test != null;
                test.addProcess(processes.get(processIndex));
            }

            assert test != null;
            test.runProcess(quantTime);
            timer += quantTime;

            // You can choose an appropriate termination condition,
            // for example, stop when all processes have completed
            boolean allProcessesCompleted = true;
            for (Process process : processes) {
                if (process.remaining_time > 0) {
                    allProcessesCompleted = false;
                    break;
                }
            }

            if (allProcessesCompleted) {
                doContinue = false;
            }
        }

        int totalWaitTime = 0;
        int totalDuration = 0;
        int numRelevantProcesses = 5;
        for (int i = 0; i < numRelevantProcesses; i++) {
            totalWaitTime += processes.get(i).waiting_time;
            totalDuration += processes.get(i).total_time;
        }

        double AVGwaitTime = totalWaitTime / (double) numRelevantProcesses;
        int totalWorkTime = totalWaitTime + totalDuration;
        double AVGworkTime = totalWorkTime / (double) numRelevantProcesses;


        System.out.println("Avg Waiting Time: " + AVGwaitTime + "ms");
        System.out.println("Avg Working Time: " + AVGworkTime + "ms");


        for (int i = 0; i < processes.size(); i++) {
            System.out.printf("Process %d effectiveness: %.3f%n", i + 1, processes.get(i).total_time * 1.0 / (processes.get(i).total_time + processes.get(i).waiting_time));
        }
        System.out.println();
    }
}