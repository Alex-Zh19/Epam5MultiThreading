package reader.impl;

import exception.MultiThreadingException;
import org.testng.annotations.Test;
import reader.CustomReader;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TextReaderTest {
    final static String PATH_TO_FILE = "dataTest/dataTest.txt";
    @Test
    public void testRead() {
        CustomReader reader = new TextReader();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL pathToFileUrl = classLoader.getResource(PATH_TO_FILE);

        List<String> stringFormFileExpected = new ArrayList<>();
        String str1 = "1.{shipment:[{Orange,Perishable},{Apple,Perishable},{Pineapple,Perishable},{watermelon,Perishable},{Melon,Perishable}]}";
        String str2 = "2.{shipment:[{Milk,Perishable},{Kefir,Perishable},{Sour Cream,Perishable}]}";
        String str3 = "3.{shipment:[{Chair,Not perishable},{Bed,Not Perishable},{curtain,not Perishable},{table,not Perishable},{monitor,not Perishable},{shelf,not Perishable}]}";
        String str4 = "4.{shipment:[]}";
        String str5 = "5.{shipment:[{Bed,not Perishable},{Chair,not Perishable},{Orange,Perishable},{sink,not Perishable}]}";

        stringFormFileExpected.add(str1);
        stringFormFileExpected.add(str2);
        stringFormFileExpected.add(str3);
        stringFormFileExpected.add(str4);
        stringFormFileExpected.add(str5);

        try {
            List<String> stringFromFileActual =
                    reader.read(new File(pathToFileUrl.getFile()).getAbsolutePath());
            assertEquals(stringFromFileActual, stringFormFileExpected);
        } catch (MultiThreadingException e) {

        }
    }
}