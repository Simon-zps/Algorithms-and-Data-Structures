import java.util.Iterator;

public class SeriesIterator<E> implements Iterator<E> {
    private int currentIndex;
    private final int endIndex;
    private final SeriesGenerator<E> generator;

    public SeriesIterator(SeriesGenerator<E> generator, int endIndex) {
        this.generator = generator;
        this.currentIndex = 1;
        this.endIndex = endIndex;
    }

    @Override
    public boolean hasNext() {
        return currentIndex <= endIndex;
    }

    @Override
    public E next() {
        E element = generator.generate(currentIndex);
        currentIndex++;
        return element;
    }
}

