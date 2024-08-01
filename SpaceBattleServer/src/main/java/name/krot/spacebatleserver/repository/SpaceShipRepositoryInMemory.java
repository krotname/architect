package name.krot.spacebatleserver.repository;

import name.krot.spacebatleserver.model.SpaceShip;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SpaceShipRepositoryInMemory implements SpaceShipRepository {
    private final Map<UUID, SpaceShip> repoSpaceShips = new HashMap<>();

    @Override
    public UUID save(SpaceShip spaceShip){
        UUID id = spaceShip.getId();
        if(id == null) {
            id = UUID.randomUUID();
        }
        repoSpaceShips.put(id, spaceShip);
        return id;
    }

    @Override
    public Optional<SpaceShip> find(UUID uuid) {
        return Optional.ofNullable(repoSpaceShips.get(uuid));
    }

    @Override
    public List<SpaceShip> findAll() {
        return repoSpaceShips.entrySet().stream().map(Map.Entry::getValue).toList();
    }
}
