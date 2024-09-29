package name.krot.spacebatleserver.model;

import lombok.Value;

import java.util.Map;

@Value
public class GameMessage {
    String gameId;
    String objectId;
    String operationId;
    Map<String, Object> args;
}
