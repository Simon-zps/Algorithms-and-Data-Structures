public class Main {

    public static void main(String[] args) {
        Computing computingFCFS = new Computing(0);
        Computing computingSJF = new Computing(1);
        Computing computingSRTF = new Computing(2);
        Computing computingRR = new Computing(3);

        computingFCFS.testCPU();
        computingSJF.testCPU();
        computingSRTF.testCPU();
        computingRR.testCPU();
    }
}