public class Computing {


    private CPU FCFS = new CPU(0);
    private CPU SJF = new CPU(1);
    private CPU SRTF = new CPU(2);
    private CPU RR = new CPU(3);

    private int timer = 0;
    private int timeQuantum = 50;

    private Process testProcess1 = new Process(450);
    private Process testProcess2 = new Process(1250);
    private Process testProcess3 = new Process(600);
    private Process testProcess4 = new Process(250);
    private Process testProcess5 = new Process(750);

    private SchedulingType type;

    public Computing(SchedulingType type) {
        this.type = type;
    }

    public void testCPU(){
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
        while(doContinue){
            if(timer == 0) {
                assert test != null;
                test.addProcess(testProcess1);
            }
            else if (timer == 100) {
                assert test != null;
                test.addProcess(testProcess2);
            }
            else if(timer == 200) {
                assert test != null;
                test.addProcess(testProcess3);
            }
            else if(timer == 300) {
                assert test != null;
                test.addProcess(testProcess4);
            }
            else if(timer == 400) {
                assert test != null;
                test.addProcess(testProcess5);
            }

            assert test != null;
            test.doRunProcess(timeQuantum);
            timer += timeQuantum;
            if(testProcess5.remaining_time <= 0)
                doContinue = false;
        }
        int totalWaitTime = testProcess1.waiting_time + testProcess2.waiting_time + testProcess3.waiting_time + testProcess4.waiting_time + testProcess5.waiting_time;
        double AVGwaitTime = totalWaitTime/5.0;
        int totalDuration = testProcess1.total_time + testProcess2.total_time + testProcess3.total_time + testProcess4.total_time + testProcess5.total_time;
        int totalWorkTime = totalWaitTime + totalDuration;
        double AVGworkTime = totalWorkTime/5.0;

        System.out.println("Avg Waiting Time: " + AVGwaitTime + "ms");
        System.out.println("Avg Working Time: " + AVGworkTime + "ms");
        System.out.println("Process 1 effectivness: " + testProcess1.total_time *1.0/(testProcess1.total_time + testProcess1.waiting_time));
        System.out.println("Process 2 effectivness: " + testProcess2.total_time *1.0/(testProcess2.total_time + testProcess2.waiting_time));
        System.out.println("Process 3 effectivness: " + testProcess3.total_time *1.0/(testProcess3.total_time + testProcess3.waiting_time));
        System.out.println("Process 4 effectivness: " + testProcess4.total_time *1.0/(testProcess4.total_time + testProcess4.waiting_time));
        System.out.println("Process 5 effectivness: " + testProcess5.total_time *1.0/(testProcess5.total_time + testProcess5.waiting_time));
        System.out.println();
    }
}