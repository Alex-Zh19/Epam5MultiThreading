package entity.factory;


import entity.Base;

public class Van extends Thread {
    private long id;
    private int countOfBox;
    private boolean isPerishable;

    protected Van() {
    }

    protected Van(long id, int count, boolean isPerishable) {
        if (isPerishable) {
            this.setPriority(MAX_PRIORITY);
        } else {
            this.setPriority(NORM_PRIORITY);
        }
        this.id = id;
        countOfBox = count;
        this.isPerishable = isPerishable;
    }

    protected Van(Van baseVan) {
        this.id = baseVan.id;
        this.countOfBox = baseVan.countOfBox;
        this.isPerishable = baseVan.isPerishable;
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
        result.append("}");
        return result.toString();
    }


    @Override
    public void run() {
        System.out.println("thread begins "+this);
        Base base = Base.getInstance();
        base.setCountOfBox(base.getCountOfBox() + this.countOfBox);
        this.countOfBox = 0;

    }

}
