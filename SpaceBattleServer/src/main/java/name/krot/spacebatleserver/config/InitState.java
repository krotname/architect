package name.krot.spacebatleserver.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.krot.spacebatleserver.model.SpaceShip;
import name.krot.spacebatleserver.service.SpaceShipService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitState {

    private final SpaceShipService spaceShipService;

    @EventListener(ApplicationReadyEvent.class)
    public void initServer() {
        spaceShipInit(UUID.randomUUID());
        spaceShipInit(UUID.randomUUID());
        spaceShipInit(UUID.randomUUID());
    }

    private void spaceShipInit(UUID uuid){
        SpaceShip spaceShip = SpaceShip.builder().id(uuid).build();
        spaceShipService.save(spaceShip);
        log.info("SpaceShip was initialized " + spaceShip);
    }
}
