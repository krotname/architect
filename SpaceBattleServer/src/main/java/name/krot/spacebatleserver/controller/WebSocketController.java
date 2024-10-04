package name.krot.spacebatleserver.controller;

import lombok.extern.slf4j.Slf4j;
import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.core.EventLoop;
import name.krot.spacebatleserver.model.GameMessage;
import name.krot.spacebatleserver.model.InterpretCommand;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/receiveMessage")
    @SendTo("/topic/commands")
    public Command receiveMessage(GameMessage message) {
        log.info("Received = {} ", message);
        // Логика маршрутизации сообщения по gameId
        String gameId = message.getGameId();
        String objectId = message.getObjectId();
        String operationId = message.getOperationId();
        Map<String, Object> args = message.getArgs();

        // Формируем команду InterpretCommand
        InterpretCommand command = new InterpretCommand(gameId, objectId, operationId, args);

        // Постановка команды в очередь на выполнение (условная очередь игры)
        EventLoop.getInstance().putInLine(command);

        // Возвращаем команду для выполнения
        return command;
    }
}
