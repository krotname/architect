package name.krot.spacebatleserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.action.MoveCommand;
import name.krot.spacebatleserver.model.SpaceShip;
import name.krot.spacebatleserver.service.SpaceShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void moveSpaceship(@RequestBody @Validated({Point.class}) Point point,
                              @RequestParam UUID spaceshipUUID) {
        log.info("/move point = {}, spaceshipUUID= {}",  point, spaceshipUUID);
        SpaceShip spaceShip = spaceShipService.find(spaceshipUUID).orElseThrow();
        Command command = new MoveCommand(spaceShip, point);
        command.execute();
    }

    @PostMapping(path = "/rotate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Повернуть корабль")
    @Deprecated
    public void rotateSpaceship(@RequestBody @Validated({SpaceShip.class}) SpaceShip spaceship) {
        log.info(String.valueOf(spaceship));
    }
}
