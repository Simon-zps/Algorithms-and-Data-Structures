public class Computer {
    private int type;

    private Processor FCFS = new Processor(0);
    private Processor SJF = new Processor(1);
    private Processor SRTF = new Processor(2);
    private Processor RR = new Processor(3);

    private int timer = 0;
    private int timeQuantum = 50;

    private Process testProcess1 = new Process(450);
    private Process testProcess2 = new Process(1250);
    private Process testProcess3 = new Process(600);
    private Process testProcess4 = new Process(250);
    private Process testProcess5 = new Process(750);

    public Computer(int type){
        this.type = type;
    }

    public void doTestProcessorType(){
        Processor test = null;
        switch(type){
            case 0:{
                test = FCFS;
                System.out.println("The test done for FCFS:");
                break;
            }
            case 1:{
                test = SJF;
                System.out.println("The test done for SJF:");
                break;
            }
            case 2:{
                test = SRTF;
                System.out.println("The test done for SRTF:");
                break;
            }
            case 3:{
                test = RR;
                System.out.println("The test done for RR:");
                break;
            }
        }

        boolean doContinue = true;
        while(doContinue){
            if(timer == 0)
                test.addProcess(testProcess1);
            else if (timer == 100)
                test.addProcess(testProcess2);
            else if(timer == 200)
                test.addProcess(testProcess3);
            else if(timer == 300)
                test.addProcess(testProcess4);
            else if(timer == 400)
                test.addProcess(testProcess5);

            test.doRunProcess(timeQuantum);
            timer += timeQuantum;
            if(testProcess5.remaining_duration <= 0)
                doContinue = false;
        }
        int totalWaitTime = testProcess1.waiting_time + testProcess2.waiting_time + testProcess3.waiting_time + testProcess4.waiting_time + testProcess5.waiting_time;
        double AVGwaitTime = totalWaitTime/5.0;
        int totalDuration = testProcess1.total_duration + testProcess2.total_duration + testProcess3.total_duration + testProcess4.total_duration + testProcess5.total_duration;
        int totalWorkTime = totalWaitTime + totalDuration;
        double AVGworkTime = totalWorkTime/5.0;

        System.out.println("Average Waiting Time: " + AVGwaitTime + "ms");
        System.out.println("Average Working Time: " + AVGworkTime + "ms");
        System.out.println("Process 1 Effectivness: " + testProcess1.total_duration*1.0/(testProcess1.total_duration+testProcess1.waiting_time));
        System.out.println("Process 2 Effectivness: " + testProcess2.total_duration*1.0/(testProcess2.total_duration+testProcess2.waiting_time));
        System.out.println("Process 3 Effectivness: " + testProcess3.total_duration*1.0/(testProcess3.total_duration+testProcess3.waiting_time));
        System.out.println("Process 4 Effectivness: " + testProcess4.total_duration*1.0/(testProcess4.total_duration+testProcess4.waiting_time));
        System.out.println("Process 5 Effectivness: " + testProcess5.total_duration*1.0/(testProcess5.total_duration+testProcess5.waiting_time));
        System.out.println("");
    }
}