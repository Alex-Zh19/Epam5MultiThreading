package entity.factory;


import java.util.concurrent.Callable;

public class Van implements Callable {
    private long id;
    private int countOfBox;

    protected Van() {
    }

    protected Van(long id, int count) {
        this.id = id;
        countOfBox = count;
    }

    protected Van(Van baseVan) {
        this.id = baseVan.id;
        this.countOfBox = baseVan.countOfBox;
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
    public Object call() throws Exception {
        return null;
    }
}
