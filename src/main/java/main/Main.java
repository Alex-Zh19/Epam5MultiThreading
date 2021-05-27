package main;

import entity.Base;
import entity.Terminal;
import entity.VanQueue;
import entity.factory.VanFactory;
import exception.MultiThreadingException;
import parser.CustomParser;
import parser.impl.TextParser;
import reader.CustomReader;
import reader.impl.TextReader;
import util.IdGenerator;

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
        Terminal terminal1 = new Terminal(IdGenerator.generateTerminalId());
        Terminal terminal2 = new Terminal(IdGenerator.generateTerminalId());
        Terminal terminal3 = new Terminal(IdGenerator.generateTerminalId());
        base.addTerminal(terminal1);
        base.addTerminal(terminal2);
        base.addTerminal(terminal3);
        try {
            vanQueue.startVanUploading();
        }catch (InterruptedException e){
            System.out.println("interrupted exception");
        }catch (ExecutionException e){
            System.out.println("execution exception");
        }
    }
}
