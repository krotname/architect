package name.krot.spacebatleserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.krot.spacebatleserver.model.SpaceShip;
import name.krot.spacebatleserver.service.SpaceShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaceship")
@RequiredArgsConstructor
@Tag(name = "PlayController", description = "Контроллер для взаимодействия с игровым сервером")
@Slf4j
public class PlayController {

    private final SpaceShipService spaceShipService;

    @GetMapping(path = "/info")
    @Operation(summary = "Запросить инфо о кораблях")
    public List<SpaceShip> infoSpaceship() {
        List<SpaceShip> allSpaceShip = spaceShipService.findAll();
        log.info(String.valueOf(allSpaceShip));
        return allSpaceShip;
    }

    @PostMapping(path = "/move", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Передвинуть корабль")
    @Deprecated
    public void moveSpaceship(@RequestBody @Validated({SpaceShip.class}) SpaceShip spaceship) {
        log.info(String.valueOf(spaceship));
    }

    @PostMapping(path = "/rotate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Повернуть корабль")
    @Deprecated
    public void rotateSpaceship(@RequestBody @Validated({SpaceShip.class}) SpaceShip spaceship) {
        log.info(String.valueOf(spaceship));
    }
}
