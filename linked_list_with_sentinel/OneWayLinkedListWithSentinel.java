class OneWayLinkedListWithSentinel<E> implements IList<E> {

    private final Node<E> sentinel;
    private int size = 0;

    public OneWayLinkedListWithSentinel() {
        sentinel = new Node<>(null, null);
    }
    @Override
    public void add(E element) {
        Node<E> node = sentinel; // points exactly to sentinel --- o -> [0] -> [1] -> ...
        while(node.getNext()!=null){ // points to the last element for example [1] for two elements (afterwards)
            node = node.getNext();
        }
        Node<E> newNode = new Node<>(element, null);
        node.setNext(newNode);
        size +=1;
    }

    @Override
    public void remove(int index) {
        if (index>size-1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = sentinel; // points exactly to sentinel --- o -> [0] -> [1] -> [2] ...
        for (int i = 0; i < index; i++) { // for two elements: previous -> points to [0] afterwards
            node = node.getNext();
        }
        Node<E> nodeToRemove = node.getNext(); // points now to [1] so that is the node we wanted to remove
        node.setNext(nodeToRemove.getNext()); // [0] -> [2] ...
        size-=1; // no i zmniejszamy listę
    }

    @Override
    public E get(int index) {
        if (index>size-1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = sentinel;
        for (int i = 0; i <= index; i++) { // dla index=1 o -> [0] -> [1] points to [1] afterwards
            node = node.getNext();
        }
        return node.getElement();
    }

    @Override
    public int size() {
        return size;
    }
}
