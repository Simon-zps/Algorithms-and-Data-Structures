import java.util.ArrayList;
import java.util.List;

public class CPU {
    private SchedulingType type;
    private final List<Process> processes = new ArrayList<>();
    private Process currentProcess = null;
    private int remainingTimeForCurrent = 1000;

    public CPU(SchedulingType type){
        this.type = type;
    }
    public void addProcess(Process process){
        this.processes.add(process);
        if(currentProcess == null){
            currentProcess = process;
        }
        if(type == SchedulingType.SRTF){
            currentProcess = reconsiderProcesses();
        }
        /*Metoda addProcess wprost sprawdza tylko typ harmonogramowania SRTF, ponieważ jest to jedyny typ
         harmonogramowania, który wymaga specjalnej akcji po dodaniu nowego procesu.
         W SRTF (Shortest Remaining Time First), aktualnie działający
        proces może zostać przerwany (zatrzymany), aby rozpocząć nowy proces z krótszym pozostałym czasem wykonania.*/
    }

    public void runProcess(int quantTime){
        /*Funkcja runProcess jest częścią algorytmu planowania procesów, Kwantd czasu odnosi się do czasu, który jest przydzielony
         każdemu procesowi na wykonywanie w jednym cyklu planowania. W tej funkcji, quantTime to argument wejściowy,
          który reprezentuje kwant czasu.
          W funkcji runProcess, kwant czasu jest używany do zmniejszenia pozostałego czasu bieżącego procesu.
          Oznacza to, że każdy proces jest wykonywany przez określony kwant czasu, a następnie jest przerywany
          i przechodzi do kolejnego procesu w kolejce, jeśli istnieją jeszcze procesy, które czekają na wykonanie.

            Jeśli natomiast algorytmem szeregowania jest Round Robin (RR), to po zakończeniu kwantu czasu, sprawdzane jest,
            czy czas wykonania obecnie uruchomionego procesu nie przekroczył maksymalnego czasu jednego "obiegu"
            (w tym przypadku 1000 jednostek czasu). Jeśli czas wykonania przekroczył ten limit,
            to obecny proces jest usuwany z listy procesów i
            dodawany z powrotem na jej koniec, a następnie wybierany jest kolejny proces z listy jako nowy obecnie uruchamiany proces.

*/
        if(currentProcess == null)
            return;
        currentProcess.remaining_time -= quantTime;
        for(Process temp : processes){
            if(temp != currentProcess)
                temp.waiting_time += quantTime;
        }
        if(currentProcess.remaining_time <=0) {
            processes.remove(currentProcess);
            currentProcess = reconsiderProcesses();
        }
        if(type == SchedulingType.RR){
            remainingTimeForCurrent -= quantTime;
            if(remainingTimeForCurrent <= 0){
                processes.remove(currentProcess);
                processes.add(currentProcess);
                currentProcess = processes.get(0);
                remainingTimeForCurrent = 1000;
            }
        }
    }

    private Process reconsiderProcesses(){
        if(processes.size() == 0)
            return null;
        Process newCurrentProcess = processes.get(0);
        switch (type) {
            case FCFS, RR -> {
            }
            case SJF -> {
                for (Process temp : processes) {
                    if (temp.total_time < newCurrentProcess.total_time)
                        newCurrentProcess = temp;
                }
            }
            case SRTF -> {
                for (Process temp : processes) {
                    if (temp.remaining_time < newCurrentProcess.remaining_time)
                        newCurrentProcess = temp;
                }
            }
        }
        return newCurrentProcess;
    }

}