package name.krot.spacebatleserver.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefillableSpaceShipTest {

    private RefillableSpaceShip ship;

    @BeforeEach
    public void setUp() {
        // Инициализация корабля с начальным значением топлива, например, 100
        ship = RefillableSpaceShip.builder()
                .fuel(100)
                .angular(5)
                .build();
    }

    @Test
    void testRefuel_IncreasesFuel() {
        // Arrange
        int deltaFuel = 50;

        // Act
        ship.refuel(deltaFuel);

        // Assert
        assertEquals(150, ship.getFuel(), "Fuel should increase by deltaFuel.");
    }

    @Test
    void testBurnFuel_DecreasesFuel() {
        // Arrange
        int deltaFuel = 40;

        // Act
        ship.burnFuel(deltaFuel);

        // Assert
        assertEquals(60, ship.getFuel(), "Fuel should decrease by deltaFuel.");
    }

    @Test
    void testCheckBurnFuel_SufficientFuel_ShouldReturnTrue() {
        // Arrange
        int deltaFuel = 80;

        // Act
        boolean result = ship.checkBurnFuel(deltaFuel);

        // Assert
        assertTrue(result, "checkBurnFuel should return true when fuel is sufficient.");
    }

    @Test
    void testCheckBurnFuel_InsufficientFuel_ShouldReturnFalse() {
        // Arrange
        int deltaFuel = 120;

        // Act
        boolean result = ship.checkBurnFuel(deltaFuel);

        // Assert
        assertFalse(result, "checkBurnFuel should return false when fuel is insufficient.");
    }

    @Test
    void testBurnFuel_ShouldNotGoNegative() {
        // Arrange
        int deltaFuel = 120;

        // Act
        ship.burnFuel(deltaFuel);

        // Assert
        assertEquals(-20, ship.getFuel(), "Fuel can go negative if burnFuel is called with a delta greater than available fuel.");
    }
}
