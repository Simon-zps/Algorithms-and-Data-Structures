public class Main {
    public static void main(String[] args) {
        Computer computerFCFS = new Computer(0);
        Computer computerSJF = new Computer(1);
        Computer computerSRTF = new Computer(2);
        Computer computerRR = new Computer(3);

        computerFCFS.doTestProcessorType();
        computerSJF.doTestProcessorType();
        computerSRTF.doTestProcessorType();
        computerRR.doTestProcessorType();
    }
}