import java.util.*;

abstract class Inverter{
    private double current;
    private double voltage;
    
    public Inverter(double current, double voltage){
        this.current = current;
        this.voltage = voltage;
    }

    public double getpowerRating(){
        return current*voltage;
    }
}

class SolarInverter extends Inverter{
    private boolean solarPanel = true;

    public SolarInverter(double current, double voltage){
        super(current, voltage);    
    }

    public boolean has_SolarPanel() {
        return solarPanel;
    }
}

class Battery extends SolarInverter{
    private boolean status = true;

    public Battery(double current, double voltage){
        super(current, voltage);
    }
    public boolean has_Battery() {
        return status;
    }
}

class Grid extends SolarInverter{
    private boolean status = false;

    public Grid(double current, double voltage){
        super(current, voltage);
    }
    public boolean has_Grid() {
        return status;
    }
}

class Check{
    public void typeOfInverter(Battery battery, Grid grid){
        if (battery.has_Battery()) {
            System.out.println("Inverter is a Solar Inverter and it is an PCU.\nPower Rating :" + battery.getpowerRating());

        }else if(grid.has_Grid()){
            System.out.println("Inverter is a Solar Inverter and it is an GTI.\nPower Rating :" + grid.getpowerRating());
        } else {
            System.out.println("Inverter is a Non-Solar Inverter.");
        }
    }
}

public class Task2 {
    public static void main(String[] args) {
        Battery battery = new Battery(2, 5);
        Grid grid = new Grid(20, 400);

        Check check = new Check();
        check.typeOfInverter(battery,grid);        
    }
}