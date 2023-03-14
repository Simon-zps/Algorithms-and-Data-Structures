public class Main {

    public static void main(String[] args) {
        Computing computingFCFS = new Computing(SchedulingType.FCFS);
        Computing computingSJF = new Computing(SchedulingType.SJF);
        Computing computingSRTF = new Computing(SchedulingType.SRTF);
        Computing computingRR = new Computing(SchedulingType.RR);
        computingFCFS.testCPU();
        computingSJF.testCPU();
        computingSRTF.testCPU();
        computingRR.testCPU();
    }
}