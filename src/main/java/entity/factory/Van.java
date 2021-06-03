package entity.factory;


import entity.Base;
import entity.VanState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Van implements Callable {
    private static final Logger logger = LogManager.getLogger();
    private long id;
    private int countOfBox;
    private boolean isPerishable;
    private VanState state;

    Van() {
    }

    Van(long id, int count, boolean isPerishable, VanState state) {
        this.id = id;
        countOfBox = count;
        this.isPerishable = isPerishable;
        this.state = state;
    }


    Van(Van baseVan) {
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
    public Van clone() {
        Van newVan = null;
        try {
            newVan = (Van) super.clone();
        } catch (CloneNotSupportedException exception) {
            logger.log(Level.ERROR, "Clone is not supported", exception);
        }
        return newVan;
    }

    @Override
    public Boolean call() throws InterruptedException {
        logger.log(Level.INFO, "thread started: {}", this);
        Base base = Base.getInstance();
        switch (state) {
            case LOAD -> {
                base.setCountOfBox(base.getCountOfBox() - countOfBox);
                this.countOfBox += countOfBox;
            }
            case UNLOAD -> {
                base.setCountOfBox(base.getCountOfBox() + countOfBox);
                this.countOfBox -= countOfBox;
            }
        }

        TimeUnit.SECONDS.sleep(2);
        logger.log(Level.INFO, "thread sleep: {}", this);
        return true;
    }

}
