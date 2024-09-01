package name.krot.spacebatleserver.action;

import name.krot.spacebatleserver.exceptions.CheckFuelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CheckFuelCommandTest {

    @Mock
    private Refillable refillableObject;

    private CheckFuelCommand checkFuelCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_WithSufficientFuel_ShouldPass() {
        // Arrange
        int deltaFuel = 10;
        when(refillableObject.checkBurnFuel(deltaFuel)).thenReturn(true);

        checkFuelCommand = CheckFuelCommand.builder()
                .refillableObject(refillableObject)
                .deltaFuel(deltaFuel)
                .build();

        // Act
        checkFuelCommand.execute();

        // Assert
        verify(refillableObject).checkBurnFuel(deltaFuel);
    }

    @Test
    void testExecute_WithInsufficientFuel_ShouldThrowCheckFuelException() {
        // Arrange
        int deltaFuel = 10;
        when(refillableObject.checkBurnFuel(deltaFuel)).thenReturn(false);

        checkFuelCommand = CheckFuelCommand.builder()
                .refillableObject(refillableObject)
                .deltaFuel(deltaFuel)
                .build();

        // Act & Assert
        assertThrows(CheckFuelException.class, () -> checkFuelCommand.execute());

        verify(refillableObject).checkBurnFuel(deltaFuel);
    }
}