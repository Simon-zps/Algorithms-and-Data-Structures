import java.util.ArrayList;
import java.util.List;

public class Computing {


    private CPU FCFS = new CPU(SchedulingType.FCFS);
    private CPU SJF = new CPU(SchedulingType.SJF);
    private CPU SRTF = new CPU(SchedulingType.SRTF);
    private CPU RR = new CPU(SchedulingType.RR);

    private int timer = 0;
    private int quantTime = 50;

    private Process testProcess1 = new Process(450);
    private Process testProcess2 = new Process(1250);
    private Process testProcess3 = new Process(600);
    private Process testProcess4 = new Process(250);
    private Process testProcess5 = new Process(750);
    private Process testProcess6 = new Process(1000);
    private Process testProcess7 = new Process(800);
    private Process testProcess8 = new Process(400);
    private Process testProcess9 = new Process(1200);
    private Process testProcess10 = new Process(300);

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
                /*In the given code, assert test != null; is used to
                check whether the test variable has been initialized or not.
                If test is null, this line of code will cause an AssertionError,
                which will stop the program's execution immediately.
                This can be helpful in catching potential bugs early in the development process.*/
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
            else if(timer == 500) {
                assert test != null;
                test.addProcess(testProcess6);
            }
            else if(timer == 600) {
                assert test != null;
                test.addProcess(testProcess7);
            }
            else if(timer == 700) {
                assert test != null;
                test.addProcess(testProcess8);
            }
            else if(timer == 800) {
                assert test != null;
                test.addProcess(testProcess9);
            }
            else if(timer == 900) {
                assert test != null;
                test.addProcess(testProcess10);
            }

            assert test != null;
            test.runProcess(quantTime);
            timer += quantTime;
            if(testProcess5.remaining_time <= 0)
                doContinue = false;
        }

        int totalWaitTime = testProcess1.waiting_time + testProcess2.waiting_time + testProcess3.waiting_time + testProcess4.waiting_time + testProcess5.waiting_time;
        double AVGwaitTime = totalWaitTime/5.0;
        int totalDuration = testProcess1.total_time + testProcess2.total_time + testProcess3.total_time + testProcess4.total_time + testProcess5.total_time;
        int totalWorkTime = totalWaitTime + totalDuration;
        double AVGworkTime = totalWorkTime/5.0; //tf to automate


        System.out.println("Avg Waiting Time: " + AVGwaitTime + "ms");
        System.out.println("Avg Working Time: " + AVGworkTime + "ms");


        System.out.println("Process 1 effectivness: " + testProcess1.total_time *1.0/(testProcess1.total_time + testProcess1.waiting_time));
        System.out.println("Process 2 effectivness: " + testProcess2.total_time *1.0/(testProcess2.total_time + testProcess2.waiting_time));
        System.out.println("Process 3 effectivness: " + testProcess3.total_time *1.0/(testProcess3.total_time + testProcess3.waiting_time));
        System.out.println("Process 4 effectivness: " + testProcess4.total_time *1.0/(testProcess4.total_time + testProcess4.waiting_time));
        System.out.println("Process 5 effectivness: " + testProcess5.total_time *1.0/(testProcess5.total_time + testProcess5.waiting_time));
        System.out.println("Process 6 effectivness: " + testProcess6.total_time *1.0/(testProcess6.total_time + testProcess6.waiting_time));
        System.out.println("Process 7 effectivness: " + testProcess7.total_time *1.0/(testProcess7.total_time + testProcess7.waiting_time));
        System.out.println("Process 8 effectivness: " + testProcess8.total_time *1.0/(testProcess8.total_time + testProcess8.waiting_time));
        System.out.println("Process 9 effectivness: " + testProcess9.total_time *1.0/(testProcess9.total_time + testProcess9.waiting_time));
        System.out.println("Process 10 effectivness: " + testProcess10.total_time *1.0/(testProcess10.total_time + testProcess10.waiting_time));
        System.out.println();
    }
}