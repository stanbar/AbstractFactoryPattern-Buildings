package com.stasbar.abstractfactorypattern.building;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Building {
    final String id = UUID.randomUUID().toString();
    final AtomicInteger health = new AtomicInteger(100);
}
