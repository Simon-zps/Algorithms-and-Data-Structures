public class Main {
    public static void main(String[] args) {

        IList<String> listEmpty = new OneWayLinkedListWithSentinel<>();

        IList<String> listOneElement = new OneWayLinkedListWithSentinel<>();
        listOneElement.add("a");

        IList<String> listEven = new OneWayLinkedListWithSentinel<>();
        listEven.add("a");
        listEven.add("b");
        listEven.add("c");
        listEven.add("d");

        IList<String> listUnEven = new OneWayLinkedListWithSentinel<>();
        listUnEven.add("a");
        listUnEven.add("b");
        listUnEven.add("c");


        System.out.println("Lista pusta:" + listEmpty.size());
        System.out.println();

        System.out.println("Lista z jednym elementem:" + listOneElement.size());
        System.out.println("Element 0 listy: "+listEven.get(0));

        System.out.println();

        System.out.println("Lista o parzystej liczbie elementów:" + listEven.size());
        for(int i=0; i<listEven.size(); i++){
            System.out.println("Element "+i+" listy: "+listEven.get(i));
        }
        System.out.println();

        System.out.println("Lista o nieparzystej liczbie elementów:" + listUnEven.size());
        for(int i=0; i<listUnEven.size(); i++){
            System.out.println("Element "+i+" listy: "+listUnEven.get(i));
        }

        System.out.println("Usuwam element z listy o nieparzystej liczbie elementów");
        listUnEven.remove(1);
        for(int i=0; i<listUnEven.size(); i++){
            System.out.println("Element "+i+" listy: "+listUnEven.get(i));
        }
    }
}

