package parser.impl;

import parser.CustomParser;

import java.util.ArrayList;
import java.util.List;

public class TextParser implements CustomParser {
    private static final String REG_TO_SPLIT_STR = ":";

    @Override
    public List<Integer> parseText(List<String> validStrings) {
        List<Integer> resultList = new ArrayList<>();
        for (String string : validStrings) {
            String[] strings = string.split(REG_TO_SPLIT_STR);
            resultList.add(Integer.parseInt(strings[1]));
        }
        return resultList;
    }
}
