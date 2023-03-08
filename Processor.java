import java.util.ArrayList;
import java.util.List;

public class Processor {
    private int type;
    private List<Process> processes = new ArrayList<>();
    private Process currentProcess = null;
    private int rrTimer = 1000;
    private static int rrMaxTimer = 1000;

    public Processor(int type){
        this.type = type;
    }
    /* Types:
    0 -> First Come First Serve FCFS
    1 -> Shortest Job First SJF
    2 -> Shortest Remaining Time First SRTF
    3 -> Round Robin RR
    */
    public void addProcess(Process process){
        processes.add(process);
        if(currentProcess == null){
            currentProcess = process;
        }
        if(type == 2){
            currentProcess = reconsiderProcesses();
        }
    }

    public void doRunProcess(int timeQuantum){
        if(currentProcess == null)
            return;
        currentProcess.remaining_duration-=timeQuantum;
        for(Process temp : processes){
            if(temp != currentProcess)
                temp.waiting_time+=timeQuantum;
        }
        if(currentProcess.remaining_duration<=0) {
            processes.remove(currentProcess);
            currentProcess = reconsiderProcesses();
        }
        if(type == 3){
            rrTimer -= timeQuantum;
            if(rrTimer <= 0){
                processes.remove(currentProcess);
                processes.add(currentProcess);
                currentProcess = processes.get(0);
                rrTimer = rrMaxTimer;
            }
        }
    }

    private Process reconsiderProcesses(){
        if(processes.size() == 0)
            return null;
        Process newCurrentProcess = processes.get(0);
        switch (type){
            case 0,3:{
                break;
            }
            case 1:{
                for(Process temp : processes){
                    if(temp.total_duration< newCurrentProcess.total_duration)
                        newCurrentProcess = temp;
                }
                break;
            }
            case 2:{
                for(Process temp : processes){
                    if(temp.remaining_duration< newCurrentProcess.remaining_duration)
                        newCurrentProcess = temp;
                }
                break;
            }
        }
        return newCurrentProcess;
    }

}