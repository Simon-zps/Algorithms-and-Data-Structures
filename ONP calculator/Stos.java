import java.util.ArrayList;

class Stos<T> {
    private ArrayList<T> items = new ArrayList<>();

    public void push(T item) {
        items.add(item);
    }

    public T pop() {
        if (!isEmpty()) {
            return items.remove(items.size() - 1);
        } else {
            return null;
        }
    }


    public boolean isEmpty() {
        return items.isEmpty();
    }

    public T peek() {
        return items.get(items.size() - 1);
    }

    public int size() {
        return items.size();
    }
}
