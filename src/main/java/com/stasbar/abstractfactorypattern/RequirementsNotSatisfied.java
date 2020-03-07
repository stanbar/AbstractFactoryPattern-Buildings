package com.stasbar.abstractfactorypattern;

public class RequirementsNotSatisfied extends Exception {
    public RequirementsNotSatisfied(String message) {
        super(message);
    }
}
