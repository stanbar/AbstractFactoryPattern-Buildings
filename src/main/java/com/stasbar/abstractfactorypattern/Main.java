package com.stasbar.abstractfactorypattern;

import com.stasbar.abstractfactorypattern.building.Farm;
import com.stasbar.abstractfactorypattern.building.Hall;
import com.stasbar.abstractfactorypattern.factory.BuildingFactory;

public class Main {
    public static void main(String[] args) throws RequirementsNotSatisfied {
        GameState gameState = new GameState();
        Hall hall = BuildingFactory.HallFactory.getHallFactory().newInstance(gameState);
        Hall anotherHall = BuildingFactory.HallFactory.getHallFactory().newInstance(gameState);


        Farm farm = BuildingFactory.FarmFactory.getFarmFactory().newInstance(gameState);
        Farm anotherFarm = BuildingFactory.FarmFactory.getFarmFactory().newInstance(gameState);

        System.out.println("Buildings: " + gameState.buildings.get());
        System.out.printf("Resources: gold %d rock %d wood %d steel %d\n",
                gameState.gold.get(),
                gameState.rock.get(),
                gameState.wood.get(),
                gameState.steel.get());

        hall.ringBell();
    }
}
