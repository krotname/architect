package name.krot.spacebatleserver.model;

import lombok.Value;
import name.krot.spacebatleserver.action.Command;

import java.util.Map;

@Value
public class InterpretCommand implements Command {
    String gameId;
    String objectId;
    String operationId;
    Map<String, Object> args;

    @Override
    public void execute() {
        // Логика для выполнения команды
    }
}