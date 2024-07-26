package name.krot.spacebatleserver.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Builder
@Value
public class Spaceship {
    UUID id;
}
