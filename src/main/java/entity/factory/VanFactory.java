package entity.factory;

import entity.VanState;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class VanFactory {
    private static final int DEFAULT_COUNT_OF_BOX = 0;

    public static Van createVan() {
        int id = IdGenerator.generateId();
        VanState state = generateState();
        return new Van(id, DEFAULT_COUNT_OF_BOX, false, state);
    }

    public static Van createVan(Integer countOfBox, Boolean isPerishable, VanState state) {
        int id = IdGenerator.generateId();
        return new Van(id, countOfBox, isPerishable, state);
    }

    public static Van createVan(int id, Integer countOfBox, Boolean isPerishable, VanState state) {
        return new Van(id, countOfBox, isPerishable, state);
    }

    public static Van createVan(Van baseVan) {
        return new Van(baseVan);
    }

    public static List<Van> createVan(List<Integer> countOfBoxList) {
        List<Van> resultVanList = new ArrayList<>();
        for (Integer countOfBox : countOfBoxList) {
            int id = IdGenerator.generateId();
            Van newVan;
            if (id % 2 == 0) {
                newVan = new Van(id, countOfBox, true,generateState());
            } else {
                newVan = new Van(id, countOfBox, false,generateState());
            }
            resultVanList.add(newVan);
        }
        return resultVanList;
    }

    private static VanState generateState() {
        VanState state;
        int stateInt = (int) (Math.round(Math.random() * 2));
        switch (stateInt) {
            case 1:
                state = VanState.LOAD;
                break;
            case 2:
                state = VanState.PASSING;
                break;
            default:
                state = VanState.UNLOAD;
        }
        return state;
    }

}
