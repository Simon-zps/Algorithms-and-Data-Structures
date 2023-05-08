import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import java.util.*;

public class PageReplacementSimulator {
    private int[] referenceString;
    private Memory memory;
    private int numFrames;
    private Random random;

    public PageReplacementSimulator(int numFrames, int referenceStringLength, int[] referenceString) {
        this.numFrames = numFrames;
        this.memory = new Memory(numFrames);
        this.referenceString = referenceString;
        this.random = new Random();
    }

    public int simulateFIFO() {
        Queue<Page> fifoQueue = new LinkedList<Page>();
        int pageFaults = 0;

        for (int i = 0; i < this.referenceString.length; i++) {
            int pageNumber = this.referenceString[i];
            Page page = new Page(pageNumber);

            if (containsPage(page)) {
                // Page hit - no action required
            } else {
                // Page fault
                pageFaults++;
                if (fifoQueue.size() < numFrames) {
                    fifoQueue.add(page);
                    addToMemory(page);
                } else {
                    Page pageToRemove = fifoQueue.poll();
                    removeFromMemory(pageToRemove);
                    fifoQueue.add(page);
                    addToMemory(page);
                }
            }
        }

        return pageFaults;
    }

    public int simulateOPT() {
        int pageFaults = 0;

        for (int i = 0; i < this.referenceString.length; i++) {
            int pageNumber = this.referenceString[i];
            Page page = new Page(pageNumber);

            if (containsPage(page)) {
                // Page hit - no action required
            } else {
                // Page fault
                pageFaults++;
                if (!isMemoryFull()) {
                    addToMemory(page);
                } else {
                    int farthestIndex = findFarthestFuturePageIndex(i);
                    removeFromMemory(memory.getPages().get(farthestIndex));
                    addToMemory(page);
                }
            }
        }

        return pageFaults;
    }


    public int simulateLRU() {
        Map<Page, Integer> lastUsedMap = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < this.referenceString.length; i++) {
            int pageNumber = this.referenceString[i];
            Page page = new Page(pageNumber);

            if (containsPage(page)) {
                // Page hit - update last used
                lastUsedMap.put(page, i);
            } else {
                // Page fault
                pageFaults++;
                if (!isMemoryFull()) {
                    addToMemory(page);
                } else {
                    Page leastRecentlyUsedPage = findLeastRecentlyUsedPage(lastUsedMap);
                    removeFromMemory(leastRecentlyUsedPage);
                    addToMemory(page);
                }
                lastUsedMap.put(page, i);
            }
        }

        return pageFaults;
    }

    public int simulateApproximatedLRU() {
        // In this implementation, we will use the Clock algorithm as an approximation of LRU
        int pageFaults = 0;
        int clockHand = 0;
        BitSet usedBits = new BitSet(numFrames);

        for (int i = 0; i < this.referenceString.length; i++) {
            int pageNumber = this.referenceString[i];
            Page page = new Page(pageNumber);

            if (containsPage(page)) {
                // Page hit - set used bit
                int pageIndex = indexOfPage(page);
                usedBits.set(pageIndex);
            } else {
                // Page fault
                pageFaults++;

                while (usedBits.get(clockHand)) {
                    usedBits.clear(clockHand);
                    clockHand = (clockHand + 1) % numFrames;
                }

                removeFromMemory(memory.getPages().get(clockHand));
                addToMemory(page, clockHand);
                usedBits.set(clockHand);

                clockHand = (clockHand + 1) % numFrames;
            }
        }

        return pageFaults;
    }


    public int simulateRAND() {
        int pageFaults = 0;

        for (int i = 0; i < this.referenceString.length; i++) {
            int pageNumber = this.referenceString[i];
            Page page = new Page(pageNumber);

            if (containsPage(page)) {
                // Page hit - no action required
            } else {
                // Page fault
                pageFaults++;
                if (!isMemoryFull()) {
                    addToMemory(page);
                } else {
                    int randomIndex = random.nextInt(numFrames);
                    removeFromMemory(memory.getPages().get(randomIndex));
                    addToMemory(page, randomIndex);
                }
            }
        }

        return pageFaults;
    }

    private boolean containsPage(Page page) {
        return memory.containsPage(page);
    }

    private boolean isMemoryFull() {
        return memory.isMemoryFull();
    }

    private void addToMemory(Page page) {
        memory.addToMemory(page);
    }

    private void removeFromMemory(Page page) {
        memory.removeFromMemory(page);
    }

    private int indexOfPage(Page page) {
        return memory.indexOfPage(page);
    }

    private void addToMemory(Page page, int index) {
        memory.getPages().set(index, page);
    }

    private int findFarthestFuturePageIndex(int currentIndex) {
        int farthestIndex = -1;
        int maxDistance = -1;
        for (int i = 0; i < memory.getSize(); i++) {
            Page page = memory.getPages().get(i);
            int distance = findNextPageIndex(page, currentIndex);
            if (distance == -1) {
                return i;
            } else if (distance > maxDistance) {
                maxDistance = distance;
                farthestIndex = i;
            }
        }
        return farthestIndex;
    }

    private int findNextPageIndex(Page page, int currentIndex) {
        for (int i = currentIndex + 1; i < referenceString.length; i++) {
            if (referenceString[i] == page.getNumber()) {
                return i;
            }
        }
        return -1;
    }

    private Page findLeastRecentlyUsedPage(Map<Page, Integer> lastUsedMap) {
        Page lruPage = null;
        int minLastUsed = Integer.MAX_VALUE;

        for (Map.Entry<Page, Integer> entry : lastUsedMap.entrySet()) {
            if (entry.getValue() < minLastUsed) {
                minLastUsed = entry.getValue();
                lruPage = entry.getKey();
            }
        }
        return lruPage;
    }

    public static void main(String[] args) {
        int[] frames = {3, 5, 10};
        int referenceStringLength = 1000;
        int[] referenceString = generateReferenceString(referenceStringLength, 50);

        for (int numFrames:frames) {
            PageReplacementSimulator simulator = new PageReplacementSimulator(numFrames, referenceStringLength, referenceString);
            System.out.println("Simulating with " + numFrames + " frames and reference string length of " + referenceStringLength);

            int fifoFaults = simulator.simulateFIFO();
            System.out.println("FIFO page faults: " + fifoFaults);

            int optFaults = simulator.simulateOPT();
            System.out.println("OPT page faults: " + optFaults);

            int lruFaults = simulator.simulateLRU();
            System.out.println("LRU page faults: " + lruFaults);

            int approxLruFaults = simulator.simulateApproximatedLRU();
            System.out.println("Approximated LRU page faults: " + approxLruFaults);

            int randFaults = simulator.simulateRAND();
            System.out.println("RAND page faults: " + randFaults);
        }
    }

    private static int[] generateReferenceString(int length, int numPages) {
        Random rand = new Random();
        int[] referenceString = new int[length];
        for (int i = 0; i < length; i++) {
            int page = rand.nextInt(numPages);
            referenceString[i] = page;
        }
        return referenceString;
    }
}

