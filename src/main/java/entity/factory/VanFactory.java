package entity.factory;

import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class VanFactory {
    private static final int DEFAULT_COUNT_OF_BOX = 0;

    public static Van createVan() {
        int id = IdGenerator.generateId();
        return new Van(id, DEFAULT_COUNT_OF_BOX);
    }

    public static Van createVan(Integer countOfBox) {
        int id = IdGenerator.generateId();
        return new Van(id, countOfBox);
    }

    public static Van createVan(Van baseVan){
        return new Van(baseVan);
    }

    public static List<Van> createVan(List<Integer> countOfBoxList) {
        List<Van> resultVanList = new ArrayList<>();
        for (Integer countOfBox : countOfBoxList) {
            int id = IdGenerator.generateId();
            Van newVan = new Van(id, countOfBox);
            resultVanList.add(newVan);
        }
        return resultVanList;
    }

}
