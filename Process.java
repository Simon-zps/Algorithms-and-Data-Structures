public class Process {
    public int total_duration;
    public int remaining_duration;
    public int waiting_time;

    public Process(int duration){
        total_duration = duration;
        remaining_duration = duration;
        waiting_time = 0;
    }
}