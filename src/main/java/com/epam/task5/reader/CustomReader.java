package com.epam.task5.reader;

import com.epam.task5.exception.MultiThreadingException;

import java.util.List;

public interface CustomReader {
    List<String> read(String fileName) throws MultiThreadingException;
}
