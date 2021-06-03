package com.epam.task5.entity;


import java.util.concurrent.atomic.AtomicBoolean;

public class Terminal {
    public final AtomicBoolean isBusy = new AtomicBoolean(false);

}
