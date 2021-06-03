package com.epam.task5.main;

import com.epam.task5.entity.Base;
import com.epam.task5.entity.Terminal;
import com.epam.task5.entity.VanQueue;
import com.epam.task5.entity.factory.VanFactory;
import com.epam.task5.exception.MultiThreadingException;
import com.epam.task5.parser.CustomParser;
import com.epam.task5.parser.impl.TextParser;
import com.epam.task5.reader.CustomReader;
import com.epam.task5.reader.impl.TextReader;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    final static String PATH_TO_FILE = "data/data.txt";

    public static void main(String[] args) throws MultiThreadingException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL pathToFileUrl = classLoader.getResource(PATH_TO_FILE);

        CustomReader reader = new TextReader();
        List<String> stringFromFile =
                reader.read(new File(pathToFileUrl.getFile()).getAbsolutePath());
        CustomParser parser = new TextParser();
        List<Integer> integers = parser.parseText(stringFromFile);

        VanQueue vanQueue = new VanQueue();
        vanQueue.addAll(VanFactory.createVan(integers));

        Base base = Base.getInstance();
        Terminal terminal1 = new Terminal();
        Terminal terminal2 = new Terminal();
        Terminal terminal3 = new Terminal();
        base.addTerminal(terminal1);
        base.addTerminal(terminal2);
        base.addTerminal(terminal3);
        try {
            vanQueue.startVanUploading();
        }catch (InterruptedException e){
            System.out.println("interrupted com.epam.task5.exception");
        }catch (ExecutionException e){
            System.out.println("execution com.epam.task5.exception");
        }
    }
}
