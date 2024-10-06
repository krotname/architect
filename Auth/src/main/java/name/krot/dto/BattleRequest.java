package name.krot.dto;

import lombok.Value;

import java.util.List;

@Value
public class BattleRequest {
    private String organizerId;
    private List<String> participants;
}