package entity;

import entity.factory.Van;
import entity.factory.VanFactory;
import exception.MultiThreadingException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class VanQueue {
    private static VanQueue vanQueue;
    private final Deque<Van> vanDeque = new ArrayDeque<>();
    private static final AtomicBoolean mark = new AtomicBoolean(true);

    private VanQueue(){
    }

    public static VanQueue getInstance() {
        if (vanQueue == null) {
            if (mark.compareAndSet(true, false)) {
                vanQueue = new VanQueue();
            }
        }
        return vanQueue;
    }

    public void add(Van van) {
        vanDeque.addLast(van);
    }

    public void addAll(List<Van> vanList) {
        vanDeque.addAll(vanList);
    }

    public Van getFirst() {
        Van van = vanDeque.getFirst();
        Van vanToSaveOriginalVan = VanFactory.createVan(van);
        return vanToSaveOriginalVan;
    }

    public Van getLast() {
        Van van = vanDeque.getLast();
        Van vanToSaveOriginalVan = VanFactory.createVan(van);
        return vanToSaveOriginalVan;
    }

    public int countOfVanInQueue() {
        return vanDeque.size();
    }

    public void startVanUploading() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(vanDeque.size());
        ExecutorService findReadyTerminalService = Executors.newSingleThreadScheduledExecutor();

        for (Van van : vanDeque) {
            Terminal terminal = findReadyTerminalService.submit(findReadyTerminal).get();
            if(terminal!=null) {
                executorService.submit(van);
                terminal.isBusy.set(false);
            }
        }
        executorService.shutdown();
        findReadyTerminalService.shutdown();
    }

    Callable<Terminal> findReadyTerminal = new Callable<Terminal>() {
        @Override
        public Terminal call() throws MultiThreadingException {
            Base base = Base.getInstance();
            Terminal terminal = base.findReadyTerminal();
            return terminal;
        }
    };
}
