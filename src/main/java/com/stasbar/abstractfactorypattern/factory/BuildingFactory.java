package com.stasbar.abstractfactorypattern.factory;

import com.stasbar.abstractfactorypattern.GameState;
import com.stasbar.abstractfactorypattern.RequirementsNotSatisfied;
import com.stasbar.abstractfactorypattern.building.Building;
import com.stasbar.abstractfactorypattern.building.Farm;
import com.stasbar.abstractfactorypattern.building.Hall;

abstract public class BuildingFactory<T extends Building> {
    abstract int requiredWood();

    abstract int requiredGold();

    abstract void ensureSpecificRequirements(GameState gameState) throws RequirementsNotSatisfied;

    protected abstract T create();

    void checkRequirements(GameState gameState) throws RequirementsNotSatisfied {
        if (gameState.wood.get() < requiredWood())
            throw new RequirementsNotSatisfied("wood");
        if (gameState.gold.get() < requiredGold())
            throw new RequirementsNotSatisfied("gold");
        ensureSpecificRequirements(gameState);
    }

    void consumeResources(GameState gameState) {
        gameState.wood.addAndGet(-requiredWood());
        gameState.gold.addAndGet(-requiredGold());
    }

    private BuildingFactory() {
    }

    // Atomic operation
    synchronized public T newInstance(GameState gameState) throws RequirementsNotSatisfied {
        checkRequirements(gameState);
        consumeResources(gameState);
        gameState.buildings.incrementAndGet();
        return create();
    }

    static public class HallFactory extends BuildingFactory<Hall> {
        @Override
        int requiredWood() {
            return 50;
        }

        @Override
        int requiredGold() {
            return 50;
        }

        int requiredSteel() {
            return 80;
        }

        @Override
        void ensureSpecificRequirements(GameState gameState) throws RequirementsNotSatisfied {
            if (gameState.steel.get() < requiredSteel())
                throw new RequirementsNotSatisfied("steel");
            // check other specific things
        }

        private HallFactory() {
            super();
        }

        @Override
        protected Hall create() {
            return new Hall();
        }
    }

    static public class FarmFactory extends BuildingFactory<Farm> {
        @Override
        int requiredWood() {
            return 10;
        }

        @Override
        int requiredGold() {
            return 50;
        }

        int requiredRock() {
            return 80;
        }

        @Override
        void ensureSpecificRequirements(GameState gameState) throws RequirementsNotSatisfied {
            if (gameState.rock.get() < requiredRock())
                throw new RequirementsNotSatisfied("rock");
            // check other specific things
        }

        @Override
        protected Farm create() {
            return new Farm();
        }

        private FarmFactory() {
            super();
        }

    }

    private static HallFactory HALL_FACTORY;

    public static synchronized HallFactory getHallFactory() throws RequirementsNotSatisfied {
        if (HALL_FACTORY == null) {
            HALL_FACTORY = new HallFactory();
        }
        return new HallFactory();
    }

    private static FarmFactory FARM_FACTORY;

    public static synchronized FarmFactory getFarmFactory() throws RequirementsNotSatisfied {
        if (FARM_FACTORY == null) {
            FARM_FACTORY = new FarmFactory();
        }
        return new FarmFactory();
    }
}
