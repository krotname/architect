package name.krot.spacebatleserver.service;

import lombok.RequiredArgsConstructor;
import name.krot.spacebatleserver.model.SpaceShip;
import name.krot.spacebatleserver.repository.SpaceShipRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpaceShipServiceImpl implements SpaceShipService{

    private final SpaceShipRepository spaceShipRepository;

    @Override
    public UUID save(SpaceShip spaceShip) {
        return spaceShipRepository.save(spaceShip);
    }

    @Override
    public Optional<SpaceShip> find(UUID uuid) {
        return spaceShipRepository.find(uuid);
    }

    @Override
    public List<SpaceShip> findAll() {
        return spaceShipRepository.findAll();
    }
}
