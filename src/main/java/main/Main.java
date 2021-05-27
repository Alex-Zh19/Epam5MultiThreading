package main;

import entity.Base;
import entity.factory.VanFactory;
import exception.MultiThreadingException;
import parser.CustomParser;
import parser.impl.TextParser;
import reader.CustomReader;
import reader.impl.TextReader;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {
    final static String PATH_TO_FILE = "data/data.txt";

    public static void main(String[] args) throws MultiThreadingException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL pathToFileUrl = classLoader.getResource(PATH_TO_FILE);

        CustomReader reader = new TextReader();
        List<String> stringFromFile =
                reader.read(new File(pathToFileUrl.getFile()).getAbsolutePath());
        CustomParser parser=new TextParser();
        List<Integer>integers=parser.parseText(stringFromFile);
        Base base=Base.getInstance();
        base.addAll(VanFactory.createVan(integers));
        base.start();
    }
}
