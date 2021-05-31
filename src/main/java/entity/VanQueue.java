package entity;

import entity.comparator.PerishableComparator;
import entity.factory.Van;
import entity.factory.VanFactory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class VanQueue {
    private final Queue<Van> vanQueue = new PriorityQueue<>(new PerishableComparator());
    private final ReentrantLock lockToUploadVan = new ReentrantLock();
    private final ReentrantLock lockToFindReadyTerminal = new ReentrantLock();

    public void add(Van van) {
        vanQueue.add(van);
    }

    public void addAll(List<Van> vanList) {
        vanQueue.addAll(vanList);
    }

    public Van get() {
        Van van = vanQueue.poll();
        Van vanToSaveOriginalVan = VanFactory.createVan(van);
        return vanToSaveOriginalVan;
    }


    public int countOfVanInQueue() {
        return vanQueue.size();
    }

    public void startVanUploading() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        ExecutorService findReadyTerminalService = Executors.newSingleThreadScheduledExecutor();
        Queue<Van> copy = new ArrayDeque<>(vanQueue);
        while (vanQueue.size() != 0) {
            Van van = vanQueue.poll();
            Optional<Terminal> optionalTerminal = Optional.empty();

            lockToFindReadyTerminal.lock();
            while (optionalTerminal.isEmpty()) {
                optionalTerminal = findReadyTerminalService.submit(findReadyTerminal).get();
            }
            Terminal terminal = optionalTerminal.get();
            lockToFindReadyTerminal.unlock();

            lockToUploadVan.lock();
            executorService.submit(van);
            lockToUploadVan.unlock();
            terminal.isBusy.set(false);
        }
        executorService.shutdown();
        findReadyTerminalService.shutdown();
    }


    Callable<Optional<Terminal>> findReadyTerminal = () -> {
        Base base = Base.getInstance();
        Optional<Terminal> terminal = base.findReadyTerminal();
        return terminal;
    };

    @Override
    public String toString() {
        return vanQueue.toString();
    }
}
