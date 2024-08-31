package name.krot.spacebatleserver.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.krot.spacebatleserver.model.SpaceShip;
import name.krot.spacebatleserver.service.SpaceShipService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitState {

    Random random = new Random();

    private final SpaceShipService spaceShipService;

    @EventListener(ApplicationReadyEvent.class)
    public void initServer() {
        spaceShipInit(UUID.randomUUID(), random.nextInt(0, 359), new Point(2, 4));
        spaceShipInit(UUID.randomUUID(), random.nextInt(0, 359), new Point(12, 5));
        spaceShipInit(UUID.randomUUID(), random.nextInt(0, 359), new Point(13, 2));
    }

    private void spaceShipInit(UUID uuid, int angular, Point point){
        SpaceShip spaceShip = SpaceShip.builder().id(uuid).angular(angular).position(point).build();
        spaceShipService.save(spaceShip);
        log.info("SpaceShip was initialized " + spaceShip);
    }
}
