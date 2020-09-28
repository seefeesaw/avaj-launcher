package com.avaj.weather;

import com.avaj.flyables.Flyable;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    protected ArrayList<Flyable> observers;

    public Tower() {
        observers = new ArrayList<Flyable>();
    }

    public void register(Flyable flyable) throws Exception {
        if (flyable == null) { throw new Exception("cannot register <null> flyable."); }
        observers.add(flyable);
        registerSpecificTower(flyable);
    }

    protected void registerSpecificTower(Flyable flyable){ }

    public void unregister(Flyable flyable) throws Exception {
        if (flyable == null) { throw new Exception("cannot register <null> flyable."); }
        observers.remove(flyable);
    }

    protected void conditionsChanged() throws Exception {
        List<Flyable> copy = new ArrayList<>(observers);
        for (Flyable flyable : copy) { flyable.updateConditions(); }
    }
}
