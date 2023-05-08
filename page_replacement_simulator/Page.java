import java.util.Objects;

public class Page {
    private final int number;

    public Page(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return number == page.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
