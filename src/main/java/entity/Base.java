package entity;

import entity.factory.Van;
import entity.factory.VanFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Base {
    private static Base instance = new Base();
    private Deque<Van> vanDeque = new ArrayDeque<>();

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

}
