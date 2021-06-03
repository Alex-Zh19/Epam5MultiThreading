package com.epam.task5.entity.comparator;

import com.epam.task5.entity.factory.Van;

import java.util.Comparator;

public class PerishableComparator implements Comparator<Van> {
        @Override
        public int compare(Van o1, Van o2) {
            int result = 0;
            if (o1.getIsPerishable() && !o2.getIsPerishable()) {
                result = -1;
            } else if (!o1.getIsPerishable() && o2.getIsPerishable()) {
                result = 1;
            }
            return result;
        }

}
