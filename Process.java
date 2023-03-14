public class Process {
    public int total_time;
    public int remaining_time;
    public int waiting_time;

    public Process(int time){
        total_time = time;
        remaining_time = time;
        waiting_time = 0;
    }
}