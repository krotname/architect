package name.krot.spacebatleserver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@SuperBuilder
public class SpaceShip extends MovableRotatableObject {
    @Setter
    @Getter
    List<PhotonTorpedo> photonTorpedos;
}
