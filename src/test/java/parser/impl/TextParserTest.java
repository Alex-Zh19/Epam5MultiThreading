package parser.impl;

import entity.factory.Van;
import entity.factory.VanFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TextParserTest {

    @Test
    public void testParseText() {
        Integer in1 = 12;
        Integer in2 = 14;
        Integer in3 = 0;
        Integer in4 = 2;
        Van van1 = VanFactory.createVan(0,in1, true);
        Van van2 = VanFactory.createVan(1,in2, false);
        Van van3 = VanFactory.createVan(2,in3, true);
        Van van4 = VanFactory.createVan(3,in4, false);

        List<Van> expectedList = new ArrayList<>();
        expectedList.add(van1);
        expectedList.add(van2);
        expectedList.add(van3);
        expectedList.add(van4);

        List<Integer> integers = new ArrayList<>();
        integers.add(in1);
        integers.add(in2);
        integers.add(in3);
        integers.add(in4);

        List<Van> actualList = VanFactory.createVan(integers);
        assertEquals(expectedList, actualList);

    }
}