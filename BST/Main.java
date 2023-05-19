import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BST bst = new BST();

        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < 3; i++){
            System.out.println("Podaj ID studenta:");
            int id = scanner.nextInt();
            scanner.nextLine();  // Zgarnij znak nowej linii
            System.out.println("Podaj imię studenta:");
            String name = scanner.nextLine();
            bst.insert(new Student(id, name));
        }

        System.out.println("Podaj ID studenta do wyszukania:");
        int idDoWyszukania = scanner.nextInt();
        Student znalezionyStudent = bst.search(new Student(idDoWyszukania, ""));
        if (znalezionyStudent != null) {
            System.out.println("Znaleziono studenta: " + znalezionyStudent.name);
        } else {
            System.out.println("Nie znaleziono studenta.");
        }

        bst.printTree(bst.root);

        System.out.println("Podaj ID studenta do usunięcia:");
        int idDoUsuniecia = scanner.nextInt();
        boolean usunieto = bst.deleteKey(new Student(idDoUsuniecia, ""));
        if (usunieto) {
            System.out.println("Usunięto studenta o ID: " + idDoUsuniecia);
        } else {
            System.out.println("Nie znaleziono studenta o ID: " + idDoUsuniecia);
        }

        // Można ponownie przeszukać drzewo, aby sprawdzić, czy student został usunięty
        znalezionyStudent = bst.search(new Student(idDoUsuniecia, ""));
        if (znalezionyStudent != null) {
            System.out.println("Znaleziono studenta: " + znalezionyStudent.name);
        } else {
            System.out.println("Nie znaleziono studenta o id: " + idDoUsuniecia);
        }

        scanner.close();
        bst.printTree(bst.root);
    }
}


