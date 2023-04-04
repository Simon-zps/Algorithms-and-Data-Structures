public class Request {
    private final int block;
    private final int deadline; // W przypadku zgłoszeń real-time, deadline jest czasem do którego zgłoszenie musi zostać obsłużone

    public Request(int block, int deadline) {
        this.block = block;
        this.deadline = deadline;
    }

    public int getBlock() {
        return block;
    }

    public int getDeadline() {
        return deadline;
    }
}