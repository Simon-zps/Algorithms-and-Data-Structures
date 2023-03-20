import java.util.Iterator;

public class Series<E> implements Iterable<E> {
    private final SeriesGenerator<E> generator;
    private final int size;


    public Series(SeriesGenerator<E> generator, int size) {
        this.generator = generator;
        this.size = size;
    }

    @Override
    public Iterator<E> iterator() {
        return new SeriesIterator<>(generator, size);
    }
}

