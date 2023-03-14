import java.util.ArrayList;
import java.util.List;

public class CPU {
    private int type;
    private final List<Process> proces = new ArrayList<>();
    private Process currentProcess = null;
    private int rrTimer = 1000;
    private static int rrMaxTimer = 1000;

    public CPU(int type){
        this.type = type;
    }
    /* Types:
    0 -> First Come First Serve FCFS
    1 -> Shortest Job First SJF
    2 -> Shortest Remaining Time First SRTF
    3 -> Round Robin RR
    */
    public void addProcess(Process process){
        this.proces.add(process);
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
        currentProcess.remaining_time -=timeQuantum;
        for(Process temp : proces){
            if(temp != currentProcess)
                temp.waiting_time+=timeQuantum;
        }
        if(currentProcess.remaining_time <=0) {
            proces.remove(currentProcess);
            currentProcess = reconsiderProcesses();
        }
        if(type == 3){
            rrTimer -= timeQuantum;
            if(rrTimer <= 0){
                proces.remove(currentProcess);
                proces.add(currentProcess);
                currentProcess = proces.get(0);
                rrTimer = rrMaxTimer;
            }
        }
    }

    private Process reconsiderProcesses(){
        if(proces.size() == 0)
            return null;
        Process newCurrentProcess = proces.get(0);
        switch (type){
            case 0,3:{
                break;
            }
            case 1:{
                for(Process temp : proces){
                    if(temp.total_time < newCurrentProcess.total_time)
                        newCurrentProcess = temp;
                }
                break;
            }
            case 2:{
                for(Process temp : proces){
                    if(temp.remaining_time < newCurrentProcess.remaining_time)
                        newCurrentProcess = temp;
                }
                break;
            }
        }
        return newCurrentProcess;
    }

}