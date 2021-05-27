package entity;

import entity.factory.Van;

import java.util.concurrent.atomic.AtomicBoolean;

public class Terminal {
    private int terminalId;
    public static final AtomicBoolean isBusy = new AtomicBoolean(false);

    public Terminal(int terminalId) {
        this.terminalId = terminalId;
    }



}
