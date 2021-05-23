package reader;

import exception.MultiThreadingException;

import java.util.List;

public interface CustomReader {
    List<String> read(String fileName) throws MultiThreadingException;
}
