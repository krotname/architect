package name.krot.spacebatleserver.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpaceShipTest {

    @Test
    void testMovementChangesPosition() {
        // Создаем начальные значения положения и скорости
        Point initialPosition = new Point(12, 5);
        Point velocity = new Point(-7, 3);

        // Создаем объект с начальным положением и скоростью
        SpaceShip spaceShip = SpaceShip.builder()
                .position(initialPosition)
                .velocity(velocity)
                .angular(2)
                .build();

        // Рассчитываем новое положение

        spaceShip.setPosition(new Point(
                spaceShip.getPosition().x + spaceShip.getVelocity().x,
                spaceShip.getPosition().y + spaceShip.getVelocity().y
        ));

        Point expectedPosition = new Point(5, 8);
        // Проверяем, что новое положение объекта корректно
        assertEquals(expectedPosition, spaceShip.getPosition());
    }
}