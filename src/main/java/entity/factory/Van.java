package entity.factory;


import entity.Base;
import entity.VanState;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Van implements Callable {
    private long id;
    private int countOfBox;
    private boolean isPerishable;
    private VanState state;

    protected Van() {
    }

    protected Van(long id, int count, boolean isPerishable, VanState state) {
        this.id = id;
        countOfBox = count;
        this.isPerishable = isPerishable;
        this.state = state;
    }


    protected Van(Van baseVan) {
        this.id = baseVan.id;
        this.countOfBox = baseVan.countOfBox;
        this.isPerishable = baseVan.isPerishable;
        this.state = baseVan.state;
    }

    public void add() {
        countOfBox++;
    }

    public void addAll(int count) {
        countOfBox += count;
    }

    public int getCountOfBox() {
        return countOfBox;
    }

    public void setCountOfBox(int count) {
        this.countOfBox = count;
    }

    public Boolean getIsPerishable() {
        return isPerishable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Van van = (Van) o;
        return van.id == id && van.countOfBox == countOfBox;
    }

    @Override
    public int hashCode() {
        int result = 9;
        int buffer = 13;
        result += id * buffer + countOfBox;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Van{id=");
        result.append(id);
        result.append(", countOfBox=");
        result.append(countOfBox);
        result.append(", perishable=");
        result.append(isPerishable);
        result.append(", state=");
        result.append(state);
        result.append("}");
        return result.toString();
    }

    @Override
    public Boolean call() throws InterruptedException {
        System.out.println("thread started: " + this);
        Base base = Base.getInstance();
        switch (state) {
            case LOAD -> base.setCountOfBox(base.getCountOfBox() - countOfBox);
            case UNLOAD -> base.setCountOfBox(base.getCountOfBox() + countOfBox);
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println("thread sleep: " + this);
        return true;
    }

}
