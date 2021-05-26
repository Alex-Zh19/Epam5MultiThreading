package entity.factory;

import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class VanFactory {
    private static final int DEFAULT_COUNT_OF_BOX = 0;

    public static Van createVan() {
        int id = IdGenerator.generateId();
        return new Van(id, DEFAULT_COUNT_OF_BOX, false);
    }

    public static Van createVan(Integer countOfBox, Boolean isPerishable) {
        int id = IdGenerator.generateId();
        return new Van(id, countOfBox, isPerishable);
    }

    public static Van createVan(Van baseVan){
        return new Van(baseVan);
    }

    public static List<Van> createVan(List<Integer> countOfBoxList) {
        List<Van> resultVanList = new ArrayList<>();
        for (Integer countOfBox : countOfBoxList) {
            int id = IdGenerator.generateId();
            Van newVan;
            if (id % 2 == 0) {
                newVan = new Van(id, countOfBox, true);
            } else {
                newVan = new Van(id, countOfBox, false);
            }
            resultVanList.add(newVan);
        }
        return resultVanList;
    }

}
