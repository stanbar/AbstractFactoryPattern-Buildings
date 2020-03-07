package com.stasbar.abstractfactorypattern;

import java.util.concurrent.atomic.AtomicInteger;

public class GameState {
    public AtomicInteger wood = new AtomicInteger(200);
    public AtomicInteger gold = new AtomicInteger(200);
    public AtomicInteger rock = new AtomicInteger(200);
    public AtomicInteger steel = new AtomicInteger(200);
    public AtomicInteger buildings = new AtomicInteger(0);
}
