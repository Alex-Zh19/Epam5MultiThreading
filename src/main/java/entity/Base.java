package entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicBoolean;

public class Base {
    private static Base instance;
    private static final AtomicBoolean mark = new AtomicBoolean(true);
    private final Deque<Terminal> terminalQueue = new ArrayDeque();
    private final int MAX_COUNT_OF_BOX = 400;
    private static int countOfBox = 0;

    private Base() {
    }

    public static Base getInstance() {
        if (instance == null) {
            if (mark.compareAndSet(true, false)) {
                instance = new Base();
            }
        }
        return instance;
    }

    public void setCountOfBox(int count) {
        if (count >= MAX_COUNT_OF_BOX) {
            countOfBox = MAX_COUNT_OF_BOX;
        } else if (count <= 0) {
            countOfBox = 0;
        } else {
            countOfBox = count;
        }

    }

    public int getCountOfBox() {
        return countOfBox;
    }

    public Terminal getTerminal() {
        return terminalQueue.getFirst();
    }

    public void addTerminal(Terminal terminal) {
        terminalQueue.addLast(terminal);
    }

    public Terminal findReadyTerminal() {

        for (Terminal terminal : terminalQueue) {
            if (terminal.isBusy.get()==false) {
                terminal.isBusy.set(true);
                return terminal;
            }
        }
        return null;
    }

}
