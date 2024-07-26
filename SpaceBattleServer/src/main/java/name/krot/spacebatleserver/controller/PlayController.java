package name.krot.spacebatleserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import name.krot.spacebatleserver.model.Spaceship;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaceship")
@RequiredArgsConstructor
@Tag(name = "PlayController", description = "Контроллер для взаимодействия с игровым сервером")
@Slf4j
public class PlayController {

    @PostMapping(path = "/move", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Передвинуть корабль")
    public void moveSpaceship(@RequestBody @Validated({Spaceship.class}) Spaceship spaceship) {
        log.info(String.valueOf(spaceship));
    }

    @PostMapping(path = "/rotate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Повернуть корабль")
    public void rotateSpaceship(@RequestBody @Validated({Spaceship.class}) Spaceship spaceship) {
        log.info(String.valueOf(spaceship));
    }
}
