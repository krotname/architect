package name.krot.spacebatleserver.repository;

import name.krot.spacebatleserver.model.SpaceShip;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpaceShipRepository {
    UUID save(SpaceShip spaceShip);
    Optional<SpaceShip> find(UUID uuid);
    List<SpaceShip> findAll();

}
