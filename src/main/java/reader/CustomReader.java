package reader;

import exception.MultiThreadingException;

public interface CustomReader {
    String read(String fileName) throws MultiThreadingException;
}
