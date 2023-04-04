import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class DiskPlanning {

    public static final int MAX = 1000; // Max block

    public static List<Request> generateRequests(int count) {
        Random random = new Random();
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int block = random.nextInt(MAX);
            int deadline = random.nextInt(100) + 1;
            requests.add(new Request(block, deadline));
        }
        return requests;
    }

    public static int FCFS(List<Request> requests) {
        /* Teh FCFS algorithm works on the principle of handling requests in the order of their arrival.
            The disk head moves from one block to another sequentially, without any optimization.
            */
        int head = 0;
        int totalHeadMovement = 0;

        for (Request request : requests) {
            // this loop itersates through the requests and adds the head movement to the total head movement and sets the current position of the head to the block of the request
            totalHeadMovement += Math.abs(request.getBlock() - head);
            head = request.getBlock();
        }
        return totalHeadMovement;
    }

    public static int SSTF(List<Request> requests) {
        /* The SSTF algorithm works on the principle of serving the closest request in relation to the current position of the disk head.
        Unlike FCFS, SSTF tries to optimize the path of the head, reducing the total number of disk head movements.*/
        int head = 0;
        int totalHeadMovement = 0;
        
        while (!requests.isEmpty()) {
            Request nearest = null;
            int minDistance = Integer.MAX_VALUE;
            for (Request request : requests) {
                int distance = Math.abs(request.getBlock() - head); // calculate the distance from the head to the block
                if (distance < minDistance) {
                    nearest = request;
                    minDistance = distance;
                }
            }
            totalHeadMovement += minDistance;
            assert nearest != null;
            head = nearest.getBlock();
            requests.remove(nearest);
        }
        return totalHeadMovement;
    }

    // Additional methods for SCAN and C-SCAN
    public static List<Request> filterAndSort(List<Request> requests, int head, boolean movingForward) {
        List<Request> filtered = new ArrayList<>();
        for (Request request : requests) {
            if ((movingForward && request.getBlock() >= head) || (!movingForward && request.getBlock() <= head)) {
                filtered.add(request);
            }
        }
        filtered.sort(Comparator.comparingInt(Request::getBlock));
        // Request::getBlock is the same as request -> request.getBlock() which means that we are sorting by the block of the request
        return filtered;
    }

    public static int SCAN(List<Request> requests) {
        /* ELEVATOR ALGORITHM
         * The SCAN algorithm works by moving the disk head in one direction and then in the other until all requests have been serviced.
         * */
        int head = 0;
        int totalHeadMovement = 0;
        boolean forward = true;

        while (!requests.isEmpty()) { // while there are still requests to be processed
            List<Request> currentDirectionSorted = filterAndSort(requests, head, forward);
            for (Request request : currentDirectionSorted) { // iterate through the requests in the current direction and add the head movement to the total head movement and set the head to the block of the request
                totalHeadMovement += Math.abs(request.getBlock() - head);
                head = request.getBlock();
                requests.remove(request);
            }
            if (forward) { // if we are moving forward we need to move the head to the end of the disk
                totalHeadMovement += Math.abs(MAX - head); // we add the distance from the current head position to the end of the disk
                head = MAX;
            } else { // if we are moving backwards we need to move the head to the beginning of the disk
                totalHeadMovement += head;
                head = 0;
            }
            forward = !forward;
        }
        return totalHeadMovement;
    }

    public static int C_SCAN(List<Request> requests) {
        /* The C-SCAN algorithm works by servicing all the requests in one direction of the disk head movement until it reaches the end of the disk.
        Then, instead of reversing the direction of the disk head, it moves the head to the other end of the disk and services all the requests in the same direction.
        * */
        int head = 0;
        int totalHeadMovement = 0;

        while (!requests.isEmpty()) {
            List<Request> currentDirectionSorted = filterAndSort(requests, head, true);
            for (Request request : currentDirectionSorted) {
                totalHeadMovement += Math.abs(request.getBlock() - head); // calculate the distance from the current head position to the block of the request
                head = request.getBlock();
                requests.remove(request);
            }
            totalHeadMovement += Math.abs(MAX - head) + MAX; // calculate the distance from the current head position
            // to the end of the disk and add the distance from the beginning of the disk to the end of the disk
            head = 0;
        }
        return totalHeadMovement;
    }

    public static int EDF(List<Request> requests) {
        // The EDF algorithm works on the principle of serving requests from those with the smallest deadline to those with the largest.
        int head = 0;
        int totalHeadMovement = 0;
        requests.sort(Comparator.comparingInt(Request::getDeadline)); // sort the requests by the deadline in ascending order

        for (Request request : requests) {
            totalHeadMovement += Math.abs(request.getBlock() - head); // calculates the distance from the current head position to the block of the request
            head = request.getBlock();
        }
        return totalHeadMovement;
    }

    public static int FD_SCAN(List<Request> requests) {
        int head = 0;
        int totalHeadMovement = 0;
        boolean forward = true;

        while (!requests.isEmpty()) {
            List<Request> currentDirection = filterAndSort(requests, head, forward);
            currentDirection.sort(Comparator.comparingInt(Request::getDeadline)); // the only difference between this and the SCAN algorithm is that we sort the requests by the deadline in ascending order

            for (Request request : currentDirection) {
                totalHeadMovement += Math.abs(request.getBlock() - head);
                head = request.getBlock();
                requests.remove(request);
            }
            if (forward) {
                totalHeadMovement += Math.abs(MAX - head);
                head = MAX;
            } else {
                totalHeadMovement += head;
                head = 0;
            }
            forward = !forward;
        }
        return totalHeadMovement;
    }

}
