import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Request> requests = DiskPlanning.generateRequests(100);

        Map<String, Integer> results = new HashMap<>();

        results.put("FCFS", DiskPlanning.FCFS(new ArrayList<>(requests)));
        results.put("SSTF", DiskPlanning.SSTF(new ArrayList<>(requests)));
        results.put("SCAN", DiskPlanning.SCAN(new ArrayList<>(requests)));
        results.put("CSCAN", DiskPlanning.C_SCAN(new ArrayList<>(requests)));
        results.put("EDF", DiskPlanning.EDF(new ArrayList<>(requests)));
        results.put("FDSCAN", DiskPlanning.FD_SCAN(new ArrayList<>(requests)));


        System.out.println("Posortowane algorytmy w kolejności rosnącej liczby przesunięć głowicy: ");
        List<Map.Entry<String, Integer>> sortedResults = new ArrayList<>(results.entrySet());
        sortedResults.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, Integer> sortedResult : sortedResults) {
            System.out.println(sortedResult.getKey() + ": " + sortedResult.getValue());
        }
        System.out.println();


    }
}