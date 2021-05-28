package entity;


import java.util.concurrent.atomic.AtomicBoolean;

public class Terminal {
    private int terminalId;
    public final AtomicBoolean isBusy = new AtomicBoolean(false);

    public Terminal(int terminalId) {
        this.terminalId = terminalId;
    }



}
