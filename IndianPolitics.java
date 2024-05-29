import java.util.*;

abstract class MP {
    private String constituency;
    private double spendingLimit;
    private double currentSpending;
    private Driver driver;

    public MP(String constituency, double spendingLimit, Driver driver) {
        this.constituency = constituency;
        this.spendingLimit = spendingLimit;
        this.driver = driver;
    }

    public String getConstituency() {
        return constituency;
    }

    public Driver getDriver() {
        return driver;
    }

    public boolean exceedsSpendingLimit() {
        return currentSpending > spendingLimit;
    }

    public void addSpending(double amount) {
        currentSpending += amount;
    }

    public abstract void specialPermission();

    @Override
    public String toString() {
        return "MP from constituency: " + constituency;
    }
}

class Driver {
    private String name;

    public Driver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class PM extends MP {
    private Driver aircraftDriver;

    public PM(String constituency, Driver driver, Driver aircraftDriver) {
        super(constituency, 10000000, driver);
        this.aircraftDriver = aircraftDriver;
    }

    public Driver getAircraftDriver() {
        return aircraftDriver;
    }

    @Override
    public void specialPermission() {
        System.out.println("PM can give permission to arrest any minister.");
    }

    @Override
    public String toString() {
        return "PM from constituency: " + getConstituency();
    }
}

class Minister extends MP {
    public Minister(String constituency, Driver driver) {
        super(constituency, 1000000, driver);
    }

    @Override
    public void specialPermission() {
        System.out.println("Minister does not have special permissions.");
    }

    @Override
    public String toString() {
        return "Minister from constituency: " + getConstituency();
    }
}

class RegularMP extends MP {
    public RegularMP(String constituency, Driver driver) {
        super(constituency, 100000, driver);
    }

    @Override
    public void specialPermission() {
        System.out.println("Regular MP does not have special permissions.");
    }

    @Override
    public String toString() {
        return "Regular MP from constituency: " + getConstituency();
    }
}

class Commissioner {
    public void canArrest(MP mp, PM pm) {
        if (mp instanceof PM) {
            System.out.println("Cannot arrest the PM.");
        } else if (mp instanceof Minister) {
            if (pm != null) {
                System.out.println("PM permission granted: Arresting the Minister.");
            } else {
                System.out.println("PM permission not granted: Cannot arrest the Minister.");
            }
        } else {
            System.out.println("Arresting the MP without any permission.");
        }
    }
}

public class IndianPolitics {
    public static void main(String[] args) {
        Driver driver1 = new Driver("car_driver");
        Driver driver2 = new Driver("car_driver");
        Driver aircraftDriver = new Driver("aircraft_driver");

        PM pm = new PM("Delhi", driver1, aircraftDriver);
        Minister minister = new Minister("Mumbai", driver2);
        RegularMP mp = new RegularMP("Noida", driver1);

        pm.addSpending(5000000);
        minister.addSpending(1700000);
        mp.addSpending(10000);

        Commissioner commissioner = new Commissioner();

        System.out.println(pm);
        System.out.println("PM exceeds spending limit: " + pm.exceedsSpendingLimit());

        System.out.println(minister);
        System.out.println("Minister exceeds spending limit: " + minister.exceedsSpendingLimit());

        System.out.println(mp);
        System.out.println("MP exceeds spending limit: " + mp.exceedsSpendingLimit());

        System.out.println("\nArrest scenarios:");
        commissioner.canArrest(mp, pm);
        commissioner.canArrest(minister, pm);
        commissioner.canArrest(pm, null);
    }
}