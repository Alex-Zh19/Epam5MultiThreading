package reader.impl;

import exception.MultiThreadingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reader.CustomReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReader implements CustomReader {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public String read(String fileName) throws MultiThreadingException {
        checkPath(fileName);
        String text;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            text = stream.map(Objects::toString).collect(Collectors.joining("\n"));
            return text;
        } catch (IOException exception) {
            throw new MultiThreadingException(exception);
        }
    }

    private void checkPath(String fileName) throws MultiThreadingException {
        if (fileName == null) {
            logger.log(Level.ERROR, "Filepath is null");
            throw new MultiThreadingException("Filepath is null");
        }
        File file = new File(fileName);
        if (!file.isFile() || !file.exists()) {
            logger.log(Level.ERROR, "file isn't exist : {}", fileName);
            throw new MultiThreadingException("file isn't exist :" + fileName);
        }
        if (!file.canRead()) {
            logger.log(Level.ERROR, "can't read file : {}", fileName);
            throw new MultiThreadingException("can't read file :" + fileName);
        }
        if (file.length() == 0) {
            logger.log(Level.ERROR, "file is empty : {}", fileName);
            throw new MultiThreadingException("file is empty :" + fileName);
        }
    }
}
