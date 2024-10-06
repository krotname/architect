package name.krot.service;

import name.krot.dto.BattleRequest;
import name.krot.entity.Battle;
import name.krot.repository.BattleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class BattleService {

    private BattleRepository battleRepository;

    public String createBattle(BattleRequest battleRequest) {
        String battleId = UUID.randomUUID().toString();

        Battle battle = new Battle(battleId, battleRequest.getOrganizerId(), battleRequest.getParticipants());

        battleRepository.save(battle);
        return battleId;
    }

    public boolean isUserInBattle(String userId, String battleId) {
        Optional<Battle> battleOpt = battleRepository.findById(battleId);
        if (battleOpt.isPresent()) {
            Battle battle = battleOpt.get();
            return battle.getParticipants().contains(userId);
        }
        return false;
    }

}
