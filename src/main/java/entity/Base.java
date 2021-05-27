package entity;

import entity.factory.Van;
import entity.factory.VanFactory;
import exception.MultiThreadingException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Base {
    private static final Base instance = new Base();
    private final Deque<Van> vanDeque = new ArrayDeque<>();
    private final int MAX_COUNT_OF_VAN = 3;
    private final int MAX_COUNT_OF_BOX = 40;
    private static int countOfBox=0;

    private Base() {
    }

    public static Base getInstance() {
        return instance;
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

    public int countOfVan(){
        return vanDeque.size();
    }

    public int getMAX_COUNT_OF_VAN(){
        return MAX_COUNT_OF_VAN;
    }

    public int getMAX_COUNT_OF_BOX(){
        return MAX_COUNT_OF_BOX;
    }

    public void setCountOfBox(int count) {
        countOfBox += count;
    }

    public int getCountOfBox() {
        return countOfBox;
    }

    public void start() throws MultiThreadingException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_COUNT_OF_VAN);
        for (Van van : vanDeque) {
            executorService.submit(van);
        }
        executorService.shutdown();
    }
}
