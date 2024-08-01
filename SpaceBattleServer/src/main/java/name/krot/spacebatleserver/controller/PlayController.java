package name.krot.spacebatleserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.action.MoveCommand;
import name.krot.spacebatleserver.action.RotateCommand;
import name.krot.spacebatleserver.model.SpaceShip;
import name.krot.spacebatleserver.service.SpaceShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@RestController
@RequestMapping("/spaceship")
@RequiredArgsConstructor
@Tag(name = "PlayController", description = "Контроллер для взаимодействия с игровым сервером")
@Slf4j
public class PlayController {

    private final SpaceShipService spaceShipService;

    private final Consumer<Command> commandConsumer;

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
        commandConsumer.accept(MoveCommand.createCommand(spaceShip, point));
        spaceShipService.save(spaceShip);
    }

    @PostMapping(path = "/rotate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Повернуть корабль")
    public void rotateSpaceship(@RequestBody @Max(359) @Min(0) int angular,
                                @RequestParam UUID spaceshipUUID) {
        log.info("/rotate angular = {}, spaceshipUUID= {}", angular, spaceshipUUID);
        SpaceShip spaceShip = spaceShipService.find(spaceshipUUID).orElseThrow();
        commandConsumer.accept(RotateCommand.createCommand(spaceShip, angular));
        spaceShipService.save(spaceShip);
    }
}
