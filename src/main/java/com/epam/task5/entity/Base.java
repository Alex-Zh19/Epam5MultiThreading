package com.epam.task5.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Base {
    private static Base instance;
    private static final AtomicBoolean mark = new AtomicBoolean(true);
    private final Deque<Terminal> terminalQueue = new ArrayDeque();
    private final int MAX_COUNT_OF_BOX = 400;
    private static final AtomicInteger countOfBox = new AtomicInteger(200);

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
            countOfBox.set(MAX_COUNT_OF_BOX);
        } else if (count <= 0) {
            countOfBox.set(0);
        } else {
            countOfBox.set(count);
        }

    }

    public int getCountOfBox() {
        return countOfBox.get();
    }

    public Terminal getTerminal() {
        return terminalQueue.getFirst();
    }

    public void addTerminal(Terminal terminal) {
        terminalQueue.addLast(terminal);
    }

    public Optional<Terminal> findReadyTerminal() {
        Optional<Terminal>terminal;
        for (Terminal currentTerminal : terminalQueue) {
            terminal=Optional.of(currentTerminal);
            if (!currentTerminal.isBusy.get()) {
                currentTerminal.isBusy.set(true);
                return terminal;
            }
        }
        terminal=Optional.empty();
        return terminal;
    }

}
