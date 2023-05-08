import java.util.ArrayList;
public class Memory {
    private final int size;
    private final ArrayList<Page> pages;

    public Memory(int size) {
        this.size = size;
        this.pages = new ArrayList<Page>(size);
        for (int i = 0; i < size; i++) {
            this.pages.add(null);
        }
    }

    public int getSize() {
        return this.size;
    }

    public ArrayList<Page> getPages() {
        return this.pages;
    }

    public boolean containsPage(Page page) {
        return this.pages.contains(page);
    }

    public boolean isMemoryFull() {
        for (Page page : this.pages) {
            if (page == null) {
                return false;
            }
        }
        return true;
    }

    public void addToMemory(Page page) {
        int index = this.pages.indexOf(null);
        if (index != -1) {
            this.pages.set(index, page);
        }
    }

    public void removeFromMemory(Page page) {
        int index = this.pages.indexOf(page);
        if (index != -1) {
            this.pages.set(index, null);
        }
    }

    public int indexOfPage(Page page) {
        return this.pages.indexOf(page);
    }
}





