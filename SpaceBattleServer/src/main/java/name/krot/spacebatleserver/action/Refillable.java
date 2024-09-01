package name.krot.spacebatleserver.action;

public interface Refillable {
    void refuel(int deltaFuel);
    void burnFuel(int deltaFuel);
    boolean checkBurnFuel(int deltaFuel);
}
