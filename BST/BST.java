class BST {
    Node root;

    BST() {
        root = null;
    }

    public void insert(Student student) {
        Node node = new Node(student);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (student.id < current.student.id) {
                current = current.left;
                if (current == null) {
                    parent.left = node;
                    return;
                }
            } else if (student.id > current.student.id) {
                current = current.right;
                if (current == null) {
                    parent.right = node;
                    return;
                }
            } else {
                // ID już istnieje w drzewie
                System.out.println("ID " + student.id + " już istnieje. Nie można dodać drugiego studenta o tym samym ID.");
                return;
            }
        }
    }

    Node insertRec(Node root, Student student) {
        if (root == null) {
            root = new Node(student);
            return root;
        }
        if (student.id < root.student.id)
            root.left = insertRec(root.left, student);
        else if (student.id > root.student.id)
            root.right = insertRec(root.right, student);
        return root;
    }

    boolean deleteKey(Student student) {
        int initialSize = getSize(root);
        root = deleteRec(root, student);
        int finalSize = getSize(root);

        return finalSize < initialSize;
    }

    Node deleteRec(Node root, Student student) {
        if (root == null)  return root;
        if (student.id < root.student.id)
            root.left = deleteRec(root.left, student);
        else if (student.id > root.student.id)
            root.right = deleteRec(root.right, student);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.student = minValue(root.right);
            root.right = deleteRec(root.right, root.student);
        }
        return root;
    }

    Student minValue(Node root) {
        Student minv = root.student;
        while (root.left != null) {
            minv = root.left.student;
            root = root.left;
        }
        return minv;
    }

    Student search(Student student) {
        return searchRec(root, student);
    }

    Student searchRec(Node root, Student student) {
        if (root == null)
            return null;
        if (root.student.id == student.id)
            return root.student;
        if (root.student.id > student.id)
            return searchRec(root.left, student);
        return searchRec(root.right, student);
    }

    int getSize(Node node) {
        if (node == null)
            return 0;
        else
            return(getSize(node.left) + 1 + getSize(node.right));
    }

    public void printTree(Node node) {
        if (node != null) {
            printTree(node.left);
            System.out.println("Student ID: " + node.student.id +", name: " + node.student.name);
            printTree(node.right);
        }
    }
}
